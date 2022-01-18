package Controllers;

import entities.Mitarbeiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.MitarbeiterService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@Component
@RequestMapping(path = "/api/controller/mitarbeiter")
@CrossOrigin(origins = "http://localhost:3000")
public class MitarbeiterController {

  @Autowired
  MitarbeiterService service;
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<Mitarbeiter> saveMitarbeiter(@RequestBody Mitarbeiter mitarbeiter){
    return service.saveMitarbeiter(mitarbeiter);
  }
  @RequestMapping(path = "/all", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter(){
    return service.getAllMitarbeiter();

  }
  @RequestMapping(path = "/id", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Optional<Mitarbeiter>>getMitarbeiterById(@RequestParam Long id){
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
}
