package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.Einsatz;
import com.einsatzstunden.demo.repositories.EinsatzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class EinsatzService {

  @Autowired
  EinsatzRepository repository;

  public ResponseEntity<Einsatz> saveEinsatz(Einsatz einsatz){
    Einsatz einsatz1= repository.save(einsatz);
    ResponseEntity<Einsatz>responseEntity=new ResponseEntity<>(einsatz1, HttpStatus.CREATED);
    return einsatz1!=null
        ? responseEntity
        :new ResponseEntity<>(einsatz1, HttpStatus.NOT_ACCEPTABLE);
  }
  public ResponseEntity<List<Einsatz>> getAllEinsatz(){
    List<Einsatz>einsatzList= repository.findAll();
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList,HttpStatus.OK);
    return einsatzList!=null
        ? responseEntity
        :new ResponseEntity<List<Einsatz>>(einsatzList, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<Optional<Einsatz>> getAllEinsatzById(Long id){
    Optional<Einsatz> einsatz= repository.findById(id);
    ResponseEntity<Optional<Einsatz>> responseEntity = new ResponseEntity<>(einsatz,HttpStatus.FOUND);
    return einsatz!=null
        ? responseEntity
        :new ResponseEntity<Optional<Einsatz>>(einsatz, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Einsatz>> getAllEinsatzByAnfangszeit(Timestamp anfangszeit){
    List<Einsatz> einsatzlist= repository.getEinsatzByAnfangsZeit(anfangszeit);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzlist,HttpStatus.FOUND);
    return einsatzlist!=null
        ? responseEntity
        :new ResponseEntity<List<Einsatz>>(einsatzlist, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<List<Einsatz>> getAllEinsatzByEndezeit(Timestamp endezeit){
    List<Einsatz> einsatzlist= repository.getEinsatzByEndeZeit(endezeit);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzlist,HttpStatus.FOUND);
    return einsatzlist!=null
        ? responseEntity
        :new ResponseEntity<List<Einsatz>>(einsatzlist, HttpStatus.NOT_FOUND);
  }
  public ResponseEntity<Einsatz>UpdateEinsatz(Long id, Einsatz einsatz){
    Optional<Einsatz>optionalEinsatz= repository.findById(id);
    if(optionalEinsatz.isPresent()){
      optionalEinsatz.get().setAnfangsZeit(einsatz.getAnfangsZeit());
      optionalEinsatz.get().setEndeZeit(einsatz.getEndeZeit());
      optionalEinsatz.get().setMitarbeiter(einsatz.getMitarbeiter());
      Einsatz result = repository.save(optionalEinsatz.get());
      ResponseEntity<Einsatz>responseEntity = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
      return  responseEntity;
    }
    return new ResponseEntity<>(optionalEinsatz.get(),HttpStatus.NOT_MODIFIED);
  }
  public void deleteEinsatz(Long id){
    repository.deleteById(id);
  }

  public ResponseEntity<List<Einsatz>> getEinsaetzeOfMitarbeiter(Long id){
    List<Einsatz> einsatzList = repository.getEinsatzByMitarbeiterId(id);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.OK);
    return responseEntity;
  }
}
