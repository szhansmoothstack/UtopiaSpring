package com.ss.utopia.service;

import com.ss.utopia.DAO.AirportRepo;
import com.ss.utopia.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepo airportRepo;

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }

    public ResponseEntity<Airport> addAirport(Airport airport) {
        try {
            return new ResponseEntity<>(airportRepo.save(airport), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Airport> getAirportById(String id) {
        Airport res = airportRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }


    public ResponseEntity<Airport> deleteAirport(String id) {
        try {
            airportRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Airport> updateAirport(Airport airport) {
        Optional<Airport> update = airportRepo.findById(airport.getIataId());
        if (update.isPresent()) {
            update.get().setCity(airport.getCity());
            return ResponseEntity.ok(airportRepo.save(update.get()));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
