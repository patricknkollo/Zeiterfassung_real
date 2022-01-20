package com.einsatzstunden.demo.repositories;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Component
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter,Long> {

  @Modifying
  @Query(value = "select * "
      + "         from mitarbeiter"
      + "         where mitarbeiterid IN (select mitarbeiterid "
      + "                                 from einsatz "
      + "                                 where anfangszeit =:specific_anfangszeit and endezeit =:specific_endezeit)", nativeQuery = true)
  @Transactional
  public List<Mitarbeiter> getMitarbeiterBySpecificEinsatz(@Param("specific_anfangszeit") Timestamp anfang, @Param("specific_endezeit")
      Timestamp ende);

  @Modifying
  @Query(value = "select * "
      + "         from mitarbeiter"
      + "         where geburt=:specific_geburt", nativeQuery = true)
  @Transactional
  public List<Mitarbeiter> getMitarbeiterByGeburt(@Param("specific_geburt") Timestamp geburt);

  @Modifying
  @Query(value = "select * "
      + "         from mitarbeiter"
      + "         where geschlecht=:specific_geschlecht", nativeQuery = true)
  @Transactional
  public List<Mitarbeiter> getMitarbeiterByGeschlecht(@Param("specific_geschlecht") String geschlecht);

  @Modifying
  @Query(value = "select * "
      + "         from mitarbeiter"
      + "         where vorname=:specific_vorname", nativeQuery = true)
  @Transactional
  public List<Mitarbeiter> getMitarbeiterByVorname(@Param("specific_vorname") String vorname);

  @Query(value = "select * "
      + "         from mitarbeiter"
      + "         where name=:specific_name", nativeQuery = true)
  @Transactional
  @Modifying
  public List<Mitarbeiter> getMitarbeiterByName(@Param("specific_name") String name);
}
