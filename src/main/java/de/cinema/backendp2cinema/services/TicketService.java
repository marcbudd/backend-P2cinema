package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Benutzer;
import de.cinema.backendp2cinema.entities.Ticket;
import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import de.cinema.backendp2cinema.enums.TicketStatus;
import de.cinema.backendp2cinema.exceptions.BenutzerNotFoundException;
import de.cinema.backendp2cinema.exceptions.TicketNotFoundException;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNotFoundException;
import de.cinema.backendp2cinema.repositories.BenutzerRepository;
import de.cinema.backendp2cinema.repositories.TicketRepository;
import de.cinema.backendp2cinema.repositories.VorstellungsplatzRepository;
import de.cinema.backendp2cinema.requestObjects.TicketRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    TicketRepository ticketRepository;

    VorstellungsplatzService vorstellungsplatzService;

    VorstellungsplatzRepository vorstellungsplatzRepository;

    BenutzerRepository benutzerRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, VorstellungsplatzService vorstellungsplatzService, VorstellungsplatzRepository vorstellungsplatzRepository, BenutzerRepository benutzerRepository) {
        this.ticketRepository = ticketRepository;
        this.vorstellungsplatzService = vorstellungsplatzService;
        this.vorstellungsplatzRepository = vorstellungsplatzRepository;
        this.benutzerRepository = benutzerRepository;
    }


    public ResponseEntity<Object> addTicket(TicketRequestObject newTicket) {
        List<UUID> vplatzIds = newTicket.getVorstellungsplatzIds();
        UUID benutzerId = newTicket.getBenutzerId();

        //Benutzer nach ID finden
        Optional<Benutzer> optionalBenutzer = benutzerRepository.findById(benutzerId);
        if(optionalBenutzer.isEmpty()){
            return new ResponseEntity<>(new BenutzerNotFoundException(benutzerId).getMessage(), HttpStatus.NOT_FOUND);
        }
        Benutzer benutzer = optionalBenutzer.get();

        //Vorstellungsplätze nach Id finden
        List<Vorstellungsplatz> vplatzList = new ArrayList<>();
        for(UUID vplatzId: vplatzIds){

            Optional<Vorstellungsplatz> optionalVplatz = vorstellungsplatzRepository.findById(vplatzId);
            if(optionalVplatz.isEmpty()){
                return new ResponseEntity<>(new VorstellungsplatzNotFoundException(vplatzId).getMessage(), HttpStatus.NOT_FOUND);
            }
            vplatzList.add(optionalVplatz.get());
        }

        //Zeit und Datum speichern
        Date buchungDatum = new Date(System.currentTimeMillis());
        Time buchungZeit = new Time(System.currentTimeMillis());

        //Ticket Objekt erstellen
        Ticket ticket = new Ticket(null, benutzer, buchungDatum, buchungZeit, TicketStatus.RESERVIERT, vplatzList, 0.0);

        //QR-Code und Gesamtpreis hinzufügen
        byte[] qrCode = QRCodeGenerator.generateQRCode("oscarcinema-dhbwproject.de/info" + ticket.getId());
        ticket.setQrCode(qrCode);
        this.gesamtpreisBerechnen(ticket);
        Ticket addedTicket = ticketRepository.save(ticket);


        return new ResponseEntity<>(addedTicket, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> storniereTicket(UUID id) {

        //Suche Ticket mit id
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isEmpty()) {
            return new ResponseEntity<>(new TicketNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

        Ticket ticket = optionalTicket.get();

        //Setze jeden zugehörigen Vorstellungsplatz auf "FREI"
        List<Vorstellungsplatz> vorstellungsplatzList = ticket.getVorstellungsplatzList();
        for(Vorstellungsplatz vplatz:vorstellungsplatzList){
            UUID vplatzId = vplatz.getId();
            vorstellungsplatzService.befreieSitz(vplatzId);
        }

        ticket.setTicketStatus(TicketStatus.STORNIERT);
        ticketRepository.save(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    private void gesamtpreisBerechnen(Ticket ticket){
        double gesamtpreis = 0.0;
        for(Vorstellungsplatz vplatz: ticket.getVorstellungsplatzList()){
            double vplatzPreis = vplatz.getPreis().getPreis();
            double vplatzRabattAbsolut = vplatz.getRabatt().getRabattAbsolut();
            double vplatzRabattProzentual = vplatz.getRabatt().getRabattProzentual();

            double vplatzPreisMitRabatt = (vplatzPreis * (1.0 - vplatzRabattProzentual)) - vplatzRabattAbsolut;

            gesamtpreis = gesamtpreis + vplatzPreisMitRabatt;
        }

        ticket.setGesamtpreis(gesamtpreis);
    }

}
