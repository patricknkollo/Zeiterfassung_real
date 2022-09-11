package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.MitarbeiterEinsatzArbeitsplatzDto;
import com.einsatzstunden.demo.entities.MitarbeiterEinsatzArbeitsplatzReportDto;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import projections.MitarbeiterEinsatzProjection;

import java.io.File;
import java.io.FileNotFoundException;
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
  public List<MitarbeiterEinsatzArbeitsplatzDto> getAllMitarbeiterWithEinsatzAndWorkplaceDto(){
    List<MitarbeiterEinsatzProjection> result = repository.findAllEmployeeEinsatzDto();
    List<MitarbeiterEinsatzArbeitsplatzDto> mitarbeiterEinsatzArbeitsplaztDtos = new ArrayList<>();


    result.forEach(
        mitarbeiterEinsatzProjection -> {
          MitarbeiterEinsatzArbeitsplatzDto mitarbeiterEinsatzArbeitsplaztDto = new MitarbeiterEinsatzArbeitsplatzDto();
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

  public List<MitarbeiterEinsatzArbeitsplatzReportDto> getAllMitarbeiterWithEinsatzAndWorkplaceForReportDto(){
    List<MitarbeiterEinsatzProjection> result = repository.findAllEmployeeEinsatzDto();
    List<MitarbeiterEinsatzArbeitsplatzReportDto> mitarbeiterEinsatzArbeitsplatzDtos = new ArrayList<>();


    result.forEach(
        mitarbeiterEinsatzProjection -> {
          MitarbeiterEinsatzArbeitsplatzReportDto mitarbeiterEinsatzArbeitsplatzDto = new MitarbeiterEinsatzArbeitsplatzReportDto();
          mitarbeiterEinsatzArbeitsplatzDto.setMitarbeiterid(mitarbeiterEinsatzProjection.getMitarbeiterid().toString());
          mitarbeiterEinsatzArbeitsplatzDto.setEinsatzid(mitarbeiterEinsatzProjection.getEinsatzid().toString());
          mitarbeiterEinsatzArbeitsplatzDto.setName(mitarbeiterEinsatzProjection.getName());
          mitarbeiterEinsatzArbeitsplatzDto.setAnfangsZeit(mitarbeiterEinsatzProjection.getAnfangsZeit().toString());
          mitarbeiterEinsatzArbeitsplatzDto.setEndeZeit(mitarbeiterEinsatzProjection.getEndeZeit().toString());
          mitarbeiterEinsatzArbeitsplatzDto.setVorname(mitarbeiterEinsatzProjection.getVorname());

          mitarbeiterEinsatzArbeitsplatzDtos.add(mitarbeiterEinsatzArbeitsplatzDto);
        }
    );
    return mitarbeiterEinsatzArbeitsplatzDtos;
  }
  public String exportReport(String format) throws FileNotFoundException, JRException {
    String path = "C:\\Users\\ndedi.patrick.nkollo\\Downloads";
    List<MitarbeiterEinsatzArbeitsplatzReportDto> mitarbeiterEinsatzArbeitsplatzDtos = getAllMitarbeiterWithEinsatzAndWorkplaceForReportDto();
    //load file and compile it
    File file = ResourceUtils.getFile("classpath:jasperreports/SimpleBlue.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mitarbeiterEinsatzArbeitsplatzDtos);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdby", "me");

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (format.equalsIgnoreCase("html")) {
      JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
    }
    if (format.equalsIgnoreCase("pdf")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
    }
    if (format.equalsIgnoreCase("xls")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
      JRXlsxExporter exporter = new JRXlsxExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "employees.pdf");
      exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

      exporter.exportReport();
    }
    return "report generated in path : " + path;
    //return "";
  }

  public String exportReport2(String format) throws FileNotFoundException, JRException {
    String path = "C:\\Users\\ndedi.patrick.nkollo\\Downloads";
    List<MitarbeiterEinsatzArbeitsplatzReportDto> mitarbeiterEinsatzArbeitsplatzDtos = getAllMitarbeiterWithEinsatzAndWorkplaceForReportDto();
    //load file and compile it
    File file = ResourceUtils.getFile("classpath:jasperreports/Cherry.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mitarbeiterEinsatzArbeitsplatzDtos);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdby", "me");

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (format.equalsIgnoreCase("html")) {
      JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees_report.html");
    }
    if (format.equalsIgnoreCase("pdf")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees_report.pdf");
    }
    return "report generated in path : " + path;
    //return "";
  }
}
