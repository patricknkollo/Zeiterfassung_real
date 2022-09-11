package com.einsatzstunden.demo.controllertest;

import com.einsatzstunden.demo.Controllers.MitarbeiterController;
import com.einsatzstunden.demo.entities.Mitarbeiter;
import com.einsatzstunden.demo.services.MitarbeiterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest(classes = {MitarbeiterControllerTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class MitarbeiterControllerTest {

  @InjectMocks
  private MitarbeiterController mitarbeiterController;

  @Mock
  private MitarbeiterService service;

  private Mitarbeiter mitarbeiter = new Mitarbeiter();

  @Test
  void test_saveMitarbeiter(){
    ResponseEntity<Mitarbeiter> expected = ResponseEntity.of(Optional.of(mitarbeiter));

    Mockito.when(service.saveMitarbeiter(mitarbeiter)).thenReturn(expected);
    ResponseEntity<Mitarbeiter> result = mitarbeiterController.saveMitarbeiter(mitarbeiter);
    Assertions.assertEquals(expected, result);
  }
}
