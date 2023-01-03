package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Overlength;
import de.cinema.backendp2cinema.exceptions.OverlengthNotFoundException;
import de.cinema.backendp2cinema.repositories.OverlengthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OverlengthService {

    private final OverlengthRepository OverlengthRepository;

    @Autowired
    public OverlengthService(OverlengthRepository OverlengthRepository){
        this.OverlengthRepository = OverlengthRepository;
    }

    public Overlength addOverlength(Overlength Overlength) {
        return OverlengthRepository.save(Overlength);

    }

    public List<Overlength> getAllOverlengths() {
        return (List<Overlength>) OverlengthRepository.findAll();
    }

    public Overlength findOverlengthById(UUID id) {
        return OverlengthRepository.findById(id).orElseThrow(()-> new OverlengthNotFoundException("Overlength with id " + id + " not found"));
    }

    public void delete(UUID id) {
        OverlengthRepository.deleteById(id);
    }


}
