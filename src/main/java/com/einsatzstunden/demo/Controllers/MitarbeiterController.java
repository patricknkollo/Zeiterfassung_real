package com.einsatzstunden.demo.Controllers;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.helper.EmailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.einsatzstunden.demo.services.MitarbeiterService;
import org.springframework.web.multipart.MultipartFile;
import projections.MitarbeiterFullnameProjection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/controller/mitarbeiter")
@CrossOrigin(origins = "http://localhost:3000")
public class MitarbeiterController {

  @Autowired
  private MitarbeiterService service;

  @Autowired
  private EmailHelper emailHelper;

  @RequestMapping(path = "/save", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<Mitarbeiter> saveMitarbeiter(@RequestBody Mitarbeiter mitarbeiter){
    return service.saveMitarbeiter(mitarbeiter);
  }
  @RequestMapping(path = "/all", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter(){
    return service.getAllMitarbeiter();
  }

  @RequestMapping(path = "/id/{thisid}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Optional<Mitarbeiter>>getMitarbeiterById(@PathVariable("thisid") Long id){
    return service.getMitarbeiterById(id);

  }
  @RequestMapping(path = "/id", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Optional<Mitarbeiter>>getMitarbeiterById1(@RequestParam Long id){
    return service.getMitarbeiterById(id);

  }
  @RequestMapping(path = "/name", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getMitarbeiterByName(@RequestParam String name){
    return service.getMitarbeiterByName(name);

  }
  @RequestMapping(path = "/vorname", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getMitarbeiterByVorname(@RequestParam String vorname){
    return service.getMitarbeiterByVorname(vorname);

  }
  @RequestMapping(path = "/geschlecht", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeschlecht(@RequestParam String geschlecht){
    return service.getMitarbeiterByGeschlecht(geschlecht);

  }
  @RequestMapping(path = "/geburt", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getMitarbeiterByGeburt(@RequestParam Timestamp geburt){
    return service.getMitarbeiterByGeburt(geburt);

  }
  @RequestMapping(path = "/zeiterfassung", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>>getMitarbeiterByEinsatz(@RequestParam Timestamp anfangzeit, @RequestParam Timestamp endezeit){
    return  service.getMitarbeiterByEinsatz(anfangzeit, endezeit);
  }
  @RequestMapping(path = "/update", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<Optional<Mitarbeiter>> UpdateMitarbeiter(@RequestParam Long id,@RequestBody Mitarbeiter mitarbeiter){
    return service.getMitarbeiterById(id);
  }

  @PostMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
  public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
    for (MultipartFile file : files) {
      service.saveUsers(file);
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(value = "/users", produces = "application/json")
  public CompletableFuture<ResponseEntity> findAllUsers() {
    return  service.findAllUsers().thenApply(ResponseEntity::ok);
  }
  @RequestMapping(value = "/sendemail")
  public String sendEmail()
  {
    emailHelper.sendEmail();
    return "Email sent successfully";
  }

  @RequestMapping(path = "/fullname", method = RequestMethod.GET)
  public ResponseEntity<List<MitarbeiterFullnameProjection>> findAllUsersFullname() {
    return  service.getMitarbeiterFullName();
  }
}
