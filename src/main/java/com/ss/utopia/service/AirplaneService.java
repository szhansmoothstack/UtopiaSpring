package com.ss.utopia.service;

import com.ss.utopia.DAO.AirplaneRepo;
import com.ss.utopia.model.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepo airplaneRepo;

    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        return ResponseEntity.ok(airplaneRepo.findAll());
    }

    public ResponseEntity<Airplane> addAirplane(Airplane airplane) {
        try {
            return new ResponseEntity<>(airplaneRepo.save(airplane), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Airplane> getAirplaneById(Long id) {
        Airplane res = airplaneRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<Airplane> deleteAirplane(Long id) {
        try {
            airplaneRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Airplane> updateAirplane(Airplane airplane) {
        Optional<Airplane> update = airplaneRepo.findById(airplane.getId());
        if (update.isPresent()) {
            update.get().setId(airplane.getId());
            update.get().setAirplaneType(airplane.getAirplaneType());
            return ResponseEntity.ok(airplaneRepo.save(update.get()));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
