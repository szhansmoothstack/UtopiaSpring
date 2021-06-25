package com.ss.utopia.service;

import com.ss.utopia.DAO.AirplaneTypeRepo;
import com.ss.utopia.DAO.BookingRepo;
import com.ss.utopia.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class BookingServiceTest {

    @MockBean
    private BookingRepo bookingRepo;

    @InjectMocks
    private BookingService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        User testUser = new User (1L, new UserRole("TestRole"), "email", "firstName",
                "lastName", "username", "password", "phone");
        Airport airport1 = new Airport("TEX", "Austin");
        Airport airport2 = new Airport("MEX", "Mexico City");
        Route testRoute = new Route (airport1, airport2);
        AirplaneType airplaneType = new AirplaneType(1L, 20);
        Airplane airplane = new Airplane(1L, airplaneType);
        Flight testFlight = new Flight(1L, testRoute, airplane, ZonedDateTime.now(),
                0, Collections.emptyList(), 50.5f);
        Booking booking1 = new Booking(1L, true, "TestConfirm", testUser,
                new BookingPayment(false, "testID"), testFlight);
        Booking booking2 = new Booking(2L, false, "TestConfirm2", testUser,
                new BookingPayment(false, "testID"), testFlight);
        Booking booking3 = new Booking(2L, true, "TestConfirm5", testUser,
                new BookingPayment(false, "testID2"), testFlight);
        List<Booking> bookingList = List.of (booking1, booking2);

        //Adding Test
        Mockito.when (bookingRepo.save(Mockito.any())).thenReturn(booking1);
        assertEquals(booking1, testerService.addBooking(booking1).getBody());
        Mockito.verify(bookingRepo).save(booking1);

        //get by id test
        Mockito.when (bookingRepo.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(booking1));
        assertEquals(testerService.getBookingById(1L).getBody(), booking1);
        Mockito.verify(bookingRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addBooking(booking1).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteBooking(booking1.getId()).getStatusCode());

        //get all testing
        Mockito.when (bookingRepo.findAll()).thenReturn(bookingList);
        assertEquals(testerService.getAllBookings().getBody(), bookingList);

        //update testing
        Mockito.when (bookingRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(booking2));
        Mockito.when (bookingRepo.save(Mockito.any())).thenReturn(booking3);

        assertEquals(testerService.updateBooking(booking3).getBody(), booking2);
    }

}