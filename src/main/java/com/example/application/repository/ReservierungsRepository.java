package com.example.application.repository;

import com.example.application.model.Reservierung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReservierungsRepository extends CrudRepository<Reservierung, Long> {
}
