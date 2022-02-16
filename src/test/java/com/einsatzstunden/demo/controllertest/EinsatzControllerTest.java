package com.einsatzstunden.demo.controllertest;

import com.einsatzstunden.demo.Controllers.EinsatzController;
import com.einsatzstunden.demo.entities.Einsatz;
import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.services.EinsatzService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {EinsatzControllerTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EinsatzControllerTest {

  @Mock
  EinsatzService service;
  @InjectMocks
  EinsatzController controller;

  Mitarbeiter mitarbeiter1 = new Mitarbeiter("eyede", "samuel", "M", Timestamp.valueOf("1972-04-14 21:36:19"));
  Mitarbeiter mitarbeiter2 = new Mitarbeiter("lobe", "abel", "M", Timestamp.valueOf("1957-03-18 07:21:00"));
  Mitarbeiter mitarbeiter3 = new Mitarbeiter("simo", "marcelle", "W", Timestamp.valueOf("1979-03-28 11:25:45"));
  Einsatz einsatz1 = new Einsatz(1l, Timestamp.valueOf("2021-04-14 06:30:19"), Timestamp.valueOf("2021-04-14 06:30:19"), mitarbeiter2);
  Einsatz einsatz2 = new Einsatz(2l, Timestamp.valueOf("2022-03-18 07:30:00"), Timestamp.valueOf("2022-03-18 17:00:00"), mitarbeiter1);
  Einsatz einsatz3 = new Einsatz(3l, Timestamp.valueOf("2022-03-28 11:30:45"), Timestamp.valueOf("2022-03-28 18:30:45"), mitarbeiter3);

  @Test
  @Order(1)
  public void test_saveEinsatz (){
    service.saveEinsatz(einsatz1);
  }
  @Test
  @Order(2)
  public void test_getAllEinsatz (){
    List<Einsatz> einsatzList = new ArrayList<>();
    einsatzList.add(einsatz1);
    einsatzList.add(einsatz2);
    einsatzList.add(einsatz3);
        //List.of(einsatz1, einsatz2, einsatz3);
    ResponseEntity<List<Einsatz>> responseEntityExpected = new ResponseEntity<>(einsatzList, HttpStatus.OK);
    Mockito.when(service.getAllEinsatz()).thenReturn(responseEntityExpected);
    assert (responseEntityExpected.equals(controller.getAllEinsatz()));
    assert (einsatz2.equals(controller.getAllEinsatz().getBody().get(1)));
  }
  @Test
  @Order(3)
  public void test_getAllEinsatzById (){
    Long id = 1L;
    ResponseEntity<Optional<Einsatz>> responseEntityExpected = new ResponseEntity<>(Optional.of(einsatz2), HttpStatus.OK);
    Mockito.when(service.getAllEinsatzById(id)).thenReturn(responseEntityExpected);
    assert (responseEntityExpected.equals(controller.getAllEinsatzById(id)));
    assert (einsatz2.equals(controller.getAllEinsatzById(id).getBody().get()));
  }
  @Test
  @Order(4)
  public void test_getAllEinsatzByAnfangszeit (){
    Timestamp anfangszeit = Timestamp.valueOf("2022-01-24 16:033:00");
    List<Einsatz> einsatzList = new ArrayList<>();
    einsatzList.add(einsatz1);
    einsatzList.add(einsatz2);
    einsatzList.add(einsatz3);
    ResponseEntity<List<Einsatz>> responseEntityExpected = new ResponseEntity<>(einsatzList, HttpStatus.OK);
    Mockito.when(service.getAllEinsatzByAnfangszeit(anfangszeit)).thenReturn(responseEntityExpected);
    assert (responseEntityExpected.equals(controller.getAllEinsatzByAnfangszeit(anfangszeit)));
    assert (einsatz2.equals(controller.getAllEinsatzByAnfangszeit(anfangszeit).getBody().get(1)));
  }
  @Test
  @Order(5)
  public void test_getAllEinsatzByEndezeit (){
    Timestamp endezeit = Timestamp.valueOf("2022-01-24 16:033:00");
    List<Einsatz> einsatzList = new ArrayList<>();
    einsatzList.add(einsatz1);
    einsatzList.add(einsatz2);
    einsatzList.add(einsatz3);
    ResponseEntity<List<Einsatz>> responseEntityExpected = new ResponseEntity<>(einsatzList, HttpStatus.OK);
    Mockito.when(service.getAllEinsatzByEndezeit(endezeit)).thenReturn(responseEntityExpected);
    assert (responseEntityExpected.equals(controller.getAllEinsatzByEndezeit(endezeit)));
    assert (einsatz2.equals(controller.getAllEinsatzByEndezeit(endezeit).getBody().get(1)));
  }
  @Test
  @Order(6)
  public void test_UpdateEinsatz (){

  }
  @Test
  @Order(7)
  public void test_deleteEinsatz (){

  }
}
