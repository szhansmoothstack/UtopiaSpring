package com.ss.utopia.controller;

import com.ss.utopia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final String url = "http://localHost:8080/";
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    RestTemplate restTemplate;

    public AdminController() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Section for AirportController
     */
    @GetMapping (path = "/airport")
    public ResponseEntity<String> getAirports (){
        return restTemplate.getForEntity(url + "airport",
                String.class);
    }

    @PutMapping(path = "/airport")
    public ResponseEntity<Airport> addAirport (@RequestBody Airport airport){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Airport> entity = new HttpEntity<>(airport, headers);
        return restTemplate.exchange(url + "airport", HttpMethod.PUT, entity,
                Airport.class);
    }

    @DeleteMapping (path = "/airport/id/{iataId}")
    public ResponseEntity<Airport> deleteAirport (@PathVariable ("iataId") String id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "airport/id/" + id, HttpMethod.DELETE, entity,
                Airport.class);
    }

    @GetMapping (path = "/airport/id/{iataId}")
    public ResponseEntity<Airport> getAirportById (@PathVariable ("iataId") String id){
        return restTemplate.getForEntity(url + "airport/id/" + id,
                Airport.class);
    }

    @PostMapping (path = "/airport")
    public ResponseEntity<Airport> updateAirport (@RequestBody Airport airport){
        HttpEntity<Airport> entity = new HttpEntity<>(airport, headers);
        return restTemplate.exchange(url + "airport", HttpMethod.POST, entity,
                Airport.class);
    }

    /**
     * Airplane Controller Section
     * All functions of AirplaneController called here
     */

    @GetMapping (path = "/airplane")
    public ResponseEntity<String> getAirplanes (){
        return restTemplate.getForEntity(url + "airplane",
                String.class);
    }

    @PutMapping(path = "/airplane")
    public ResponseEntity<Airplane> addAirplane (@RequestBody Airplane airplane){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Airplane> entity = new HttpEntity<>(airplane, headers);
        return restTemplate.exchange(url + "airplane", HttpMethod.PUT, entity,
                Airplane.class);
    }

    @DeleteMapping (path = "/airplane/id/{id}")
    public ResponseEntity<Airplane> deleteAirplane (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "airplane/id/" + id, HttpMethod.DELETE, entity,
                Airplane.class);
    }

    @GetMapping (path = "/airplane/id/{id}")
    public ResponseEntity<Airplane> getAirplaneId (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "airplane/id/" + id,
                Airplane.class);
    }

    @PostMapping (path = "/airplane")
    public ResponseEntity<Airplane> updateAirplane (@RequestBody Airplane airplane){
        HttpEntity<Airplane> entity = new HttpEntity<>(airplane, headers);
        return restTemplate.exchange(url + "airplane", HttpMethod.POST, entity,
                Airplane.class);
    }

    /**
     * AirplaneType Section
     */

    @GetMapping (path = "/airplanetype")
    public ResponseEntity<String> getAirplaneTypes (){
        return restTemplate.getForEntity(url + "airplanetype",
                String.class);
    }

    @PutMapping(path = "/airplanetype")
    public ResponseEntity<AirplaneType> addAirplaneType (@RequestBody AirplaneType airplaneType){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AirplaneType> entity = new HttpEntity<>(airplaneType, headers);
        return restTemplate.exchange(url + "airplanetype", HttpMethod.PUT, entity,
                AirplaneType.class);
    }

    @DeleteMapping (path = "/airplanetype/id/{id}")
    public ResponseEntity<AirplaneType> deleteAirplaneType (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "airplanetype/id/" + id, HttpMethod.DELETE, entity,
                AirplaneType.class);
    }

    @GetMapping (path = "/airplanetype/id/{id}")
    public ResponseEntity<AirplaneType> getTypeById (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "airplanetype/id/" + id,
                AirplaneType.class);
    }

    @PostMapping (path = "/airplanetype")
    public ResponseEntity<AirplaneType> updateAirplaneType (@RequestBody AirplaneType airplaneType){
        HttpEntity<AirplaneType> entity = new HttpEntity<>(airplaneType, headers);
        return restTemplate.exchange(url + "airplanetype", HttpMethod.POST, entity,
                AirplaneType.class);
    }

    /**
     * Booking controller section
     */

    @GetMapping (path = "/booking")
    public ResponseEntity<String> getBooking (){
        return restTemplate.getForEntity(url + "booking",
                String.class);
    }

    @PutMapping(path = "/booking")
    public ResponseEntity<Booking> addBooking (@RequestBody Booking booking){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Booking > entity = new HttpEntity<>(booking, headers);
        return restTemplate.exchange(url + "booking", HttpMethod.PUT, entity,
                Booking.class);
    }

    @DeleteMapping (path = "/booking/id/{id}")
    public ResponseEntity<Booking> deleteBooking (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "booking/id/" + id, HttpMethod.DELETE, entity,
                Booking.class);
    }

    @GetMapping (path = "/booking/id/{id}")
    public ResponseEntity<Booking> getBooking (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "booking/id/" + id,
                Booking.class);
    }

    @PostMapping (path = "/booking")
    public ResponseEntity<Booking> updateBooking (@RequestBody Booking booking){
        HttpEntity<Booking> entity = new HttpEntity<>(booking, headers);
        return restTemplate.exchange(url + "booking", HttpMethod.POST, entity,
                Booking.class);
    }

    /**
     * Flight Controller Section
     */

    @GetMapping (path = "/flight")
    public ResponseEntity<String> getFlights (){
        return restTemplate.getForEntity(url + "flight",
                String.class);
    }

    @PutMapping(path = "/flight")
    public ResponseEntity<Flight> addFlight (@RequestBody Flight flight){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Flight > entity = new HttpEntity<>(flight, headers);
        return restTemplate.exchange(url + "flight", HttpMethod.PUT, entity,
                Flight.class);
    }

    @DeleteMapping (path = "/flight/id/{id}")
    public ResponseEntity<Flight> deleteFlight (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "flight/id/" + id, HttpMethod.DELETE, entity,
                Flight.class);
    }

    @GetMapping (path = "/flight/id/{id}")
    public ResponseEntity<Flight> getFlightById (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "flight/id/" + id,
                Flight.class);
    }

    @PostMapping (path = "/flight")
    public ResponseEntity<Flight> updateFlight (@RequestBody Flight flight){
        HttpEntity<Flight> entity = new HttpEntity<>(flight, headers);
        return restTemplate.exchange(url + "flight", HttpMethod.POST, entity,
                Flight.class);
    }

    /**
     * Route Controller Section
     */

    @GetMapping (path = "/route")
    public ResponseEntity<String> getRoutes (){
        return restTemplate.getForEntity(url + "route",
                String.class);
    }

    @PutMapping(path = "/route")
    public ResponseEntity<Route> addRoute (@RequestBody Route route){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Route> entity = new HttpEntity<>(route, headers);
        return restTemplate.exchange(url + "route", HttpMethod.PUT, entity,
                Route.class);
    }

    @DeleteMapping (path = "/route/id/{id}")
    public ResponseEntity<Route> deleteRoute (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "route/id/" + id, HttpMethod.DELETE, entity,
                Route.class);
    }

    @GetMapping (path = "/route/id/{id}")
    public ResponseEntity<Route> getRouteById (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "route/id/" + id,
                Route.class);
    }

    @PostMapping (path = "/route")
    public ResponseEntity<Route> updateRoute (@RequestBody Route route){
        HttpEntity<Route> entity = new HttpEntity<>(route, headers);
        return restTemplate.exchange(url + "route", HttpMethod.POST, entity,
                Route.class);
    }


    /**
     * User Controller Section
     */

    @GetMapping (path = "/user")
    public ResponseEntity<String> getUser (){
        return restTemplate.getForEntity(url + "user",
                String.class);
    }

    @PutMapping(path = "/user")
    public ResponseEntity<User> addUser (@RequestBody User user){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(url + "user", HttpMethod.PUT, entity,
                User.class);
    }

    @DeleteMapping (path = "/user/id/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable ("id") Long id){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> entity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(url + "user/id/" + id, HttpMethod.DELETE, entity,
                User.class);
    }

    @GetMapping (path = "/user/id/{id}")
    public ResponseEntity<User> getUserById (@PathVariable ("id") Long id){
        return restTemplate.getForEntity(url + "user/id/" + id,
                User.class);
    }

    @PostMapping (path = "/user")
    public ResponseEntity<User> updateRoute (@RequestBody User user){
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(url + "user", HttpMethod.POST, entity,
                User.class);
    }

}
