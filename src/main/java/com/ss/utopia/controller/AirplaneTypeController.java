package com.ss.utopia.controller;

import com.ss.utopia.model.AirplaneType;
import com.ss.utopia.service.AirplaneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/airplanetype")
public class AirplaneTypeController {
    @Autowired
    AirplaneTypeService airplaneTypeService;

    /**
     * GETs all types
     * @return list of types
     */
    @GetMapping
    public ResponseEntity<List<AirplaneType>> getAllTypes(){
        return airplaneTypeService.getAllTypes();
    }

    /**
     * adds an types
     * @param airplaneType to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<AirplaneType> addType (@RequestBody AirplaneType airplaneType){
        return airplaneTypeService.addType(airplaneType);
    }

    /**
     * Deletes by ID
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping (path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<AirplaneType> deleteType (@PathVariable ("id") Long id){
        return airplaneTypeService.deleteType (id);
    }

    /**
     * get by ID
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping (path = "/id/{id}")
    public ResponseEntity<AirplaneType> getTypeById (@PathVariable ("id") Long id){
        return airplaneTypeService.getTypeById(id);
    }

    /**
     * POST to update type
     * @param airplaneType with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<AirplaneType> updateType (@RequestBody AirplaneType airplaneType){
        return airplaneTypeService.updateType(airplaneType);
    }
}

