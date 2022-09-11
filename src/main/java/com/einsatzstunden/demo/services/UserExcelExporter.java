package com.einsatzstunden.demo.services;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class UserExcelExporter {
  private XSSFWorkbook workbook;
  private XSSFSheet sheet;

  private List<Mitarbeiter> listMa;


  public UserExcelExporter(List<Mitarbeiter> listMitarbeiter) {
    this.listMa=listMitarbeiter;
    workbook = new XSSFWorkbook();

  }

  private void createCell(Row row,int columnCount, Object value, CellStyle style) {
    sheet.autoSizeColumn(columnCount);
    Cell cell=row.createCell(columnCount);
    if(value instanceof Long) {
      cell.setCellValue((Long) value);
    }else if(value instanceof Integer) {
      cell.setCellValue((Integer) value);
    }else if(value instanceof Boolean) {
      cell.setCellValue((Boolean) value);
    }else if(value instanceof Timestamp) {
      cell.setCellValue((Timestamp) value);
    }else {
      cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
  }
  private void writeHeaderLine() {
    sheet=workbook.createSheet("mitarbeiter-warmen");
// ---------------------------------------------------------------------------------------
    Row row = sheet.createRow(0);
    CellStyle style = workbook.createCellStyle();
    XSSFFont font=workbook.createFont();
    font.setBold(true);
    font.setFontHeight(20);
    style.setFont(font);
    style.setAlignment(HorizontalAlignment.CENTER);
    createCell(row,0,"MA Information",style);
    sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
    font.setFontHeightInPoints((short)(10));
// -----------------------------------------------------------------------------------------
    row=sheet.createRow(1);
    font.setBold(true);
    font.setFontHeight(16);
    style.setFont(font);
    createCell(row, 0, "mitarbeiter Id", style);
    createCell(row, 1, "Name", style);
    createCell(row, 2, "vorname", style);
    createCell(row, 3, "geschlecht(e)", style);
    createCell(row, 4, "geb", style);
    createCell(row, 5, "password", style);

  }

  private void writeDataLines() {
    int rowCount=2;

    CellStyle style=workbook.createCellStyle();
    XSSFFont font=workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);

    for(Mitarbeiter mitarbeiter:listMa) {
      Row row=sheet.createRow(rowCount++);
      int columnCount=0;
      createCell(row, columnCount++, mitarbeiter.getMitarbeiterid(), style);
      createCell(row, columnCount++, mitarbeiter.getName(), style);
      createCell(row, columnCount++, mitarbeiter.getVorname(), style);
      createCell(row, columnCount++, mitarbeiter.getGeschlecht(), style);
      createCell(row, columnCount++, mitarbeiter.getGeburt(), style);
      createCell(row, columnCount++, mitarbeiter.getPassword(), style);
    }
  }

  public void export(HttpServletResponse response) throws IOException {
    writeHeaderLine();
    writeDataLines();

    ServletOutputStream outputStream=response.getOutputStream();
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
  }


}
