package com.example.application.model;

import java.time.LocalDateTime;

public class Reservierung {

    private String raumName;
    private LocalDateTime startZeit;
    private LocalDateTime endZeit;

    public Reservierung(String raumName, LocalDateTime startZeit, LocalDateTime endZeit) {
        this.raumName = raumName;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
    }

    // Getter und Setter
}
