package com.viaticospro.services;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.application.InvitadoDTO;
import com.viaticospro.repositories.FacturaRepository;
import com.viaticospro.repositories.InvitadoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class InvitadoServiceImpl implements InvitadoService{

    private final InvitadoRepository repo;

    public InvitadoServiceImpl(InvitadoRepository repo) {
        this.repo = repo;
    }


    @Override
    public List<InvitadoDTO> getInvitadosPorFactura(Long facturaId) {
        List<InvitadoDTO> listInvitados = StreamSupport.stream(repo.findAllByFactura_Id(facturaId).spliterator(), false)
                .map(InvitadoDTO::new)
                .collect(Collectors.toList());
        return listInvitados;
    }

}
