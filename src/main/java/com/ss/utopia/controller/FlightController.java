package com.ss.utopia.controller;

import com.ss.utopia.model.Booking;
import com.ss.utopia.model.Flight;
import com.ss.utopia.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    /**
     * @return list of Flights
     */
    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        return flightService.getAllFlights();
    }

    /**
     * adds Flights
     *
     * @param flight to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    /**
     * Deletes by ID
     *
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping(path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id") Long id) {
        return flightService.deleteFlight(id);
    }

    /**
     * get by ID
     *
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") Long id) {
        return flightService.getFlightById(id);
    }

    /**
     * POST to update Flights
     *
     * @param flight with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }
}
