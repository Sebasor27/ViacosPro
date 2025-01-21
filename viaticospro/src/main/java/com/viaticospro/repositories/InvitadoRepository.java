package com.viaticospro.repositories;

import com.viaticospro.domain.Invitado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitadoRepository extends CrudRepository<Invitado, Long> {
    List<Invitado> findAllByFactura_Id(Long id);
}
