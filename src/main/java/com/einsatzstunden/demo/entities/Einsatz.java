package com.einsatzstunden.demo.entities;

import com.einsatzstunden.demo.helper.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonSerializer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
public class Einsatz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long einsatzid;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
  private Date anfangsZeit;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
  private Date endeZeit;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mitarbeiterid")
  //je definis le nom de la column qui va representer le tableau mitarbeiter dans le tableau einsatz avec @JoinColumn(name = "mitarbeiterid")
  private Mitarbeiter mitarbeiter;

  public Einsatz(Long einsatzid, Date anfangsZeit, Date endeZeit, Mitarbeiter mitarbeiter) {
    this.einsatzid = einsatzid;
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
    this.mitarbeiter = mitarbeiter;
  }

  public Einsatz(Date anfangsZeit, Date endeZeit, Mitarbeiter mitarbeiter) {
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
    this.mitarbeiter = mitarbeiter;
  }

  public Einsatz(Date anfangsZeit, Date endeZeit) {
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
  }

  public Einsatz() {
  }

  public Long getEinsatzid() {
    return einsatzid;
  }

  public void setEinsatzid(Long einsatzId) {
    this.einsatzid = einsatzId;
  }

  public Date getAnfangsZeit() {
    return anfangsZeit;
  }

  public void setAnfangsZeit(Date anfangsZeit) {
    this.anfangsZeit = anfangsZeit;
  }

  public Date getEndeZeit() {
    return endeZeit;
  }

  public void setEndeZeit(Date endeZeit) {
    this.endeZeit = endeZeit;
  }

  public Mitarbeiter getMitarbeiter() {
    return mitarbeiter;
  }

  public void setMitarbeiter(Mitarbeiter mitarbeiter) {
    this.mitarbeiter = mitarbeiter;
  }

  @Override
  public String toString() {
    return "Einsatz{" +
        "einsatzId=" + einsatzid +
        ", anfangsZeit=" + anfangsZeit +
        ", endeZeit=" + endeZeit +
        ", mitarbeiter=" + mitarbeiter +
        '}';
  }
}
