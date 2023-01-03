package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Show;
import de.cinema.backendp2cinema.exceptions.ShowNotFoundException;
import de.cinema.backendp2cinema.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private final ShowRepository ShowRepository;

    @Autowired
    public ShowService(ShowRepository ShowRepository){
        this.ShowRepository = ShowRepository;
    }

    public Show addShow(Show Show) {
        return ShowRepository.save(Show);

    }

    public List<Show> getAllShows() {
        return (List<Show>) ShowRepository.findAll();
    }

    public Show findShowById(UUID id) {
        return ShowRepository.findById(id).orElseThrow(()-> new ShowNotFoundException("Show with id " + id + " not found"));
    }

    public void delete(UUID id) {
        ShowRepository.deleteById(id);
    }


}
