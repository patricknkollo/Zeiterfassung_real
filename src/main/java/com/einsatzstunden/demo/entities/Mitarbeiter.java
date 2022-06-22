package com.einsatzstunden.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Component
public class Mitarbeiter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mitarbeiterid;
  private String name;
  private String vorname;
  @Column(length = 1)
  private String geschlecht;
  private Timestamp geburt;
  private String password;

  @JsonIgnore
  @OneToMany(
      mappedBy = "mitarbeiter",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Einsatz> einsatzList;

  public Mitarbeiter(Long mitarbeiterid, String name, String vorname, String geschlecht, Timestamp geburt,
      List<Einsatz> einsatzList) {
    this.mitarbeiterid = mitarbeiterid;
    this.name = name;
    this.vorname = vorname;
    this.geschlecht = geschlecht;
    this.geburt = geburt;
    this.einsatzList = einsatzList;
  }

  public Mitarbeiter(String name, String vorname, String geschlecht, Timestamp geburt) {
    this.name = name;
    this.vorname = vorname;
    this.geschlecht = geschlecht;
    this.geburt = geburt;
  }

  public Mitarbeiter(String name, String vorname, String geschlecht, Timestamp geburt,
      List<Einsatz> einsatzList) {
    this.name = name;
    this.vorname = vorname;
    this.geschlecht = geschlecht;
    this.geburt = geburt;
    this.einsatzList = einsatzList;
  }
  public Mitarbeiter(String name, String vorname, String geschlecht, Timestamp geburt, String password,
      List<Einsatz> einsatzList) {
    this.name = name;
    this.vorname = vorname;
    this.geschlecht = geschlecht;
    this.geburt = geburt;
    this.password = password;
    this.einsatzList = einsatzList;
  }

  public Mitarbeiter() {
  }

  public Long getMitarbeiterid() {
    return mitarbeiterid;
  }

  public void setMitarbeiterid(Long mitarbeiterId) {
    this.mitarbeiterid = mitarbeiterId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getGeschlecht() {
    return geschlecht;
  }

  public void setGeschlecht(String geschlecht) {
    this.geschlecht = geschlecht;
  }

  public Timestamp getGeburt() {
    return geburt;
  }

  public void setGeburt(Timestamp geburt) {
    this.geburt = geburt;
  }

  public List<Einsatz> getEinsatzList() {
    return einsatzList;
  }

  public void setEinsatzList(List<Einsatz> einsatzList) {
    this.einsatzList = einsatzList;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Mitarbeiter{" +
        "mitarbeiterid=" + mitarbeiterid +
        ", name='" + name + '\'' +
        ", vorname='" + vorname + '\'' +
        ", geschlecht='" + geschlecht + '\'' +
        ", geburt=" + geburt +
        ", password='" + password + '\'' +
        ", einsatzList=" + einsatzList +
        '}';
  }
}
