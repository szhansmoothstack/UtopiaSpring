package com.ss.utopia.controller;

import com.ss.utopia.model.Airplane;
import com.ss.utopia.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/airplane")
public class AirplaneController {
    @Autowired
    AirplaneService airplaneService;

    /**
     * GETs all airplanes
     * @return list of airplanes
     */
    @GetMapping
    public ResponseEntity<List<Airplane>> getAirplane(){
        return airplaneService.getAllAirplanes();
    }

    /**
     * adds an airplane
     * @param airplane to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<Airplane> addAirplane (@RequestBody Airplane airplane){
        return airplaneService.addAirplane(airplane);
    }

    /**
     * Deletes by ID
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping (path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<Airplane> deleteAirPlane (@PathVariable ("id") Long id){
        return airplaneService.deleteAirplane (id);
    }

    /**
     * get by ID
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping (path = "/id/{id}")
    public ResponseEntity<Airplane> getAirplaneById (@PathVariable ("id") Long id){
        return airplaneService.getAirplaneById(id);
    }

    /**
     * POST to update an airplane
     * @param airplane with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<Airplane> updateAirplane (@RequestBody Airplane airplane){
        return airplaneService.updateAirplane(airplane);
    }
}
