package com.einsatzstunden.demo.servicetest;

import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.repositories.MitarbeiterRepository;
import com.einsatzstunden.demo.services.MitarbeiterService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.logging.Logger;

@SpringBootTest(classes = {MitarbeiterServiceTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MitarbeiterServiceTest {

  private static final Logger LOGGER = Logger.getLogger(MitarbeiterServiceTest.class.getName());

  @Mock
  MitarbeiterRepository repository;
  @InjectMocks
  MitarbeiterService service;

  Mitarbeiter mitarbeiter1 = new Mitarbeiter("eyede", "samuel", "M", Timestamp.valueOf("1972-04-14 21:36:19"));
  Mitarbeiter mitarbeiter2 = new Mitarbeiter("lobe", "abel", "M", Timestamp.valueOf("1957-03-18 07:21:00"));
  Mitarbeiter mitarbeiter3 = new Mitarbeiter("simo", "marcelle", "W", Timestamp.valueOf("1979-03-28 11:25:45"));

  @Test
  @Order(1)
  public void test_saveMitarbeiter (){

  }
  @Test
  @Order(2)
  public void test_getAllMitarbeiter (){

  }
  @Test
  @Order(3)
  public void test_getMitarbeiterById (){

  }
  @Test
  @Order(4)
  public void test_getMitarbeiterByName (){

  }
  @Test
  @Order(5)
  public void test_getMitarbeiterByVorname (){

  }
  @Test
  @Order(5)
  public void test_getMitarbeiterByGeschlecht (){

  }
  @Test
  @Order(5)
  public void test_getMitarbeiterByGeburt (){

  }
  @Test
  @Order(5)
  public void test_getMitarbeiterByEinsatz (){

  }
  @Test
  @Order(5)
  public void test_UpdateMitarbeiter (){

  }
}
