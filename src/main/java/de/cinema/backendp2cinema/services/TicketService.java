package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Ticket;
import de.cinema.backendp2cinema.exceptions.TicketNotFoundException;
import de.cinema.backendp2cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository TicketRepository;

    @Autowired
    public TicketService(TicketRepository TicketRepository){
        this.TicketRepository = TicketRepository;
    }

    public Ticket addTicket(Ticket Ticket) {
        return TicketRepository.save(Ticket);

    }

    public List<Ticket> getAllTickets() {
        return (List<Ticket>) TicketRepository.findAll();
    }

    public Ticket findTicketById(UUID id) {
        return TicketRepository.findById(id).orElseThrow(()-> new TicketNotFoundException("Ticket with id " + id + " not found"));
    }

    public void delete(UUID id) {
        TicketRepository.deleteById(id);
    }


}
