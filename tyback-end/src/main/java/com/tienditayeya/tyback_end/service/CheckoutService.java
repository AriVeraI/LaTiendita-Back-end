package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CrearPedidoRequest;
import com.tienditayeya.tyback_end.dto.DireccionesDTO;
import com.tienditayeya.tyback_end.dto.PedidoResponse;
import com.tienditayeya.tyback_end.dto.RequetsEnvioDTO;
import com.tienditayeya.tyback_end.model.Pago;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CheckoutService {

    private static final java.util.Set<String> METODOS_PAGO_PERMITIDOS = java.util.Set.of(
            "Tarjeta simulada",
            "PayPal simulado",
            "Mercado Pago simulado"
    );

    private final PedidoService pedidoService;
    private final DireccionesService direccionesService;
    private final PagoService pagoService;
    private final EnvioService envioService;

    public CheckoutService(PedidoService pedidoService,
                           DireccionesService direccionesService,
                           PagoService pagoService,
                           EnvioService envioService) {
        this.pedidoService = pedidoService;
        this.direccionesService = direccionesService;
        this.pagoService = pagoService;
        this.envioService = envioService;
    }

    /**
     * Registra toda la compra en una sola transacción. Si falla la dirección,
     * el pago simulado o el envío, también se revierte el pedido y el stock.
     */
    @Transactional
    public PedidoResponse procesarCompra(CrearPedidoRequest request) {
        validarMetodoPago(request.pago().metodoPago());

        PedidoResponse pedido = pedidoService.crearPedido(request);
        guardarDireccion(request);
        guardarPagoSimulado(request, pedido);
        crearEnvioSimulado(pedido);

        return pedido;
    }

    private void guardarDireccion(CrearPedidoRequest request) {
        CrearPedidoRequest.DireccionPedidoRequest direccion = request.direccion();

        DireccionesDTO dto = new DireccionesDTO(
                null,
                direccion.calle().trim(),
                direccion.numero().trim(),
                direccion.colonia().trim(),
                direccion.ciudad().trim(),
                direccion.estado().trim(),
                direccion.codigoPostal().trim(),
                request.idUsuario()
        );

        direccionesService.crear(dto);
    }

    private void guardarPagoSimulado(CrearPedidoRequest request, PedidoResponse pedido) {
        Pago pago = new Pago();
        pago.setMetodoPago(request.pago().metodoPago().trim());
        pago.setMonto(pedido.total().doubleValue());
        pago.setFechaPago(LocalDateTime.now());
        pago.setPedidosIdPedidos(pedido.idPedidos());
        pagoService.guardarPago(pago);
    }

    private void crearEnvioSimulado(PedidoResponse pedido) {
        String numeroRastreo = "YEYA-" + pedido.numeroPedido();
        LocalDateTime ahora = LocalDateTime.now();

        RequetsEnvioDTO envio = new RequetsEnvioDTO(
                "Envío estándar",
                numeroRastreo,
                ahora,
                LocalDate.now().plusDays(5).toString(),
                pedido.idPedidos()
        );

        envioService.crearEnvio(envio);
    }

    private void validarMetodoPago(String metodoPago) {
        String normalizado = metodoPago == null ? "" : metodoPago.trim();
        if (!METODOS_PAGO_PERMITIDOS.contains(normalizado)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Método de pago no permitido para este prototipo"
            );
        }
    }
}
