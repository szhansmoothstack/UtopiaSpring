package com.ss.utopia.service;

import com.ss.utopia.DAO.RouteRepo;
import com.ss.utopia.model.Flight;
import com.ss.utopia.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    RouteRepo routeRepo;

    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeRepo.findAll());
    }

    public ResponseEntity<Route> addRoute(Route route) {
        try {
            return new ResponseEntity<>(routeRepo.save(route), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Route> getRouteById(Long id) {
        Route res = routeRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<Route> deleteRoute(Long id) {
        try {
            routeRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Route> updateRoute(Route route) {
        Optional<Route> update = routeRepo.findById(route.getId());
        if (update.isPresent()) {
            update.get().setDestination(route.getDestination());
            update.get().setOrigin(route.getOrigin());
            try {
                return ResponseEntity.ok(routeRepo.save(update.get()));
            }catch (Exception ignored){
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
