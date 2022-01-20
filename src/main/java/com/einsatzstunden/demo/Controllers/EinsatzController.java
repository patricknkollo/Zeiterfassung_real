package com.einsatzstunden.demo.Controllers;

import com.einsatzstunden.demo.entities.Einsatz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.einsatzstunden.demo.services.EinsatzService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Component
@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping(path = "/api/controller/einsatz")
public class EinsatzController {

  @Autowired
  EinsatzService service;
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<Einsatz> saveEinsatz(@RequestBody Einsatz einsatz){
    return service.saveEinsatz(einsatz);
  }
  @RequestMapping(path = "/all", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Einsatz>> getAllEinsatz(){
     return service.getAllEinsatz();
  }

  @RequestMapping(path = "/id/{thisid}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Optional<Einsatz>> getAllEinsatzById(@PathVariable("thisid") Long id){
    return service.getAllEinsatzById(id);
  }
  @RequestMapping(path = "/anfangszeit", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Einsatz>> getAllEinsatzByAnfangszeit(@RequestParam Timestamp anfangszeit){
    return service.getAllEinsatzByAnfangszeit(anfangszeit);
  }
  @RequestMapping(path = "/endezeit", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<Einsatz>> getAllEinsatzByEndezeit(@RequestParam Timestamp endezeit){
    return service.getAllEinsatzByEndezeit(endezeit);
  }
  @RequestMapping(path = "/update", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<Einsatz>UpdateEinsatz(@RequestParam Long id, @RequestBody Einsatz einsatz){
    return service.UpdateEinsatz(id, einsatz);
  }
  @RequestMapping(path = "/delete/{thisid}", method = RequestMethod.DELETE)
  public void deleteEinsatz(@PathVariable("thisid") Long id){
    service.deleteEinsatz(id);
  }
}
