package com.ss.utopia.DAO;

import com.ss.utopia.model.AirplaneType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneTypeRepo extends JpaRepository <AirplaneType, Long> {
}
