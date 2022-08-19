package projections;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;

public interface MitarbeiterFullnameProjection {

   Long getMitarbeiterid();

   String getName();

   String getVorname();

   String getGeschlecht();

   Date getGeburt();

   String getPassword();

  @Value("#{target.name+ '  ' +target.vorname}")
  String getFullname ();

  @Value("#{target.name+' '+target.geburt}")
  String getNameAndGeburt();
}
