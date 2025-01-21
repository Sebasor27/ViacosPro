package com.viaticospro.controllers;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.services.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }


    @GetMapping("/factura/{ruc}")
    public ResponseEntity<?> getFacturaByRuc(@PathVariable String ruc) {
        try {
            List<FacturaDTO> rucDTO = facturaService.getFacturaPorRuc(ruc);
            return ResponseEntity.ok(rucDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mala peticion");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno");
        }
    }

    @GetMapping("/factura/list")
    public ResponseEntity<?> getAll() {
        try {
            List<FacturaDTO> rucDTO = facturaService.getAllFacturas();
            return ResponseEntity.ok(rucDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mala peticion");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno");
        }
    }

    @PostMapping("/factura/save")
    public ResponseEntity<?> saveFactura(@RequestBody FacturaDTO facturaDTO) {
        try {
            FacturaDTO rucDTO = facturaService.save(facturaDTO);
            return ResponseEntity.ok(rucDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mala peticion");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


}
