package com.viaticospro.services;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.application.InvitadoDTO;
import com.viaticospro.domain.Factura;
import com.viaticospro.domain.Invitado;
import com.viaticospro.repositories.FacturaRepository;
import com.viaticospro.repositories.InvitadoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class FacturaServiceImpl implements FacturaService{

    private final FacturaRepository repo;
    private final InvitadoRepository invitadoRepository;

    public FacturaServiceImpl(FacturaRepository repo, InvitadoRepository invitadoRepository) {
        this.repo = repo;
        this.invitadoRepository = invitadoRepository;
    }

    @Override
    public List<FacturaDTO> getFacturaPorRuc(String ruc) {
        List<FacturaDTO> dto = repo.findByRucEstablecimiento(ruc).stream().map(FacturaDTO::new).collect(toList());
        return dto;
    }

    @Override
    public List<FacturaDTO> getAllFacturas() {
        List<FacturaDTO> listFacturas = StreamSupport.stream(repo.findAll().spliterator(), false)
                .map(FacturaDTO::new)
                .collect(toList());
        return listFacturas;
    }

    @Override
    public FacturaDTO save(FacturaDTO dto) throws Exception {
        Factura factura = Factura.create(dto);
        Factura saved = repo.save(factura);
        List<Invitado> invitados = dto.getInvitados().stream().map(Invitado::create).toList();
        invitados = invitados.stream().map(invitado -> {
            invitado.setFactura(saved);
            return invitado;
        }).toList();
        invitadoRepository.saveAll(invitados);
        FacturaDTO facturaSaved = new FacturaDTO(repo.findById(saved.getId()).get());
        return facturaSaved;
    }
}
