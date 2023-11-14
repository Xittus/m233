package com.example.application.service;

import com.example.application.model.Reservierung;
import com.example.application.repository.ReservierungsRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservierungService {

    @Autowired
    public ReservierungsRepository reservierungsRepository;

    public void reserviereRaum(String raumName, LocalDateTime startZeit, LocalDateTime endZeit) {
        if(endZeit.isAfter(startZeit)) {
            Reservierung reservierung = new Reservierung(raumName, startZeit, endZeit);
            reservierungsRepository.save(reservierung);

            Notification.show("Raum erfolgreich reserviert.");
        } else {

            Notification.show("Raum konnte nicht reserviert werden.");
        }
    }


    public Iterable<Reservierung> getReservierungen() {
        return reservierungsRepository.findAll();
    }


}
