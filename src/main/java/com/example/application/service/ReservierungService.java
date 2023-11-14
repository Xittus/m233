package com.example.application.service;

import com.example.application.model.Reservierung;
import com.example.application.repository.ReservierungsRepository;
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

    public boolean verifyReservierung(Reservierung reservierung1,Reservierung reservierung2){
        if (reservierung1.getStartZeit().isBefore(reservierung2.getStartZeit())){
            return true;
        } else if (reservierung1.getStartZeit().isEqual(reservierung2.getStartZeit())) {
            return false;
        } else if (reservierung1.getStartZeit().isAfter(reservierung2.getStartZeit())) {
            return true;
        } else {
            System.out.println("Fehler");
            return false;
        }
    }
}
