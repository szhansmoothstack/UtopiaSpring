package com.ss.utopia.controller;

import com.ss.utopia.model.Booking;
import com.ss.utopia.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    /**
     * @return list of bookings
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getBookings (){
        return bookingService.getAllBookings();
    }

    /**
     * adds booking
     * @param booking to be added
     * @return a response entity regarding the add
     */
    @PutMapping
    public ResponseEntity<Booking> addBooking (@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }

    /**
     * Deletes by ID
     * @param id
     * @return HTTP response regarding the deletion
     */
    @DeleteMapping (path = "/id/{id}")
    @ResponseBody
    public ResponseEntity<Booking> deleteBooking (@PathVariable ("id") Long id){
        return bookingService.deleteBooking (id);
    }

    /**
     * get by ID
     * @param id
     * @return HTTP response regarding the get
     */
    @GetMapping (path = "/id/{id}")
    public ResponseEntity<Booking> getBookingById (@PathVariable ("id") Long id){
        return bookingService.getBookingById(id);
    }

    /**
     * POST to update an booking
     * @param booking with all attributes that need to be updated
     * @return HTTP response regarding the POST
     */
    @PostMapping
    public ResponseEntity<Booking> updateBooking (@RequestBody Booking booking){
        return bookingService.updateBooking(booking);
    }
}
