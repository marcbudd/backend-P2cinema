package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Rate;
import de.cinema.backendp2cinema.exceptions.RateNotFoundException;
import de.cinema.backendp2cinema.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RateService {

    private final RateRepository RateRepository;

    @Autowired
    public RateService(RateRepository RateRepository){
        this.RateRepository = RateRepository;
    }

    public Rate addRate(Rate Rate) {
        return RateRepository.save(Rate);

    }

    public List<Rate> getAllRates() {
        return (List<Rate>) RateRepository.findAll();
    }

    public Rate findRateById(UUID id) {
        return RateRepository.findById(id).orElseThrow(()-> new RateNotFoundException("Rate with id " + id + " not found"));
    }

    public void delete(UUID id) {
        RateRepository.deleteById(id);
    }


}
