package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.Einsatz;
import com.einsatzstunden.demo.entities.Mitarbeiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import projections.MitarbeiterFullnameProjection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Component
public class MitarbeiterService {

  Logger logger = LoggerFactory.getLogger(MitarbeiterService.class);
  @Autowired
  MitarbeiterRepository repository;

  @Transactional
  public ResponseEntity<Mitarbeiter> saveMitarbeiter(Mitarbeiter mitarbeiter) {
    Mitarbeiter result = repository.save(mitarbeiter);
    ResponseEntity<Mitarbeiter> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
    return result != null
        ? responseEntity
        : new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
  }

  @Transactional
  public ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter() {
    List<Mitarbeiter> mitarbeiters = repository.findAll();
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.OK);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  @Transactional
  public ResponseEntity<Optional<Mitarbeiter>> getMitarbeiterById(Long id) {
    Optional<Mitarbeiter> mitarbeiter = repository.findById(id);
    ResponseEntity<Optional<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiter, HttpStatus.FOUND);
    return mitarbeiter != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiter, HttpStatus.NOT_FOUND);
  }

  @Transactional
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByName(String name) {
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByName(name);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  @Transactional
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByVorname(String vorname) {
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByVorname(vorname);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  @Transactional
  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeschlecht(String geschlecht) {
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByGeschlecht(geschlecht);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeburt(Timestamp geburt) {
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterByGeburt(geburt);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Mitarbeiter>> getMitarbeiterByEinsatz(Timestamp anfangzeit, Timestamp endezeit) {
    List<Mitarbeiter> mitarbeiters = repository.getMitarbeiterBySpecificEinsatz(anfangzeit, endezeit);
    ResponseEntity<List<Mitarbeiter>> responseEntity = new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND);
    return mitarbeiters != null
        ? responseEntity
        : new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }

  @Transactional
  public ResponseEntity<Optional<Mitarbeiter>> UpdateMitarbeiter(Long id, Mitarbeiter mitarbeiter) {
    Optional<Mitarbeiter> mitarbeiterOptional = repository.findById(id);
    if (mitarbeiterOptional.isPresent()) {
      mitarbeiterOptional.get().setEinsatzList(mitarbeiter.getEinsatzList());
      mitarbeiterOptional.get().setGeburt(mitarbeiter.getGeburt());
      mitarbeiterOptional.get().setGeschlecht(mitarbeiter.getGeschlecht());
      mitarbeiterOptional.get().setName(mitarbeiter.getName());
      mitarbeiterOptional.get().setVorname(mitarbeiter.getVorname());
      Mitarbeiter result = repository.save(mitarbeiterOptional.orElse(null));
      ResponseEntity<Optional<Mitarbeiter>> responseEntity = new ResponseEntity<>(Optional.of(result),
          HttpStatus.ACCEPTED);
      return responseEntity;
    }
    return new ResponseEntity<>(mitarbeiterOptional, HttpStatus.NOT_MODIFIED);
  }

  @Transactional
  public ResponseEntity<List<Einsatz>> getAllValideEinsaetzeOfMitarbeiter(Long id) {
    List<Einsatz> einsatzList = repository.findById(id)
        .get()
        .getEinsatzList()
        .stream()
        .filter(einsatz -> einsatz.getAnfangsZeit().getTime() != einsatz.getEndeZeit().getTime())
        .collect(Collectors.toList());
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.FOUND);
    return responseEntity;
  }

  @Transactional
  public ResponseEntity<List<Einsatz>> getAllInvalideEinsaetzeOfMitarbeiter(Long id) {
    List<Einsatz> einsatzList = repository.findById(id)
        .get()
        .getEinsatzList()
        .stream()
        .filter(einsatz -> einsatz.getAnfangsZeit().getTime() == einsatz.getEndeZeit().getTime())
        .collect(Collectors.toList());
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.FOUND);
    return responseEntity;
  }

  @Async
  public CompletableFuture<List<Mitarbeiter>> findAllUsers() {
    logger.info("get list of user by " + Thread.currentThread().getName());
    List<Mitarbeiter> users = repository.findAll();
    return CompletableFuture.completedFuture(users);
  }

  @Async
  public CompletableFuture<List<Mitarbeiter>> saveUsers(MultipartFile file) throws Exception {
    long start = System.currentTimeMillis();
    List<Mitarbeiter> users = parseCSVFile(file);
    logger.info("saving list of users of size {}", users.size(), "" + Thread.currentThread().getName());
    users = repository.saveAll(users);
    long end = System.currentTimeMillis();
    logger.info("Total time {}", (end - start));
    return CompletableFuture.completedFuture(users);
  }

  private List<Mitarbeiter> parseCSVFile(final MultipartFile file) throws Exception {
    final List<Mitarbeiter> mitarbeiters = new ArrayList<>();
    try {
      try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String line;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while ((line = br.readLine()) != null) {
          final String[] data = line.split(",");
          final Mitarbeiter mitarbeiter = new Mitarbeiter();
          mitarbeiter.setName(data[0]);
          mitarbeiter.setVorname(data[1]);
          mitarbeiter.setGeschlecht(data[2]);
          mitarbeiter.setGeburt(new Timestamp(simpleDateFormat.parse(data[3]).getTime()));
          mitarbeiters.add(mitarbeiter);
        }
        return mitarbeiters;
      }
    } catch (final Exception e) {
      logger.error("Failed to parse CSV file {}", e);
      throw new Exception("Failed to parse CSV file {}", e);
    }
  }

  @Transactional
  public ResponseEntity<List<MitarbeiterFullnameProjection>> getMitarbeiterFullName(){
  List<MitarbeiterFullnameProjection>mitarbeiters = repository.findMitarbeiterFullName();
  return mitarbeiters !=  null ?
     new ResponseEntity<>(mitarbeiters, HttpStatus.FOUND):
         new ResponseEntity<>(mitarbeiters, HttpStatus.NOT_FOUND);
  }
}
