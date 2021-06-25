package com.ss.utopia.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "flight_generator")
    private Long id;

    @ManyToOne (targetEntity = Route.class, optional = false)
    @JoinColumn (name = "route_id", nullable = false)
    private Route route;

    @ManyToOne (targetEntity = Airplane.class, optional = false)
    @JoinColumn (name = "airplane_id", nullable = false)
    private Airplane airplane;

    @Column (nullable = false, name = "departure_time")
    private ZonedDateTime departureTime;

    @Column (name = "reserved_seats", nullable = false)
    private int reservedSeats;

    @OneToMany (targetEntity = Booking.class, cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @Column (name = "seat_price", nullable = false)
    private float seatPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(float seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Flight(Long id, Route route, Airplane airplane, ZonedDateTime departureTime, int reservedSeats, List<Booking> bookings, float seatPrice) {
        this.id = id;
        this.route = route;
        this.airplane = airplane;
        this.departureTime = departureTime;
        this.reservedSeats = reservedSeats;
        this.bookings = bookings;
        this.seatPrice = seatPrice;
    }

    public Flight() {
    }

    public Flight(Route route, Airplane airplane, ZonedDateTime departureTime, int reservedSeats, List<Booking> bookings, float seatPrice) {
        this.route = route;
        this.airplane = airplane;
        this.departureTime = departureTime;
        this.reservedSeats = reservedSeats;
        this.bookings = bookings;
        this.seatPrice = seatPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return reservedSeats == flight.reservedSeats && Float.compare(flight.seatPrice, seatPrice) == 0 && Objects.equals(id, flight.id) && Objects.equals(route, flight.route) && Objects.equals(airplane, flight.airplane) && Objects.equals(departureTime, flight.departureTime) && Objects.equals(bookings, flight.bookings);
    }

}
