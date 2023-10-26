package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reservierung {

    @GeneratedValue
    @Id
    private Long id;

    private String raumName;
    private LocalDateTime startZeit;
    private LocalDateTime endZeit;

    public Reservierung(String raumName, LocalDateTime startZeit, LocalDateTime endZeit) {
        this.raumName = raumName;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
    }

    // Getter und Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaumName() {
        return raumName;
    }

    public void setRaumName(String raumName) {
        this.raumName = raumName;
    }

    public LocalDateTime getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(LocalDateTime startZeit) {
        this.startZeit = startZeit;
    }

    public LocalDateTime getEndZeit() {
        return endZeit;
    }

    public void setEndZeit(LocalDateTime endZeit) {
        this.endZeit = endZeit;
    }
}
