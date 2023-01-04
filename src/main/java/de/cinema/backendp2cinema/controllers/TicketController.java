package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Ticket;
import de.cinema.backendp2cinema.exceptions.TicketNotFoundException;
import de.cinema.backendp2cinema.exceptions.KinoNotFoundException;
import de.cinema.backendp2cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //alle Tickets zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Ticket>> findAll(){
        Iterable<Ticket> tickete = ticketRepository.findAll();
        return new ResponseEntity<>(tickete, HttpStatus.OK);
    }

    //Ticket nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Ticket> suche = ticketRepository.findById(id);
        try{
            Ticket gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new TicketNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Ticket hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Ticket> save(@RequestBody Ticket newTicket){
        Ticket addedTicket = ticketRepository.save(newTicket);
        return new ResponseEntity<>(addedTicket, HttpStatus.CREATED);
    }

    //Ticket ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Ticket ticket) {
        Optional<Ticket> toUpdate = ticketRepository.findById(id);

        try {
            UUID ticketId = toUpdate.get().getId();
            ticket.setId(ticketId);
            ticketRepository.save(ticket);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Ticket nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Ticket> toDelete = ticketRepository.findById(id);
        try{
            UUID ticketId = toDelete.get().getId();
            ticketRepository.deleteById(ticketId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}

