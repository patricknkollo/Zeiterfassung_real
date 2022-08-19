package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.MitarbeiterEinsatzArbeitsplaztDto;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import projections.MitarbeiterEinsatzProjection;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class MitarbeiterEinsatzDtoService {

  @Autowired
  private MitarbeiterRepository repository;

  public ResponseEntity<List<MitarbeiterEinsatzProjection>> getAllMitarbeiterWithEinsatzAndWorkplace() {
    List<MitarbeiterEinsatzProjection> result = repository.findAllEmployeeEinsatzDto();
    return result != null
        ? new ResponseEntity<>(result, HttpStatus.FOUND)
        : new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
  }
  public List<MitarbeiterEinsatzArbeitsplaztDto> getAllMitarbeiterWithEinsatzAndWorkplaceDto(){
    List<MitarbeiterEinsatzProjection> result = repository.findAllEmployeeEinsatzDto();
    List<MitarbeiterEinsatzArbeitsplaztDto> mitarbeiterEinsatzArbeitsplaztDtos = new ArrayList<>();


    result.forEach(
        mitarbeiterEinsatzProjection -> {
          MitarbeiterEinsatzArbeitsplaztDto mitarbeiterEinsatzArbeitsplaztDto = new MitarbeiterEinsatzArbeitsplaztDto();
          mitarbeiterEinsatzArbeitsplaztDto.setMitarbeiterid(mitarbeiterEinsatzProjection.getMitarbeiterid());
          mitarbeiterEinsatzArbeitsplaztDto.setEinsatzid(mitarbeiterEinsatzProjection.getEinsatzid());
          mitarbeiterEinsatzArbeitsplaztDto.setName(mitarbeiterEinsatzProjection.getName());
          mitarbeiterEinsatzArbeitsplaztDto.setAnfangsZeit(mitarbeiterEinsatzProjection.getAnfangsZeit());
          mitarbeiterEinsatzArbeitsplaztDto.setEndeZeit(mitarbeiterEinsatzProjection.getEndeZeit());
          mitarbeiterEinsatzArbeitsplaztDto.setVorname(mitarbeiterEinsatzProjection.getVorname());

          mitarbeiterEinsatzArbeitsplaztDtos.add(mitarbeiterEinsatzArbeitsplaztDto);
        }
    );
    return mitarbeiterEinsatzArbeitsplaztDtos;
  }
  public String exportReport(String format) throws FileNotFoundException, JRException {
    String path = "C:\\Users\\ndedi.patrick.nkollo\\Downloads";
    List<MitarbeiterEinsatzArbeitsplaztDto> mitarbeiterEinsatzArbeitsplaztDtos = getAllMitarbeiterWithEinsatzAndWorkplaceDto();
    //load file and compile it
    File file = ResourceUtils.getFile("classpath:jasperreports/mitarbeiterEinsatzArbeitsplaztDto.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mitarbeiterEinsatzArbeitsplaztDtos);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdby", "me");

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (format.equalsIgnoreCase("html")) {
      JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
    }
    if (format.equalsIgnoreCase("pdf")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
    }
    return "report generated in path : " + path;
    //return "";
  }
}
