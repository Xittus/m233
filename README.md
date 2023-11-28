Raumreservierungs- und Benutzerverwaltungs-System
Überblick

Diese Anwendung bietet eine integrierte Lösung für die Raumreservierung und Benutzerverwaltung. Sie ermöglicht es den Benutzern, Räume für bestimmte Zeiträume zu reservieren und ihre Benutzerprofile zu verwalten. Das System umfasst robuste Sicherheitsmechanismen und eine intuitive Benutzeroberfläche.
Funktionen

    Raumreservierung: Benutzer können Räume für benötigte Zeiträume reservieren.
    Benutzerverwaltung: Registrierung, Anmeldung, und Profilverwaltung.
    Admin-Panel: Spezielle Verwaltungsoptionen für Administratoren.
    Sicherheit: Geschützt durch Spring Security, bietet das System Authentifizierung und Autorisierung.

Technologie-Stack

    Frontend: Vaadin
    Backend: Spring Boot
    Sicherheit: Spring Security
    Datenbank: Java Persistence API (JPA)

Voraussetzungen

    JDK 11 oder höher
    Maven 3.6 oder höher
    Eine kompatible SQL-Datenbank

Installation und Ausführung

    Repository klonen:

    sh

git clone [Repository-URL]

Datenbank konfigurieren: Passen Sie die application.properties Datei an, um Ihre Datenbankeinstellungen zu konfigurieren.

Anwendung bauen:

sh

mvn clean install

Anwendung starten:

sh

    mvn spring-boot:run

    Öffnen Sie einen Webbrowser und navigieren Sie zu http://localhost:8080.

Nutzung

    Registrieren Sie sich als neuer Benutzer oder melden Sie sich an, falls Sie bereits ein Konto haben.
    Reservieren Sie einen Raum über die Raumreservierungsseite.
    Admins können über das Admin-Panel Benutzer verwalten.

Tests

    Führen Sie Integrationstests durch, um die korrekte Funktionalität der Anwendung zu überprüfen.
