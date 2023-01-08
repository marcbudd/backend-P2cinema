package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNichtFreiException;
import de.cinema.backendp2cinema.enums.VorstellungsplatzStatus;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNichtReserviertException;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNotFoundException;
import de.cinema.backendp2cinema.repositories.TicketRepository;
import de.cinema.backendp2cinema.repositories.VorstellungRepository;
import de.cinema.backendp2cinema.repositories.VorstellungsplatzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.Semaphore;

@Service
public class VorstellungsplatzService {

    @Autowired
    VorstellungsplatzRepository vorstellungsplatzRepository;

    @Autowired
    VorstellungRepository vorstellungRepository;

    @Autowired
    TicketRepository ticketRepository;

    Semaphore mySemaphore;

    public VorstellungsplatzService() {
        mySemaphore = new Semaphore(1, true);
    }


    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "reserviert", sofern er noch nicht reserviert ist
    public ResponseEntity<Object> reserviereSitz(UUID vplatzId) {
        try {
            mySemaphore.acquire();

            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            if(vplatz.getStatus() == VorstellungsplatzStatus.FREI){
                vplatz.setStatus(VorstellungsplatzStatus.RESERVIERT);
                vorstellungsplatzRepository.save(vplatz);
                return new ResponseEntity<>(vplatz, HttpStatus.OK);
            }
            return new ResponseEntity<>(new VorstellungsplatzNichtFreiException(vplatzId), HttpStatus.BAD_REQUEST);

        }catch(InterruptedException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }catch(NoSuchElementException f){
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(vplatzId), HttpStatus.NOT_FOUND);
        }finally {
            mySemaphore.release();
        }
    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "frei"
    public ResponseEntity<Object> befreieSitz(UUID vplatzId){

        try {
            mySemaphore.acquire();
            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            vplatz.setStatus(VorstellungsplatzStatus.FREI);
            vorstellungsplatzRepository.save(vplatz);
            return new ResponseEntity<>(vplatz, HttpStatus.OK);

        }catch(InterruptedException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }catch(NoSuchElementException f){
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(vplatzId).getMessage(), HttpStatus.NOT_FOUND);
        }finally {
            mySemaphore.release();
        }

    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "blockiert"
    public ResponseEntity<Object> blockiereSitz(UUID vplatzId){

        try {
            mySemaphore.acquire();
            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            vplatz.setStatus(VorstellungsplatzStatus.BLOCKIERT);
            vorstellungsplatzRepository.save(vplatz);
            return new ResponseEntity<>(vplatz, HttpStatus.OK);
        }catch(InterruptedException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }catch(NoSuchElementException f){
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(vplatzId).getMessage(), HttpStatus.NOT_FOUND);
        }finally {
            mySemaphore.release();
        }
    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "gebucht", falls er vorher reserviert war
    public ResponseEntity<Object> bucheSitz(UUID vplatzId){
        try {
            mySemaphore.acquire();

            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            if(vplatz.getStatus() == VorstellungsplatzStatus.RESERVIERT){
                vplatz.setStatus(VorstellungsplatzStatus.GEBUCHT);
                vorstellungsplatzRepository.save(vplatz);
                return new ResponseEntity<>(vplatz, HttpStatus.OK);
            }

            return new ResponseEntity<>(new VorstellungsplatzNichtReserviertException(vplatzId).getMessage(), HttpStatus.BAD_REQUEST);

        }catch(InterruptedException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }catch(NoSuchElementException f){
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(vplatzId).getMessage(), HttpStatus.NOT_FOUND);
        }finally {
            mySemaphore.release();
        }
    }

}
