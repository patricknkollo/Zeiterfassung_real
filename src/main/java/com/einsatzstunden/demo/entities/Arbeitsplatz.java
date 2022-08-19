
package com.einsatzstunden.demo.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
public class Arbeitsplatz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mitarbeiterid;
  private String arbeitsplatzName;
  private String strasse;
  private String strasseNumber;
  private String plz;

  public Arbeitsplatz(String arbeitsplatzName, String strasse, String strasseNumber, String plz) {
    this.arbeitsplatzName = arbeitsplatzName;
    this.strasse = strasse;
    this.strasseNumber = strasseNumber;
    this.plz = plz;
  }

  public Arbeitsplatz() {
  }

  public Long getMitarbeiterid() {
    return mitarbeiterid;
  }

  public void setMitarbeiterid(Long mitarbeiterid) {
    this.mitarbeiterid = mitarbeiterid;
  }

  public String getArbeitsplatzName() {
    return arbeitsplatzName;
  }

  public void setArbeitsplatzName(String arbeitsplatzName) {
    this.arbeitsplatzName = arbeitsplatzName;
  }

  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  public String getStrasseNumber() {
    return strasseNumber;
  }

  public void setStrasseNumber(String strasseNumber) {
    this.strasseNumber = strasseNumber;
  }

  public String getPlz() {
    return plz;
  }

  public void setPlz(String plz) {
    this.plz = plz;
  }

  @Override
  public String toString() {
    return "Arbeitsplatz{" +
        "mitarbeiterid=" + mitarbeiterid +
        ", arbeitsplatzName='" + arbeitsplatzName + '\'' +
        ", strasse='" + strasse + '\'' +
        ", strasseNumber='" + strasseNumber + '\'' +
        ", plz='" + plz + '\'' +
        '}';
  }
}

