package com.einsatzstunden.demo.Controllers;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;
import com.einsatzstunden.demo.services.UserExcelExporter;
import com.einsatzstunden.demo.services.UserExcelImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/web")
public class XLSExportController {

  @Autowired
  private MitarbeiterRepository repository;

  @GetMapping("/export/excel")
  public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headervalue = "attachment; filename=mitarbeiter_info.xlsx";

    response.setHeader(headerKey, headervalue);
    List<Mitarbeiter> listMitarbeiter = repository.findAll();
    UserExcelExporter exp = new UserExcelExporter(listMitarbeiter);
    exp.export(response);

  }

  @RequestMapping("/import/excel")
  @ResponseBody
  public String importFromExcel() {
    UserExcelImporter excelImporter=new UserExcelImporter();
    List<Mitarbeiter> listMitarbeiter= excelImporter.excelImport();
    repository.saveAll(listMitarbeiter);
    return "Import Successfully";
  }

}