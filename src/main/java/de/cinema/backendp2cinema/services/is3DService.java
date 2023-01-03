package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.is3D;
import de.cinema.backendp2cinema.exceptions.is3DNotFoundException;
import de.cinema.backendp2cinema.repositories.is3DRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class is3DService {

    private final is3DRepository is3DRepository;

    @Autowired
    public is3DService(is3DRepository is3DRepository){
        this.is3DRepository = is3DRepository;
    }

    public is3D addis3D(is3D is3D) {
        return is3DRepository.save(is3D);

    }

    public List<is3D> getAllis3Ds() {
        return (List<is3D>) is3DRepository.findAll();
    }

    public is3D findis3DById(UUID id) {
        return is3DRepository.findById(id).orElseThrow(()-> new is3DNotFoundException("Is3D with id " + id + " not found"));
    }

    public void delete(UUID id) {
        is3DRepository.deleteById(id);
    }

}
