package com.ss.utopia.controller;

import com.ss.utopia.model.Route;
import com.ss.utopia.model.User;
import com.ss.utopia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * @return list of User
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * adds User
     *
     * @param user to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Deletes by ID
     *
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping(path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    /**
     * get by ID
     *
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * POST to update User
     *
     * @param user with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<User> updateRoute(@RequestBody User user) {
        return userService.updateUser(user);
    }
}

