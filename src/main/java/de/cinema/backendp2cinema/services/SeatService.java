package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Seat;
import de.cinema.backendp2cinema.exceptions.SeatNotFoundException;
import de.cinema.backendp2cinema.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    private final SeatRepository SeatRepository;

    @Autowired
    public SeatService(SeatRepository SeatRepository){
        this.SeatRepository = SeatRepository;
    }

    public Seat addSeat(Seat Seat) {
        return SeatRepository.save(Seat);

    }

    public List<Seat> getAllSeats() {
        return (List<Seat>) SeatRepository.findAll();
    }

    public Seat findSeatById(UUID id) {
        return SeatRepository.findById(id).orElseThrow(()-> new SeatNotFoundException("Seat with id " + id + " not found"));
    }

    public void delete(UUID id) {
        SeatRepository.deleteById(id);
    }


}
