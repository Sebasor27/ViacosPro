package com.viaticospro.domain;

import com.viaticospro.application.InvitadoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "invitados")
@Data
public class Invitado extends BasicEntity {

    @ManyToOne(optional=false)
    @JoinColumn(name="factura_id", referencedColumnName="id")
    private Factura factura;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "created_at", nullable = true)
    private LocalDate createdAt;

    public static Invitado create(InvitadoDTO dto){
        Invitado inv = new Invitado();
        inv.setNombre(dto.getNombre());
        inv.setCargo(dto.getCargo());
        inv.setCreatedAt(dto.getCreatedAt());
        return inv;
    }
}
