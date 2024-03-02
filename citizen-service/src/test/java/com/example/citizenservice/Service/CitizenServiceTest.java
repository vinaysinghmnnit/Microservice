package com.example.citizenservice.Service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.citizenservice.Entity.Citizen;
import com.example.citizenservice.Repository.CitizenRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CitizenServiceTest {
    @Mock
    CitizenRepository citizenRepository;
    @InjectMocks
    CitizenService citizenService;

    @Test
    @DisplayName("Testing Citizen By ID")
    public void testGetCitizenById(){
        int id = 1;
        Citizen citizen = new Citizen();
        citizen.setId(id);
        when(citizenRepository.findById(id)).thenReturn(Optional.of(citizen));
        Optional<Citizen> result = citizenService.getCitizenById(id);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void testGetCitizens(){
        Iterable<Citizen> citizenIterable = Arrays.asList(new Citizen(1,"John Dae",456),
            new Citizen(2, "John Cena",456));
        when(citizenRepository.findAll()).thenReturn(citizenIterable);
        List<Citizen> results = (List)citizenService.getAllCitizens();
        assertEquals(2, results.size());
        assertEquals("John Dae", results.get(0).getName());
        assertEquals("John Cena", results.get(1).getName());
    }
}
