package com.viaticospro.application;

import com.viaticospro.domain.Invitado;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InvitadoDTO {
    private String nombre;
    private String cargo;
    private LocalDate createdAt;

    public InvitadoDTO() {
        // Constructor vacío necesario para Jackson
    }

    public InvitadoDTO(String nombre, String cargo) {
        this.cargo = cargo;
        this.nombre = nombre;
    }

    public InvitadoDTO(Invitado invitado) {
        this.cargo = invitado.getCargo();
        this.nombre = invitado.getNombre();
        this.createdAt = LocalDate.from(invitado.getCreatedAt());
    }
}
