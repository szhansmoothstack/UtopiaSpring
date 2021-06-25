package com.ss.utopia.service;

import com.ss.utopia.DAO.AirplaneRepo;
import com.ss.utopia.model.Airplane;
import com.ss.utopia.model.AirplaneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class AirplaneServiceTest {

    @MockBean
    private AirplaneRepo airplaneRepo;

    @InjectMocks
    private AirplaneService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        AirplaneType airplaneType = new AirplaneType(1L, 20);
        AirplaneType airplaneType2 = new AirplaneType(2L, 25);
        Airplane airplane = new Airplane(1L, airplaneType);
        Airplane airplane2 = new Airplane(2L, airplaneType);
        Airplane updatedAirplane = new Airplane(2L, airplaneType2);
        List<Airplane> airplanes = List.of (airplane, airplane2);

        //Adding Test
        Mockito.when (airplaneRepo.save(Mockito.any())).thenReturn(airplane);
        assertEquals(airplane, testerService.addAirplane(airplane).getBody());
        Mockito.verify(airplaneRepo).save(airplane);

        //get by id test
        Mockito.when (airplaneRepo.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(airplane));
        assertEquals(testerService.getAirplaneById(1L).getBody(), airplane);
        Mockito.verify(airplaneRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addAirplane(airplane).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteAirplane(airplane.getId()).getStatusCode());

        //get all testing
        Mockito.when (airplaneRepo.findAll()).thenReturn(airplanes);
        assertEquals(testerService.getAllAirplanes().getBody(), airplanes);

        //update testing
        Mockito.when (airplaneRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(airplane2));
        Mockito.when (airplaneRepo.save(Mockito.any())).thenReturn(updatedAirplane);

        assertEquals(testerService.updateAirplane(updatedAirplane).getBody(), airplane2);
    }
}