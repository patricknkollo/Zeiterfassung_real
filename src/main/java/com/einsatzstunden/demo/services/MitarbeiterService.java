package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class MitarbeiterService {
  @Autowired
  MitarbeiterRepository repository;

  public ResponseEntity<Mitarbeiter> saveMitarbeiter(Mitarbeiter mitarbeiter){
    Mitarbeiter result = repository.save(mitarbeiter);
    ResponseEntity<Mitarbeiter>responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
    return result!=null
        ? responseEntity
        :new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
  }

  public ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter(){
    List<Mitarbeiter>mitarbeiters = repository.findAll();
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.OK);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<Optional<Mitarbeiter>>getMitarbeiterById(Long id){
    Optional<Mitarbeiter>mitarbeiter = repository.findById(id);
    ResponseEntity<Optional<Mitarbeiter>>responseEntity = new ResponseEntity<>(mitarbeiter, HttpStatus.FOUND);
    return mitarbeiter!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiter, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByName(String name){
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByName(name);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByVorname(String vorname){
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByVorname(vorname);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeschlecht(String geschlecht){
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByGeschlecht(geschlecht);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeburt(Timestamp geburt){
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByGeburt(geburt);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Mitarbeiter>>getMitarbeiterByEinsatz(Timestamp anfangzeit, Timestamp endezeit){
    List<Mitarbeiter>mitarbeiters= repository.getMitarbeiterBySpecificEinsatz(anfangzeit, endezeit);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters!=null
        ? responseEntity
        :new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<Optional<Mitarbeiter>> UpdateMitarbeiter(Long id, Mitarbeiter mitarbeiter){
    Optional<Mitarbeiter>mitarbeiterOptional =repository.findById(id);
    if(mitarbeiterOptional.isPresent()){
      mitarbeiterOptional.get().setEinsatzList(mitarbeiter.getEinsatzList());
      mitarbeiterOptional.get().setGeburt(mitarbeiter.getGeburt());
      mitarbeiterOptional.get().setGeschlecht(mitarbeiter.getGeschlecht());
      mitarbeiterOptional.get().setName(mitarbeiter.getName());
      mitarbeiterOptional.get().setVorname(mitarbeiter.getVorname());
      Mitarbeiter result = repository.save(mitarbeiterOptional.orElse(null));
      ResponseEntity<Optional<Mitarbeiter>> responseEntity = new ResponseEntity<>(Optional.of(result), HttpStatus.ACCEPTED);
      return  responseEntity;
    }
    return  new ResponseEntity<>(mitarbeiterOptional, HttpStatus.NOT_MODIFIED);
  }
}
