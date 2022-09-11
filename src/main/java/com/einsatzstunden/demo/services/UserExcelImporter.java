package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserExcelImporter {


  public List<Mitarbeiter> excelImport(){
    List<Mitarbeiter> listma=new ArrayList<>();
    String name="";
    String vorname="";
    String geschlecht="";
    String geb= "";
    String password="";

    String excelFilePath="C:\\Users\\ndedi.patrick.nkollo\\excel-import.xlsx";

    long start = System.currentTimeMillis();

    FileInputStream inputStream;
    try {
      inputStream = new FileInputStream(excelFilePath);
      Workbook workbook=new XSSFWorkbook(inputStream);
      Sheet firstSheet=workbook.getSheetAt(0);
      Iterator<Row> rowIterator=firstSheet.iterator();
      rowIterator.next();

      while(rowIterator.hasNext()) {
        Row nextRow = rowIterator.next();
        Iterator<Cell> cellIterator=nextRow.cellIterator();
        while(cellIterator.hasNext()) {
          Cell nextCell=cellIterator.next();
          int columnIndex=nextCell.getColumnIndex();
          switch (columnIndex) {
          case 0:
            name=nextCell.getStringCellValue();
            System.out.println(name);
            break;
          case 1:
            vorname=nextCell.getStringCellValue();
            System.out.println(vorname);
            break;
          case 2:
            geschlecht=nextCell.getStringCellValue();
            System.out.println(geschlecht);
            break;
          case 3:
            geb=nextCell.getStringCellValue();
            System.out.println(geb);
            break;
          case 4:
            password=nextCell.getStringCellValue();
            System.out.println(password);
            break;
          }
        }
        listma.add(new Mitarbeiter(name, vorname, geschlecht, Timestamp.valueOf("2022-09-10 17:36:26"), Arrays.asList()));
      }

      workbook.close();
      long end = System.currentTimeMillis();
      System.out.printf("Import done in %d ms\n", (end - start));

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return listma;
  }

}

