package com.ss.utopia.controller;


import com.ss.utopia.model.Airport;
import com.ss.utopia.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    /**
     * GETs all airports
     * @return list of airports
     */
    @GetMapping
    public ResponseEntity<List<Airport>> getAirport (){
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    /**
     * adds an airport
     * @param airport to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<Airport> addAirport (@RequestBody Airport airport){
        return airportService.addAirport(airport);
    }

    /**
     * Deletes by ID
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping (path = "/id/{iataId}")
    @ResponseBody
    public ResponseEntity<Airport> deleteAirport (@PathVariable ("iataId") String id){
        return airportService.deleteAirport (id);
    }

    /**
     * get by ID
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping (path = "/id/{iataId}")
    public ResponseEntity<Airport> getAirportById (@PathVariable ("iataId") String id){
        return airportService.getAirportById(id);
    }

    /**
     * POST to update an airport
     * @param airport with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<Airport> updateAirport (@RequestBody Airport airport){
        return airportService.updateAirport(airport);
    }
}
