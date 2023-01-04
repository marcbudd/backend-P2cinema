package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import de.cinema.backendp2cinema.enums.VorstellungsplatzStatus;
import de.cinema.backendp2cinema.repositories.TicketRepository;
import de.cinema.backendp2cinema.repositories.VorstellungRepository;
import de.cinema.backendp2cinema.repositories.VorstellungsplatzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.Semaphore;

@Service
public class ReservierungService {

    @Autowired
    VorstellungsplatzRepository vorstellungsplatzRepository;

    @Autowired
    VorstellungRepository vorstellungRepository;

    @Autowired
    TicketRepository ticketRepository;

    Semaphore mySemaphore;

    public ReservierungService() {
        mySemaphore = new Semaphore(1, true);
    }


    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "reserviert", sofern er noch nicht reserviert ist
    public boolean reserviereSitz(UUID vplatzId) {
        boolean erfolg = false;

        try {
            mySemaphore.acquire();

            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            if(vplatz.getStatus() == VorstellungsplatzStatus.FREI){
                vplatz.setStatus(VorstellungsplatzStatus.RESERVIERT);
                vorstellungsplatzRepository.save(vplatz);
                erfolg = true;
            }

        } catch (InterruptedException | NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            mySemaphore.release();
        }

        return erfolg;
    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "frei"
    public boolean befreieSitz(UUID vplatzId){
        boolean erfolg = false;

        try {
            mySemaphore.acquire();
            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            vplatz.setStatus(VorstellungsplatzStatus.FREI);
            vorstellungsplatzRepository.save(vplatz);
            erfolg = true;
        }catch(InterruptedException | NoSuchElementException e){
            e.printStackTrace();
        } finally {
            mySemaphore.release();
        }

        return true;

    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "blockiert"
    public boolean blockiereSitz(UUID vplatzId){
        boolean erfolg = false;

        try {
            mySemaphore.acquire();
            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            vplatz.setStatus(VorstellungsplatzStatus.BLOCKIERT);
            vorstellungsplatzRepository.save(vplatz);
            erfolg = true;
        }catch(InterruptedException | NoSuchElementException e){
            e.printStackTrace();
        } finally {
            mySemaphore.release();
        }

        return true;
    }

    //Diese Methode setzt den Status eines Vorstellungsplatzes mit der ID vplatzId auf "gebucht", falls er vorher reserviert war
    public boolean bucheSitz(UUID vplatzId){
        boolean erfolg = false;

        try {
            mySemaphore.acquire();

            Vorstellungsplatz vplatz = vorstellungsplatzRepository.findById(vplatzId).get();
            if(vplatz.getStatus() == VorstellungsplatzStatus.RESERVIERT){
                vplatz.setStatus(VorstellungsplatzStatus.GEBUCHT);
                vorstellungsplatzRepository.save(vplatz);
                erfolg = true;
            }

        } catch (InterruptedException | NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            mySemaphore.release();
        }

        return erfolg;
    }

}
