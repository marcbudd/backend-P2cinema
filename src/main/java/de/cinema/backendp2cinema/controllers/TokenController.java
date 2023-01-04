package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Token;
import de.cinema.backendp2cinema.exceptions.TokenNotFoundException;
import de.cinema.backendp2cinema.exceptions.KinoNotFoundException;
import de.cinema.backendp2cinema.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    TokenRepository tokenRepository;

    @Autowired
    public TokenController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    //alle Tokens zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Token>> findAll(){
        Iterable<Token> tokene = tokenRepository.findAll();
        return new ResponseEntity<>(tokene, HttpStatus.OK);
    }

    //Token nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Token> suche = tokenRepository.findById(id);
        try{
            Token gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new TokenNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Token hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Token> save(@RequestBody Token newToken){
        Token addedToken = tokenRepository.save(newToken);
        return new ResponseEntity<>(addedToken, HttpStatus.CREATED);
    }

    //Token ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Token token) {
        Optional<Token> toUpdate = tokenRepository.findById(id);

        try {
            UUID tokenId = toUpdate.get().getId();
            token.setId(tokenId);
            tokenRepository.save(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Token nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Token> toDelete = tokenRepository.findById(id);
        try{
            UUID tokenId = toDelete.get().getId();
            tokenRepository.deleteById(tokenId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}

