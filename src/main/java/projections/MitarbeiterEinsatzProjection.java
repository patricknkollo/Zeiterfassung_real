package projections;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.time.LocalDate;

public interface MitarbeiterEinsatzProjection {

  Long getMitarbeiterid();

  String getName();

  String getVorname();

  String getGeschlecht();

  Date getGeburt();

  String getPassword();

  @Value("#{target.einsatzid}")
  Long getEinsatzid();

  @Value("#{target.anfangszeit}")
  LocalDate getAnfangsZeit();

  @Value("#{target.endezeit}")
  LocalDate getEndeZeit();

}
