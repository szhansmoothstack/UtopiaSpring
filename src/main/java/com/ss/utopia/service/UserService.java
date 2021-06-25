package com.ss.utopia.service;

import com.ss.utopia.DAO.UserRepo;
import com.ss.utopia.model.Flight;
import com.ss.utopia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    public ResponseEntity<User> addUser(User user) {
        try {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<User> getUserById(Long id) {
        User res = userRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<User> deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<User> updateUser(User user) {
        Optional<User> update = userRepo.findById(user.getId());
        if (update.isPresent()) {
            update.get().setEmail(user.getEmail());
            update.get().setFirstName(user.getFirstName());
            update.get().setLastName(user.getLastName());
            update.get().setUserName(user.getUserName());
            update.get().setPassword(user.getPassword());
            update.get().setPhone(user.getPhone());
            update.get().setUserRole(user.getUserRole());
            try {
                return ResponseEntity.ok(userRepo.save(update.get()));
            }catch (Exception ignored){
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
