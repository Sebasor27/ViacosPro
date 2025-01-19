package com.viaticospro.application;

import com.viaticospro.domain.Factura;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class FacturaDTO {
    private LocalDate fecha;
    private String detalle;
    private String nombreEstablecimiento;
    private String rucEstablecimiento;
    private String numeroFactura;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private int numeroInvitados;
    private LocalDate createdAt;
    private List<InvitadoDTO> invitados;

    public FacturaDTO() {
        // Constructor vacío necesario para Jackson
    }

    public FacturaDTO(Factura factura) {
        this.fecha = factura.getFecha();
        this.detalle = factura.getDetalle();
        this.nombreEstablecimiento = factura.getNombreEstablecimiento();
        this.rucEstablecimiento = factura.getRucEstablecimiento();
        this.numeroFactura = factura.getNumeroFactura();
        this.subtotal = factura.getSubtotal();
        this.iva = factura.getIva();
        this.total = factura.getTotal();
        this.numeroInvitados = factura.getNumeroInvitados();
        this.createdAt = LocalDate.from(factura.getCreatedAt());
        this.invitados = Objects.isNull(factura.getInvitados()) ? Collections.emptyList() : factura.getInvitados().stream()
                .map(InvitadoDTO::new)
                .collect(Collectors.toList());

    }
}
