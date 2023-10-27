package com.example.application.views.main;

import com.example.application.security.SecurityService;
import com.example.application.service.ReservierungService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "reservierung", layout = MainLayout.class)

@PermitAll

public class ReservierungView extends VerticalLayout {

    private ReservierungService reservierungService;
    private ComboBox<String> raumAuswahl;
    private DateTimePicker startZeit;
    private DateTimePicker endZeit;
    private Button reservierenButton;


    public ReservierungView(ReservierungService reservierungService) {
        this.reservierungService = reservierungService;
        initUI();
    }

    private void initUI() {
        raumAuswahl = new ComboBox<>("Raum auswählen");
        raumAuswahl.setItems("Raum 1", "Raum 2", "Raum 3"); // Hier können Sie die Raumnamen hinzufügen

        startZeit = new DateTimePicker("Startzeit");
        endZeit = new DateTimePicker("Endzeit");

        reservierenButton = new Button("Reservieren", e -> reserviereRaum());



        add(raumAuswahl, startZeit, endZeit, reservierenButton);
    }

    private void reserviereRaum() {
        try {
            reservierungService.reserviereRaum(raumAuswahl.getValue(), startZeit.getValue(), endZeit.getValue());
            Notification.show("Raum erfolgreich reserviert.");
        } catch (Exception e) {
            Notification.show("Fehler bei der Reservierung: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }
}
