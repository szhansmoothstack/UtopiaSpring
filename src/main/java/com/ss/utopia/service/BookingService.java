package com.ss.utopia.service;

import com.ss.utopia.DAO.BookingRepo;
import com.ss.utopia.model.Booking;
import com.ss.utopia.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingRepo bookingRepo;

    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingRepo.findAll());
    }

    public ResponseEntity<Booking> addBooking(Booking booking) {
        try {
            return new ResponseEntity<>(bookingRepo.save(booking), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Booking> getBookingById(Long id) {
        Booking res = bookingRepo.findById(id).orElse(null);
        return res == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(res);
    }

    public ResponseEntity<Booking> deleteBooking(Long id) {
        try {
            bookingRepo.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Booking> updateBooking(Booking booking) {
        Optional<Booking> update = bookingRepo.findById(booking.getId());
        if (update.isPresent()) {
            update.get().setActive(booking.isActive());
            update.get().setFlight(booking.getFlight());
            update.get().setBookingUser(booking.getBookingUser());
            update.get().setBookingPayment(booking.getBookingPayment());
            update.get().setConfirmationCode(booking.getConfirmationCode());
            try {
                return ResponseEntity.ok(bookingRepo.save(update.get()));
            }catch (Exception ignored){
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
