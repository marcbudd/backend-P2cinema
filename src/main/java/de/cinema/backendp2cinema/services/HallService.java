package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Hall;
import de.cinema.backendp2cinema.exceptions.HallNotFoundException;
import de.cinema.backendp2cinema.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HallService {

    private final HallRepository HallRepository;

    @Autowired
    public HallService(HallRepository HallRepository) {
        this.HallRepository = HallRepository;
    }

    public Hall addHall(Hall Hall) {
        return HallRepository.save(Hall);

    }

    public List<Hall> getAllHalls() {
        return (List<Hall>) HallRepository.findAll();
    }

    public Hall findHallById(UUID id) {
        return HallRepository.findById(id).orElseThrow(() -> new HallNotFoundException("Hall with id " + id + " not found"));
    }

    public void delete(UUID id) {
        HallRepository.deleteById(id);
    }

}