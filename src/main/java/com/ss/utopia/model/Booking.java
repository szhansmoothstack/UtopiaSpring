package com.ss.utopia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "booking")
public class Booking {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "booking_generator")
    private Long id;
    @Column (name = "active", nullable = false)
    private boolean active;
    @Column (name = "confirmation_code", nullable = false)
    private String confirmationCode;
    @ManyToOne (targetEntity = User.class, optional = false)
    @JoinColumn (name = "booking_user_id", nullable = false)
    private User bookingUser;
    @Embedded
    private BookingPayment bookingPayment;
    @ManyToOne (targetEntity = Flight.class, optional = false)
    @JoinColumn (name = "flight_id", nullable = false)
    private Flight flight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public User getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(User bookingUser) {
        this.bookingUser = bookingUser;
    }

    public BookingPayment getBookingPayment() {
        return bookingPayment;
    }

    public void setBookingPayment(BookingPayment bookingPayment) {
        this.bookingPayment = bookingPayment;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking() {
    }

    public Booking(Long id, boolean active, String confirmationCode, User bookingUser, BookingPayment bookingPayment, Flight flight) {
        this.id = id;
        this.active = active;
        this.confirmationCode = confirmationCode;
        this.bookingUser = bookingUser;
        this.bookingPayment = bookingPayment;
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return active == booking.active && Objects.equals(id, booking.id) && Objects.equals(confirmationCode, booking.confirmationCode) && Objects.equals(bookingUser, booking.bookingUser) && Objects.equals(bookingPayment, booking.bookingPayment) && Objects.equals(flight, booking.flight);
    }

}
