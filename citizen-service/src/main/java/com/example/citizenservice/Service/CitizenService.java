package com.example.citizenservice.Service;

import com.example.citizenservice.Entity.Citizen;
import com.example.citizenservice.Repository.CitizenRepository;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {
    @Autowired
    CitizenRepository citizenRepository;

    public Optional<Citizen> getCitizenById(int id){
        return citizenRepository.findById(id);
    }

    public Iterable<Citizen> getAllCitizens(){
        return citizenRepository.findAll();
    }

    public Citizen saveCitizen(Citizen citizen){
        return citizenRepository.save(citizen);
    }

    public void deleteAllCitizenRecords(){
        citizenRepository.deleteAll();
    }
}
