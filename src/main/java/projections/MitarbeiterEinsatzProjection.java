package projections;

import java.sql.Date;
import java.time.LocalDate;

public interface MitarbeiterEinsatzProjection {

  Long getMitarbeiterid();

  String getName();

  String getVorname();

  String getGeschlecht();

  Date getGeburt();

  String getPassword();

  Long getEinsatzid();

  LocalDate getAnfangsZeit();

  LocalDate getEndeZeit();

}
