package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CrearPedidoRequest;
import com.tienditayeya.tyback_end.dto.PedidoResponse;
import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.model.Pedido;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.model.EnvioModel;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import com.tienditayeya.tyback_end.repository.PedidoRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import com.tienditayeya.tyback_end.repository.EnvioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final EnvioRepository envioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         ProductoRepository productoRepository,
                         DetallePedidoRepository detallePedidoRepository,
                         EnvioRepository envioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.envioRepository = envioRepository;
    }

    @Transactional(readOnly = true)
    public List<PedidoResponse> obtenerTodos() {
        return pedidoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponse obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
    }

    @Transactional
    public PedidoResponse crearPedido(CrearPedidoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        List<DetallePendiente> detallesPendientes = new ArrayList<>();
        Set<Integer> idsProductos = new HashSet<>();

        for (CrearPedidoRequest.ItemPedidoRequest item : request.items()) {
            if (!idsProductos.add(item.idProducto())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un producto no puede repetirse dos veces en el mismo pedido");
            }
            Producto producto = productoRepository.findById(item.idProducto())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Producto no encontrado: " + item.idProducto()
                    ));

            if (producto.getStock() == null || producto.getStock() < item.cantidad()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Stock insuficiente para " + producto.getNombreProducto()
                );
            }

            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(item.cantidad()));
            total = total.add(subtotal);
            detallesPendientes.add(new DetallePendiente(producto, item.cantidad()));
        }

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(generarNumeroPedido());
        pedido.setTotal(total);
        pedido.setEstadoPedido("Procesando");
        pedido.setFechaCreacionPedido(LocalDateTime.now());
        pedido.setUsuario(usuario);
        Pedido guardado = pedidoRepository.save(pedido);

        List<DetallePedido> detalles = new ArrayList<>();
        for (DetallePendiente pendiente : detallesPendientes) {
            Producto producto = pendiente.producto();
            int nuevoStock = producto.getStock() - pendiente.cantidad();
            producto.setStock(nuevoStock);
            if (nuevoStock == 0) {
                producto.setDisponibilidad("agotado");
            }
            productoRepository.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setCantidad(pendiente.cantidad());
            detalle.setPrecioTotalUnitario(producto.getPrecio());
            detalle.setPedidosIdPedidos(guardado.getIdPedidos());
            detalle.setProductosIdProductos(producto.getIdProductos());
            detalles.add(detalle);
        }
        detallePedidoRepository.saveAll(detalles);

        return toResponse(guardado);
    }


    @Transactional
    public PedidoResponse actualizarEstado(Long id, String estado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        String anterior = pedido.getEstadoPedido() == null ? "" : pedido.getEstadoPedido();
        boolean seCancela = !"Cancelado".equalsIgnoreCase(anterior) && "Cancelado".equalsIgnoreCase(estado);
        boolean seReactiva = "Cancelado".equalsIgnoreCase(anterior) && !"Cancelado".equalsIgnoreCase(estado);

        if (seCancela) {
            ajustarStockPorPedido(id, true);
        } else if (seReactiva) {
            ajustarStockPorPedido(id, false);
        }

        pedido.setEstadoPedido(estado);
        Pedido guardado = pedidoRepository.save(pedido);
        envioRepository.findByPedidosIdPedidos(id).ifPresent(envio -> {
            envio.setEstadoEnvio(estadoEnvioParaPedido(estado));
            envioRepository.save(envio);
        });
        return toResponse(guardado);
    }

    /**
     * Al cancelar se devuelve inventario. Si un pedido cancelado se reactiva,
     * se valida y descuenta otra vez el stock dentro de la misma transacción.
     */
    private void ajustarStockPorPedido(Long pedidoId, boolean devolver) {
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidosIdPedidos(pedidoId);
        for (DetallePedido detalle : detalles) {
            Producto producto = productoRepository.findById(detalle.getProductosIdProductos())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
                            "No se encontró un producto del pedido para ajustar su stock"));
            int cantidad = detalle.getCantidad() == null ? 0 : detalle.getCantidad();
            int actual = producto.getStock() == null ? 0 : producto.getStock();
            if (devolver) {
                producto.setStock(actual + cantidad);
            } else {
                if (actual < cantidad) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "No hay stock suficiente para reactivar el pedido: " + producto.getNombreProducto());
                }
                producto.setStock(actual - cantidad);
            }
            producto.setDisponibilidad(producto.getStock() > 0 ? "disponible" : "agotado");
            productoRepository.save(producto);
        }
    }

    @Transactional
    public void eliminarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    private Integer generarNumeroPedido() {
        for (int intento = 0; intento < 20; intento++) {
            int numero = ThreadLocalRandom.current().nextInt(10000, 100000);
            if (!pedidoRepository.existsByNumeroPedido(numero)) {
                return numero;
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo generar el número de pedido");
    }

    private String estadoEnvioParaPedido(String estadoPedido) {
        return switch (estadoPedido) {
            case "Enviado" -> "En tránsito";
            case "Entregado" -> "Entregado";
            case "Cancelado" -> "Cancelado";
            case "Pagado", "Procesando", "Pendiente" -> "Preparando";
            default -> "Preparando";
        };
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getIdPedidos(),
                pedido.getNumeroPedido(),
                pedido.getTotal(),
                pedido.getEstadoPedido(),
                pedido.getFechaCreacionPedido(),
                pedido.getUsuario() != null ? pedido.getUsuario().getIdUsuario() : null
        );
    }

    private record DetallePendiente(Producto producto, int cantidad) {}
}
