package com.viaticospro.services;

import com.viaticospro.application.FacturaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FacturaService {

    List<FacturaDTO> getFacturaPorRuc(String ruc);

    List<FacturaDTO> getAllFacturas();

    FacturaDTO save(FacturaDTO dto) throws Exception;

}
