package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.*;
import de.cinema.backendp2cinema.enums.VorstellungsplatzStatus;
import de.cinema.backendp2cinema.exceptions.FilmNotFoundException;
import de.cinema.backendp2cinema.exceptions.KeineVorstellungZuFilmException;
import de.cinema.backendp2cinema.exceptions.VorstellungNotFoundException;
import de.cinema.backendp2cinema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VorstellungService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    VorstellungRepository vorstellungRepository;

    @Autowired
    PreisRepository preisRepository;

    @Autowired
    VorstellungsplatzRepository vorstellungsplatzRepository;

    @Autowired
    SitzplatzRepository sitzplatzRepository;

    //neue Vorstellung anlegen
    public ResponseEntity<Vorstellung> addNeueVorstellung(Vorstellung neueVorstellung){
        Vorstellung addedVorstellung = vorstellungRepository.save(neueVorstellung);

        //Zugehörige Vorstellungssitze nach Sitzplan erstellen
        Sitzplan sitzplan = neueVorstellung.getSaal().getSitzplan();
        List<Sitzplatz> sitzList = sitzplatzRepository.findAllBySitzplan(sitzplan).get();
        for (Sitzplatz sitz: sitzList){
            vorstellungsplatzAnlegen(neueVorstellung, sitz);
        }

        return new ResponseEntity<>(addedVorstellung, HttpStatus.CREATED);
    }


    private void vorstellungsplatzAnlegen(Vorstellung neueVorstellung, Sitzplatz sitz) {
        Preis preis = null;
        Optional<Preis> optionalPreis = preisRepository.findBySitzplatzkategorie(sitz.getSitzplatzkategorie());
        if(optionalPreis.isPresent()){
            preis = optionalPreis.get();
        }

        Vorstellungsplatz neuerVorstellungsplatz = new Vorstellungsplatz(sitz,
                neueVorstellung,
                VorstellungsplatzStatus.FREI,
                null,
                preis,
                null);

        vorstellungsplatzRepository.save(neuerVorstellungsplatz);
    }


    //Vorstellungen nach Filmen suchen
    public ResponseEntity<Object> getVorstellungByFilm(UUID filmId){

        //zuerst Film aus dem Repository suchen
        Film film;
        Optional<Film> filmOptional = filmRepository.findById(filmId);

        if(filmOptional.isPresent()) {
            film = filmOptional.get();
        }else{
            return new ResponseEntity<>(new FilmNotFoundException(filmId), HttpStatus.NOT_FOUND);
        }

        //Jede Vorstellung eines Films in ResponseEntity zurückgeben
        Optional<List<Vorstellung>> optionalFilmList = vorstellungRepository.findAllByFilm(film);
        if(optionalFilmList.isPresent()){
            return new ResponseEntity<>(optionalFilmList.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new KeineVorstellungZuFilmException(filmId).getMessage(), HttpStatus.NOT_FOUND);

    }

}
