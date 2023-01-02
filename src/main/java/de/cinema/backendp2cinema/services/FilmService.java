package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.exceptions.FilmNotFoundException;
import de.cinema.backendp2cinema.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmService {

    //Alternativ: FilmService Instanz kann in der zugehörigen KontrollerInstanz genutzt werden
//    private final FilmRepository filmRepository;
//
//    @Autowired
//    public FilmService(FilmRepository filmRepository){
//        this.filmRepository = filmRepository;
//    }
//
//    public Film addFilm(Film film) {
//        return filmRepository.save(film);
//
//    }
//
//    public List<Film> getAllFilme() {
//        return (List<Film>) filmRepository.findAll();
//    }
//
//    public Film findFilmById(UUID id) {
//        return filmRepository.findById(id).orElseThrow(()-> new FilmNotFoundException(id));
//    }
//
//    public void delete(UUID id) {
//        filmRepository.deleteById(id);
//    }
}