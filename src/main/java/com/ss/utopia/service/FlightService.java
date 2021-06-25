package com.ss.utopia.service;

import com.ss.utopia.DAO.FlightRepo;
import com.ss.utopia.model.Airplane;
import com.ss.utopia.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepo flightRepo;

    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightRepo.findAll());
    }

    public ResponseEntity<Flight> addFlight(Flight flight) {
        try {
            return new ResponseEntity<>(flightRepo.save(flight), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Flight> getFlightById(Long id) {
        Flight res = flightRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<Flight> deleteFlight(Long id) {
        try {
            flightRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Flight> updateFlight(Flight flight) {
        Optional<Flight> update = flightRepo.findById(flight.getId());
        if (update.isPresent()) {
            update.get().setId(flight.getId());
            update.get().setAirplane(flight.getAirplane());
            update.get().setBookings(flight.getBookings());
            update.get().setDepartureTime(flight.getDepartureTime());
            update.get().setRoute(flight.getRoute());
            update.get().setReservedSeats(flight.getReservedSeats());
            update.get().setSeatPrice(flight.getSeatPrice());
            try {
                return ResponseEntity.ok(flightRepo.save(update.get()));
            }catch (Exception ignored){
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
