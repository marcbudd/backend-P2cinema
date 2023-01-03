package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Seat_Category;
import de.cinema.backendp2cinema.exceptions.Seat_CategoryNotFoundException;
import de.cinema.backendp2cinema.repositories.Seat_CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Seat_CategoryService {

    private final Seat_CategoryRepository Seat_CategoryRepository;

    @Autowired
    public Seat_CategoryService(Seat_CategoryRepository Seat_CategoryRepository){
        this.Seat_CategoryRepository = Seat_CategoryRepository;
    }

    public Seat_Category addSeat_Category(Seat_Category Seat_Category) {
        return Seat_CategoryRepository.save(Seat_Category);

    }

    public List<Seat_Category> getAllSeat_Categorys() {
        return (List<Seat_Category>) Seat_CategoryRepository.findAll();
    }

    public Seat_Category findSeat_CategoryById(UUID id) {
        return Seat_CategoryRepository.findById(id).orElseThrow(()-> new Seat_CategoryNotFoundException("Seat_Category with id " + id + " not found"));
    }

    public void delete(UUID id) {
        Seat_CategoryRepository.deleteById(id);
    }


}
