package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.AnalyticsResponse;
import com.tienditayeya.tyback_end.model.Pedido;
import com.tienditayeya.tyback_end.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AnalyticsService {
    private final PedidoRepository pedidoRepository;
    public AnalyticsService(PedidoRepository pedidoRepository){this.pedidoRepository=pedidoRepository;}

    @Transactional(readOnly = true)
    public AnalyticsResponse performance(){
        List<Pedido> pedidos=pedidoRepository.findAll();
        YearMonth actual=YearMonth.now(); Locale locale=Locale.forLanguageTag("es-MX");
        List<String> meses=new ArrayList<>(); List<BigDecimal> ventas=new ArrayList<>(); List<Long> cantidad=new ArrayList<>();
        for(int i=5;i>=0;i--){
            YearMonth mes=actual.minusMonths(i);
            BigDecimal total=pedidos.stream().filter(p->p.getFechaCreacionPedido()!=null&&YearMonth.from(p.getFechaCreacionPedido()).equals(mes))
                    .filter(p->!"Cancelado".equalsIgnoreCase(p.getEstadoPedido()))
                    .map(Pedido::getTotal).filter(t->t!=null).reduce(BigDecimal.ZERO,BigDecimal::add);
            long n=pedidos.stream().filter(p->p.getFechaCreacionPedido()!=null&&YearMonth.from(p.getFechaCreacionPedido()).equals(mes))
                    .filter(p->!"Cancelado".equalsIgnoreCase(p.getEstadoPedido())).count();
            String nombre=mes.getMonth().getDisplayName(TextStyle.FULL,locale);
            meses.add(nombre.substring(0,1).toUpperCase(locale)+nombre.substring(1)); ventas.add(total); cantidad.add(n);
        }
        return new AnalyticsResponse(meses,ventas,cantidad);
    }
}
