package com.viaticospro.controllers;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.application.InvitadoDTO;
import com.viaticospro.services.FacturaService;
import com.viaticospro.services.InvitadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InvitadosController {

    private final InvitadoService invitadoService;

    public InvitadosController(InvitadoService invitadoService) {
        this.invitadoService = invitadoService;
    }


    @GetMapping("/factura/invitados/{facturaId}")
    public ResponseEntity<?> getFacturaByRuc(@PathVariable Long facturaId) {
        try {
            List<InvitadoDTO> invitadoDTOS = invitadoService.getInvitadosPorFactura(facturaId);
            return ResponseEntity.ok(invitadoDTOS);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mala peticion");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno");
        }
    }
}
