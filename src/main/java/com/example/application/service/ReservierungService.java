package com.raum.application.service;

import com.raum.application.model.Reservierung;
import com.raum.application.repository.ReservierungsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservierungService {

    @Autowired
    public ReservierungsRepository reservierungsRepository;

    public void reserviereRaum(String raumName, LocalDateTime startZeit, LocalDateTime endZeit) {
        Reservierung reservierung = new Reservierung(raumName, startZeit, endZeit);
        reservierungsRepository.save(reservierung);
    }

    public Iterable<Reservierung> getReservierungen() {
        return reservierungsRepository.findAll();
    }
}
