package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.ShowSeat;
import de.cinema.backendp2cinema.exceptions.ShowSeatNotFoundException;
import de.cinema.backendp2cinema.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowSeatService {

    private final ShowSeatRepository ShowSeatRepository;

    @Autowired
    public ShowSeatService(ShowSeatRepository ShowSeatRepository){
        this.ShowSeatRepository = ShowSeatRepository;
    }

    public ShowSeat addShowSeat(ShowSeat ShowSeat) {
        return ShowSeatRepository.save(ShowSeat);

    }

    public List<ShowSeat> getAllShowSeats() {
        return (List<ShowSeat>) ShowSeatRepository.findAll();
    }

    public ShowSeat findShowSeatById(UUID id) {
        return ShowSeatRepository.findById(id).orElseThrow(()-> new ShowSeatNotFoundException("ShowSeat with id " + id + " not found"));
    }

    public void delete(UUID id) {
        ShowSeatRepository.deleteById(id);
    }


}
