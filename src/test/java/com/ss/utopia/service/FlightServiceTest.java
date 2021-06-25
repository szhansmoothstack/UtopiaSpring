package com.ss.utopia.service;

import com.ss.utopia.DAO.FlightRepo;
import com.ss.utopia.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class FlightServiceTest {

    @MockBean
    private FlightRepo flightRepo;

    @InjectMocks
    private FlightService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        Airport airport1 = new Airport("TEX", "Austin");
        Airport airport2 = new Airport("MEX", "Mexico City");
        Route testRoute = new Route (airport1, airport2);
        AirplaneType airplaneType = new AirplaneType(1L, 20);
        Airplane airplane = new Airplane(1L, airplaneType);
        Flight flight1 = new Flight(1L, testRoute, airplane, ZonedDateTime.now(),
                0, Collections.emptyList(), 50.5f);
        Flight flight2 = new Flight(2L, testRoute, airplane, ZonedDateTime.now(),
                0, Collections.emptyList(), 80.5f);
        Flight flight3 = new Flight(2L, testRoute, airplane, ZonedDateTime.now(),
                0, Collections.emptyList(), 250.5f);
        List<Flight> flightList = List.of (flight1, flight2);

        //Adding Test
        Mockito.when (flightRepo.save(Mockito.any())).thenReturn(flight1);
        assertEquals(flight1, testerService.addFlight(flight1).getBody());
        Mockito.verify(flightRepo).save(flight1);

        //get by id test
        Mockito.when (flightRepo.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(flight1));
        assertEquals(testerService.getFlightById(1L).getBody(), flight1);
        Mockito.verify(flightRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addFlight(flight1).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteFlight(flight1.getId()).getStatusCode());

        //get all testing
        Mockito.when (flightRepo.findAll()).thenReturn(flightList);
        assertEquals(testerService.getAllFlights().getBody(), flightList);

        //update testing
        Mockito.when (flightRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(flight2));
        Mockito.when (flightRepo.save(Mockito.any())).thenReturn(flight3);

        assertEquals(testerService.updateFlight(flight3).getBody(), flight2);
    }

}