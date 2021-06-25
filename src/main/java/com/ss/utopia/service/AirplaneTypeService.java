package com.ss.utopia.service;

import com.ss.utopia.DAO.AirplaneTypeRepo;
import com.ss.utopia.model.Airplane;
import com.ss.utopia.model.AirplaneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneTypeService {
    @Autowired
    AirplaneTypeRepo airplaneTypeRepo;

    public ResponseEntity<List<AirplaneType>> getAllTypes() {
        return ResponseEntity.ok(airplaneTypeRepo.findAll());
    }

    public ResponseEntity<AirplaneType> addType(AirplaneType airplaneType) {
        try {
            return new ResponseEntity<>(airplaneTypeRepo.save(airplaneType), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<AirplaneType> deleteType(Long id) {
        try {
            airplaneTypeRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<AirplaneType> getTypeById(Long id) {
        AirplaneType res = airplaneTypeRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<AirplaneType> updateType(AirplaneType airplaneType) {
        Optional<AirplaneType> update = airplaneTypeRepo.findById(airplaneType.getId());
        if (update.isPresent()) {
            update.get().setId(airplaneType.getId());
            update.get().setCapacity(airplaneType.getCapacity());
            return ResponseEntity.ok(airplaneTypeRepo.save(update.get()));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
