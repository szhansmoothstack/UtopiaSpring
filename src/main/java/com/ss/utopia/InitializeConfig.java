package com.ss.utopia;

import com.ss.utopia.DAO.*;
import com.ss.utopia.model.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.Collections;

@Configuration
public class InitializeConfig {

    @Bean
    ApplicationRunner applicationRunner(AirportRepo airportRepo,
                                        AirplaneTypeRepo airplaneTypeRepo,
                                        AirplaneRepo airplaneRepo,
                                        BookingRepo bookingRepo,
                                        FlightRepo flightRepo,
                                        RouteRepo routeRepo,
                                        UserRepo userRepo) {
        return args -> {
            //add initial sample data
            //add three airports
            Airport initAirport = new Airport("MEX", "Mexico City");
            if (!airportRepo.existsById(initAirport.getIataId())) airportRepo.save(initAirport);
            initAirport = new Airport("ATL", "Atlanta");
            if (!airportRepo.existsById(initAirport.getIataId())) airportRepo.save(initAirport);
            initAirport = new Airport("DEN", "Denver");
            if (!airportRepo.existsById(initAirport.getIataId())) airportRepo.save(initAirport);

            //add some routes
            if (routeRepo.findAll().size() <= 1) {
                routeRepo.save(new Route(new Airport("MEX", "Mexico City"),
                        new Airport("ATL", "Atlanta")));
                routeRepo.save(new Route(new Airport("DEN", "Denver"),
                        new Airport("ATL", "Atlanta")));
            }

            //add airplanetype

            if (airplaneTypeRepo.findAll().size() == 0) {
                AirplaneType airplaneType = new AirplaneType(1L, 20);
                airplaneTypeRepo.save(airplaneType);
            }

            //add airplane

            if (airplaneRepo.findAll().size() == 0) {
                Airplane airplane = new Airplane(1L, airplaneTypeRepo.findAll().iterator().next());
                airplaneRepo.save(airplane);
            }

            //add a flight

            if (flightRepo.findAll().size() == 0) {
                Flight testFlight = new Flight(1L, routeRepo.findAll().iterator().next(),
                        airplaneRepo.findAll().iterator().next(), ZonedDateTime.now(),
                        0, Collections.emptyList(), 50.5f);
                flightRepo.save(testFlight);
            }

            //add a user

            if (userRepo.findAll().size() == 0) {
                User testUser = new User(1L, new UserRole("TestRole"), "email", "firstName",
                        "lastName", "username", "password", "phone");
                userRepo.save(testUser);
            }

            //add a booking

            if (bookingRepo.findAll().size() == 0){
                bookingRepo.save (new Booking(null, true, "TestConCode",
                        userRepo.findAll().iterator().next(),
                        new BookingPayment(false, "StripeID"),
                        flightRepo.findAll().iterator().next()));
            }


        };
    }
}
