package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Seatingplan;
import de.cinema.backendp2cinema.exceptions.SeatingplanNotFoundException;
import de.cinema.backendp2cinema.repositories.SeatingplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatingplanService {

    private final SeatingplanRepository SeatingplanRepository;

    @Autowired
    public SeatingplanService(SeatingplanRepository SeatingplanRepository){
        this.SeatingplanRepository = SeatingplanRepository;
    }

    public Seatingplan addSeatingplan(Seatingplan Seatingplan) {
        return SeatingplanRepository.save(Seatingplan);

    }

    public List<Seatingplan> getAllSeatingplans() {
        return (List<Seatingplan>) SeatingplanRepository.findAll();
    }

    public Seatingplan findSeatingplanById(UUID id) {
        return SeatingplanRepository.findById(id).orElseThrow(()-> new SeatingplanNotFoundException("Seatingplan with id " + id + " not found"));
    }

    public void delete(UUID id) {
        SeatingplanRepository.deleteById(id);
    }


}
