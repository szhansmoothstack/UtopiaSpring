package com.ss.utopia.service;

import com.ss.utopia.DAO.RouteRepo;
import com.ss.utopia.model.Airport;
import com.ss.utopia.model.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class RouteServiceTest {

    @MockBean
    private RouteRepo routeRepo;

    @InjectMocks
    private RouteService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        Airport airport1 = new Airport("TEX", "Austin");
        Airport airport2 = new Airport("MEX", "Mexico City");
        Airport airport3 = new Airport("AMS", "Amsterdam");
        Route route1 = new Route (1L, airport1, airport2);
        Route route2 = new Route (2L, airport2, airport3);
        Route route3 = new Route (2L, airport3, airport1);
        List<Route> routeList = List.of (route1, route2);

        //Adding Test
        Mockito.when (routeRepo.save(Mockito.any())).thenReturn(route1);
        assertEquals(route1, testerService.addRoute(route1).getBody());
        Mockito.verify(routeRepo).save(route1);

        //get by id test
        Mockito.when (routeRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(route1));
        assertEquals(testerService.getRouteById(route1.getId()).getBody(), route1);
        Mockito.verify(routeRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addRoute(route1).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteRoute(route1.getId()).getStatusCode());

        //get all testing
        Mockito.when (routeRepo.findAll()).thenReturn(routeList);
        assertEquals(testerService.getAllRoutes().getBody(), routeList);

        //update testing
        Mockito.when (routeRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(route2));
        Mockito.when (routeRepo.save(Mockito.any())).thenReturn(route3);

        assertEquals(testerService.updateRoute(route3).getBody(), route2);
    }
}