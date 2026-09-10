package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.AdminSummaryResponse;
import com.tienditayeya.tyback_end.dto.ClienteAdminResponse;
import com.tienditayeya.tyback_end.dto.PedidoAdminResponse;
import com.tienditayeya.tyback_end.model.Pedido;
import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.repository.PedidoRepository;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import com.tienditayeya.tyback_end.repository.EnvioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Service
public class AdminDashboardService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final EnvioRepository envioRepository;

    public AdminDashboardService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository,
                                 ProductoRepository productoRepository, DetallePedidoRepository detallePedidoRepository,
                                 EnvioRepository envioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.envioRepository = envioRepository;
    }

    @Transactional(readOnly = true)
    public AdminSummaryResponse resumen() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        BigDecimal ventas = pedidos.stream()
                .filter(p -> !"Cancelado".equalsIgnoreCase(p.getEstadoPedido()))
                .map(Pedido::getTotal).filter(t -> t != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        YearMonth ahora = YearMonth.now();
        long pedidosMes = pedidos.stream().filter(p -> p.getFechaCreacionPedido() != null
                && YearMonth.from(p.getFechaCreacionPedido()).equals(ahora)).count();
        long clientes = usuarioRepository.findAll().stream()
                .filter(u -> u.getRol() != null && "user".equalsIgnoreCase(u.getRol().getRolUsuario())).count();
        long productos = productoRepository.count();
        long stockBajo = productoRepository.findAll().stream().filter(p -> p.getStock() != null && p.getStock() <= 2).count();
        return new AdminSummaryResponse(ventas, pedidosMes, clientes, productos, stockBajo);
    }

    @Transactional(readOnly = true)
    public List<ClienteAdminResponse> clientes() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getRol() != null && "user".equalsIgnoreCase(u.getRol().getRolUsuario()))
                .map(u -> toCliente(u, pedidos)).toList();
    }


    @Transactional(readOnly = true)
    public List<PedidoAdminResponse> pedidos() {
        return pedidoRepository.findAll().stream()
                .sorted((a,b) -> Long.compare(b.getIdPedidos(), a.getIdPedidos()))
                .map(p -> {
                    List<String> productos = detallePedidoRepository.findByPedidosIdPedidos(p.getIdPedidos()).stream()
                            .map(DetallePedido::getProductosIdProductos)
                            .map(productoRepository::findById)
                            .flatMap(java.util.Optional::stream)
                            .map(Producto::getNombreProducto)
                            .toList();
                    var envio = envioRepository.findByPedidosIdPedidos(p.getIdPedidos()).orElse(null);
                    return new PedidoAdminResponse(p.getIdPedidos(), p.getNumeroPedido(),
                            p.getUsuario()!=null?p.getUsuario().getNombreCompleto():"",
                            p.getUsuario()!=null?p.getUsuario().getEmail():"", productos, p.getTotal(),
                            p.getEstadoPedido(), p.getFechaCreacionPedido(),
                            envio != null ? envio.getNumeroRastreo() : "—",
                            envio != null ? envio.getEstadoEnvio() : "Sin envío");
                }).toList();
    }

    private ClienteAdminResponse toCliente(Usuario u, List<Pedido> pedidos) {
        List<Pedido> delUsuario = pedidos.stream()
                .filter(p -> p.getUsuario() != null && p.getUsuario().getIdUsuario().equals(u.getIdUsuario())).toList();
        BigDecimal gastado = delUsuario.stream()
                .filter(p -> !"Cancelado".equalsIgnoreCase(p.getEstadoPedido()))
                .map(Pedido::getTotal).filter(t -> t != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ClienteAdminResponse(u.getIdUsuario(), u.getNombreCompleto(), u.getEmail(), u.getTelefono(),
                u.getFechaRegistro(), delUsuario.size(), gastado);
    }
}
