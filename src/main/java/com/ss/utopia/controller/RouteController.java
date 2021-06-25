package com.ss.utopia.controller;

import com.ss.utopia.model.Flight;
import com.ss.utopia.model.Route;
import com.ss.utopia.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/route")
public class RouteController {
    @Autowired
    RouteService routeService;

    /**
     * @return list of Routes
     */
    @GetMapping
    public ResponseEntity<List<Route>> getRoutes() {
        return routeService.getAllRoutes();
    }

    /**
     * adds Route
     *
     * @param route to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        return routeService.addRoute(route);
    }

    /**
     * Deletes by ID
     *
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping(path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<Route> deleteRoute(@PathVariable("id") Long id) {
        return routeService.deleteRoute(id);
    }

    /**
     * get by ID
     *
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") Long id) {
        return routeService.getRouteById(id);
    }

    /**
     * POST to update Flights
     *
     * @param route with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<Route> updateRoute(@RequestBody Route route) {
        return routeService.updateRoute(route);
    }
}
