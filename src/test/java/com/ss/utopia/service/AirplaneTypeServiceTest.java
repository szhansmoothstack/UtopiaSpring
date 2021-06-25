package com.ss.utopia.service;

import com.ss.utopia.DAO.AirplaneRepo;
import com.ss.utopia.DAO.AirplaneTypeRepo;
import com.ss.utopia.model.Airplane;
import com.ss.utopia.model.AirplaneType;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith (MockitoExtension.class)
@ExtendWith (SpringExtension.class)
class AirplaneTypeServiceTest {

    @MockBean
    private AirplaneTypeRepo airplaneTypeRepo;

    @InjectMocks
    private AirplaneTypeService testerService;

    @Test
    void generalTests() {
        //Initialize testing variables
        AirplaneType airplaneType = new AirplaneType(1L, 20);
        AirplaneType airplaneType2 = new AirplaneType(2L, 25);
        AirplaneType airplaneType3 = new AirplaneType(2L, 35);
        List<AirplaneType> typeList = List.of (airplaneType, airplaneType2);

        //Adding Test
        Mockito.when (airplaneTypeRepo.save(Mockito.any())).thenReturn(airplaneType);
        assertEquals(airplaneType, testerService.addType(airplaneType).getBody());
        Mockito.verify(airplaneTypeRepo).save(airplaneType);

        //get by id test
        Mockito.when (airplaneTypeRepo.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(airplaneType));
        assertEquals(testerService.getTypeById(1L).getBody(), airplaneType);
        Mockito.verify(airplaneTypeRepo).findById(Mockito.anyLong());

        //deletion test
        assertEquals(HttpStatus.OK, testerService.addType(airplaneType).getStatusCode());
        assertEquals(HttpStatus.OK, testerService.deleteType(airplaneType.getId()).getStatusCode());

        //get all testing
        Mockito.when (airplaneTypeRepo.findAll()).thenReturn(typeList);
        assertEquals(testerService.getAllTypes().getBody(), typeList);

        //update testing
        Mockito.when (airplaneTypeRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(airplaneType2));
        Mockito.when (airplaneTypeRepo.save(Mockito.any())).thenReturn(airplaneType3);

        assertEquals(testerService.updateType(airplaneType3).getBody(), airplaneType2);
    }
}