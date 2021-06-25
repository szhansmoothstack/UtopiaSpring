package com.ss.utopia.DAO;

import com.ss.utopia.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {
}
