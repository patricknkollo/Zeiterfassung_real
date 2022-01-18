package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Component
public class Einsatz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long einsatzId;
  private Timestamp anfangsZeit;
  private Timestamp endeZeit;
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mitarbeiter_id")
  private Mitarbeiter mitarbeiter;

  public Einsatz(Long einsatzId, Timestamp anfangsZeit, Timestamp endeZeit, Mitarbeiter mitarbeiter) {
    this.einsatzId = einsatzId;
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
    this.mitarbeiter = mitarbeiter;
  }

  public Einsatz(Timestamp anfangsZeit, Timestamp endeZeit, Mitarbeiter mitarbeiter) {
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
    this.mitarbeiter = mitarbeiter;
  }

  public Einsatz(Timestamp anfangsZeit, Timestamp endeZeit) {
    this.anfangsZeit = anfangsZeit;
    this.endeZeit = endeZeit;
  }

  public Einsatz() {
  }

  public Long getEinsatzId() {
    return einsatzId;
  }

  public void setEinsatzId(Long einsatzId) {
    this.einsatzId = einsatzId;
  }

  public Timestamp getAnfangsZeit() {
    return anfangsZeit;
  }

  public void setAnfangsZeit(Timestamp anfangsZeit) {
    this.anfangsZeit = anfangsZeit;
  }

  public Timestamp getEndeZeit() {
    return endeZeit;
  }

  public void setEndeZeit(Timestamp endeZeit) {
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
        "einsatzId=" + einsatzId +
        ", anfangsZeit=" + anfangsZeit +
        ", endeZeit=" + endeZeit +
        ", mitarbeiter=" + mitarbeiter +
        '}';
  }
}
