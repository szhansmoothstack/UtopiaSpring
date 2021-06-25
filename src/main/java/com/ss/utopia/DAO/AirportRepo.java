package com.ss.utopia.DAO;

import com.ss.utopia.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepo extends JpaRepository<Airport, String> {
    //Optional<Airport> findAirportByCity (String city);
}
