package com.ss.utopia.service;

import com.ss.utopia.DAO.UserRepo;
import com.ss.utopia.model.User;
import com.ss.utopia.model.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepo userRepo;

    @InjectMocks
    private UserService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        User user1 = new User (1L, new UserRole("TestRole"), "email", "firstName",
                "lastName", "username", "password", "phone");
        User user2 = new User (2L, new UserRole("TestRole2"), "email2", "firstName",
                "lastName", "username2", "password", "phone2");
        User user3 = new User (2L, new UserRole("TestRole3"), "email", "firstName",
                "lastName", "username3", "password", "phone3");
        List<User> routeList = List.of (user1, user2);

        //Adding Test
        Mockito.when (userRepo.save(Mockito.any())).thenReturn(user1);
        assertEquals(user1, testerService.addUser(user1).getBody());
        Mockito.verify(userRepo).save(user1);

        //get by id test
        Mockito.when (userRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user1));
        assertEquals(testerService.getUserById(user1.getId()).getBody(), user1);
        Mockito.verify(userRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addUser(user1).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteUser(user1.getId()).getStatusCode());

        //get all testing
        Mockito.when (userRepo.findAll()).thenReturn(routeList);
        assertEquals(testerService.getAllUsers().getBody(), routeList);

        //update testing
        Mockito.when (userRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user2));
        Mockito.when (userRepo.save(Mockito.any())).thenReturn(user3);

        assertEquals(testerService.updateUser(user3).getBody(), user2);
    }
}