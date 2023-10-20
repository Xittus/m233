package com.example.application.service;

import com.example.application.model.Reservierung;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservierungService {

    private List<Reservierung> reservierungen = new ArrayList<>();

    public void reserviereRaum(String raumName, LocalDateTime startZeit, LocalDateTime endZeit) {
        Reservierung reservierung = new Reservierung(raumName, startZeit, endZeit);
        reservierungen.add(reservierung);
    }

    public List<Reservierung> getReservierungen() {
        return reservierungen;
    }
}
