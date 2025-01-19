package com.viaticospro.services;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.application.InvitadoDTO;

import java.util.List;

public interface InvitadoService {
    List<InvitadoDTO> getInvitadosPorFactura(Long facturaId);
}
