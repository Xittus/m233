package com.raum.application.repository;

import com.raum.application.model.Reservierung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservierungsRepository extends CrudRepository<Reservierung, Long> {
}
