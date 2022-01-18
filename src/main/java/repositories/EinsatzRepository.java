package repositories;

import entities.Einsatz;
import entities.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface EinsatzRepository extends JpaRepository<Einsatz, Long> {
  @Modifying
  @Query(value = "select * "
      + "         from einsatz"
      + "         where anfangszeit=:specific_anfangszeit", nativeQuery = true)
  @Transactional
  public List<Einsatz> getEinsatzByAnfangsZeit(@Param("specific_anfangszeit") Timestamp anfangszeit);

  @Modifying
  @Query(value = "select * "
      + "         from einsatz"
      + "         where endezeit=:specific_endezeit", nativeQuery = true)
  @Transactional
  public List<Einsatz> getEinsatzByEndeZeit(@Param("specific_endezeit") Timestamp endezeit);

  @Query(value = "select * "
      + "         from einsatz"
      + "         where mitarbeiterid=:specific_mitarbeiterid", nativeQuery = true)
  @Transactional
  @Modifying
  public List<Einsatz> getEinsatzByMitarbeiterId(@Param("specific_mitarbeiterid") Long name);
}
