package com.viaticospro.repositories;

import com.viaticospro.application.FacturaDTO;
import com.viaticospro.domain.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {
    List<Factura> findByRucEstablecimiento(String ruc);
}
