package com.einsatzstunden.demo.Controllers;

import com.einsatzstunden.demo.entities.MitarbeiterEinsatzArbeitsplatzDto;
import com.einsatzstunden.demo.entities.MitarbeiterEinsatzArbeitsplatzReportDto;
import com.einsatzstunden.demo.services.MitarbeiterEinsatzDtoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import projections.MitarbeiterEinsatzProjection;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@Component
@RequestMapping(path = "/api/mitarbeiterEinsatzDtoController")
@CrossOrigin(origins = "http://localhost:3000")
public class MitarbeiterEinsatzDtoController {

  @Autowired
  private MitarbeiterEinsatzDtoService service;

  @RequestMapping(path = "/all", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<MitarbeiterEinsatzProjection>> getAllMitarbeiterEinsatzArbeitsplazt(){
    return service.getAllMitarbeiterWithEinsatzAndWorkplace();
  }

  @RequestMapping(path = "/dto/all", method = RequestMethod.GET)
  public @ResponseBody List<MitarbeiterEinsatzArbeitsplatzDto> getAllMitarbeiterEinsatzArbeitsplaztDto(){
    return service.getAllMitarbeiterWithEinsatzAndWorkplaceDto();
  }
  @RequestMapping(path = "/dto/all/report/{format}", method = RequestMethod.GET)
  public String getAllMitarbeiterEinsatzArbeitsplaztDtoReport(@PathVariable("format") String exportFormat)
      throws JRException, FileNotFoundException {
    return service.exportReport(exportFormat);
  }
  @RequestMapping(path = "/dto/all/report2/{format}", method = RequestMethod.GET, produces = {"application/pdf"})
  public String getAllMitarbeiterEinsatzArbeitsplaztDtoReport2(@PathVariable("format") String exportFormat)
      throws JRException, FileNotFoundException {
    return service.exportReport2(exportFormat);
  }
}
