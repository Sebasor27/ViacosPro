package com.viaticospro.domain;

import com.viaticospro.application.FacturaDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
public class Factura extends BasicEntity {

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "detalle", nullable = false)
    private String detalle;

    @Column(name = "nombre_establecimiento", nullable = false)
    private String nombreEstablecimiento;

    @Column(name = "ruc_establecimiento", nullable = false)
    private String rucEstablecimiento;

    @Column(name = "numero_factura", nullable = false)
    private String numeroFactura;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "iva", nullable = false)
    private BigDecimal iva;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "numero_invitados", nullable = false)
    private int numeroInvitados;

    @OneToMany(mappedBy = "factura", fetch= FetchType.EAGER)
    private List<Invitado> invitados;

    public static Factura create(FacturaDTO dto) throws Exception {
        Factura factura = new Factura();

        if(dto.getNumeroInvitados()< dto.getInvitados().size()){
            throw new Exception("Numero de invitados incorrecto");
        }

        LocalDate hoy = LocalDate.now();
        LocalDate fechaFactura = dto.getFecha();
        long diasDiferencia = ChronoUnit.DAYS.between(fechaFactura, hoy);

        if (diasDiferencia < 0) {
            throw new Exception("La fecha de la factura no puede ser en el futuro.");
        }
        if (diasDiferencia > 90) {
            throw new Exception("La fecha de la factura no puede ser mayor a 90 días en el pasado.");
        }
        // Validar el RUC del establecimiento
        if (!validarRucEcuatoriano(dto.getRucEstablecimiento())) {
            throw new IllegalArgumentException("El RUC del establecimiento es inválido.");
        }


        factura.setFecha(dto.getFecha());
        factura.setDetalle(dto.getDetalle());
        factura.setNombreEstablecimiento(dto.getNombreEstablecimiento());
        factura.setRucEstablecimiento(dto.getRucEstablecimiento());
        factura.setNumeroFactura(dto.getNumeroFactura());
        factura.setSubtotal(dto.getSubtotal());
        factura.setIva(dto.getIva());
        factura.setTotal(dto.getTotal());
        factura.setNumeroInvitados(dto.getNumeroInvitados());
        return factura;
    }


    /**
     * Valida un RUC ecuatoriano.
     *
     * @param ruc El RUC a validar
     * @return true si el RUC es válido, false de lo contrario
     */
    private static boolean validarRucEcuatoriano(String ruc) {
        if (ruc == null || ruc.length() != 13 || !ruc.matches("\\d+")) {
            return false;
        }

        String cedula = ruc.substring(0, 10);
        if (!validarCedulaEcuatoriana(cedula)) {
            return false;
        }

        char tipoContribuyente = ruc.charAt(10);
        if (tipoContribuyente != '6' && tipoContribuyente != '9' && (tipoContribuyente < '0' || tipoContribuyente > '5')) {
            return false;
        }

        String codigoEstablecimiento = ruc.substring(10, 13);
        return "001".equals(codigoEstablecimiento);
    }

    /**
     * Valida una cédula ecuatoriana.
     *
     * @param cedula La cédula a validar
     * @return true si la cédula es válida, false de lo contrario
     */
    private static boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula.length() != 10) {
            return false;
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito < 0 || tercerDigito > 5) {
            return false;
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        int digitoVerificador = 10 - (suma % 10);
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }

        return digitoVerificador == Character.getNumericValue(cedula.charAt(9));
    }

}
