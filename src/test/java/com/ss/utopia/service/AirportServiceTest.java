package com.ss.utopia.service;

import com.ss.utopia.DAO.AirportRepo;
import com.ss.utopia.model.Airplane;
import com.ss.utopia.model.AirplaneType;
import com.ss.utopia.model.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class AirportServiceTest {


    @MockBean
    private AirportRepo airportRepo;

    @InjectMocks
    private AirportService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        Airport initAirport = new Airport("MEX", "Mexico City");
        Airport initAirport2 = new Airport("ATL", "Atlanta");
        Airport initAirport3 = new Airport("ATL", "Atlanta2");
        List<Airport> airplanes = List.of (initAirport, initAirport2);

        //Adding Test
        Mockito.when (airportRepo.save(Mockito.any())).thenReturn(initAirport);
        assertEquals(initAirport, testerService.addAirport(initAirport).getBody());
        Mockito.verify(airportRepo).save(initAirport);

        //get by id test
        Mockito.when (airportRepo.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(initAirport));
        assertEquals(testerService.getAirportById("MEX").getBody(), initAirport);
        Mockito.verify(airportRepo).findById(Mockito.anyString());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addAirport(initAirport).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteAirport(initAirport.getIataId()).getStatusCode());

        //get all testing
        Mockito.when (airportRepo.findAll()).thenReturn(airplanes);
        assertEquals(testerService.getAllAirports(), airplanes);

        //update testing
        Mockito.when (airportRepo.findById(Mockito.anyString())).thenReturn(Optional.of(initAirport2));
        Mockito.when (airportRepo.save(Mockito.any())).thenReturn(initAirport3);

        assertEquals(testerService.updateAirport(initAirport3).getBody(), initAirport2);
    }

}