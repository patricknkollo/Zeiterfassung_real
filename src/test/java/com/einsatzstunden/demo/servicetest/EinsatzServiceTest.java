package com.einsatzstunden.demo.servicetest;

import com.einsatzstunden.demo.entities.Einsatz;
import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.repositories.EinsatzRepository;
import com.einsatzstunden.demo.services.EinsatzService;
import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {EinsatzServiceTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EinsatzServiceTest {

  @InjectMocks
  EinsatzService einsatzService;
  @Mock
  EinsatzRepository repository;

  Mitarbeiter mitarbeiter1 = new Mitarbeiter("eyede", "samuel", "M", Timestamp.valueOf("1972-04-14 21:36:19"));
  Mitarbeiter mitarbeiter2 = new Mitarbeiter("lobe", "abel", "M", Timestamp.valueOf("1957-03-18 07:21:00"));
  Mitarbeiter mitarbeiter3 = new Mitarbeiter("simo", "marcelle", "W", Timestamp.valueOf("1979-03-28 11:25:45"));

  private Einsatz einsatz1 = new Einsatz(Timestamp.valueOf("2022-02-15 07:00:00"),Timestamp.valueOf("2022-02-15 16:00:00"), mitarbeiter1);
  private Einsatz einsatz2 = new Einsatz(Timestamp.valueOf("2022-02-16 07:00:00"),Timestamp.valueOf("2022-02-16 16:00:00"), mitarbeiter2);
  private Einsatz einsatz3 = new Einsatz(Timestamp.valueOf("2022-02-17 07:00:00"),Timestamp.valueOf("2022-02-17 16:00:00"), mitarbeiter3);

  @Test
  @Order(1)
  public void test_saveEinsatz(){

  }
  @Test
  @Order(2)
  public void test_getAllEinsatz(){
    List<Einsatz>einsatzList = Arrays.asList(einsatz1, einsatz2, einsatz3);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.OK);
    Mockito.when(repository.findAll()).thenReturn(einsatzList);
    BDDAssertions.assertThat(responseEntity.equals(einsatzService.getAllEinsatz()));
  }
  @Test
  @Order(3)
  public void test_getAllEinsatzById(){
    Long id =1L;
    ResponseEntity<Optional<Einsatz>> responseEntity = new ResponseEntity<>(Optional.of(einsatz1), HttpStatus.OK);
    Mockito.when(repository.findById(id)).thenReturn(Optional.of(einsatz1));
    BDDAssertions.assertThat(responseEntity.equals(einsatzService.getAllEinsatzById(id)));
  }
  @Test
  @Order(4)
  public void test_getAllEinsatzByAnfangszeit(){
    List<Einsatz>einsatzList = Arrays.asList(einsatz1, einsatz2, einsatz3);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.FOUND);
    Timestamp anfangszeit = Timestamp.valueOf("2022-02-15 07:00:00");
    Mockito.when(repository.getEinsatzByAnfangsZeit(anfangszeit)).thenReturn(einsatzList);
    Assert.assertEquals(einsatzService.getAllEinsatzByAnfangszeit(anfangszeit),responseEntity);
  }
  @Test
  @Order(5)
  public void test_getAllEinsatzByEndezeit(){
    List<Einsatz>einsatzList = Arrays.asList(einsatz1, einsatz2, einsatz3);
    ResponseEntity<List<Einsatz>> responseEntity = new ResponseEntity<>(einsatzList, HttpStatus.FOUND);
    Timestamp anfangszeit = Timestamp.valueOf("2022-02-15 07:00:00");
    Mockito.when(repository.getEinsatzByEndeZeit(anfangszeit)).thenReturn(einsatzList);
    Assert.assertEquals(einsatzService.getAllEinsatzByEndezeit(anfangszeit),responseEntity);
  }
  @Test
  @Order(6)
  public void test_UpdateEinsatz(){

  }
  @Test
  @Order(7)
  public void test_getEinsaetzeOfMitarbeiter(){

  }
}
