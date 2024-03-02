package com.example.citizenservice.Controller;

import com.example.citizenservice.DTO.CitizenResponse;
import com.example.citizenservice.Entity.Citizen;
import com.example.citizenservice.Service.CitizenService;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
    @Autowired
    CitizenService citizenService;
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable int id){
        Optional<Citizen> citizenOptional = citizenService.getCitizenById(id);
        if(! citizenOptional.isEmpty()){
            Citizen citizen = citizenOptional.get();
            CitizenResponse citizenResponse = new CitizenResponse(citizen.getId(), citizen.getName(), citizen.getVaccinationCenterId());
            return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/id", produces = "application/json")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens(){
        Iterable<Citizen> citizenIterable = citizenService.getAllCitizens();
        List<CitizenResponse> citizenResponses = StreamSupport.stream(citizenIterable.spliterator(), false).
            map(citizen -> new CitizenResponse(citizen.getId(), citizen.getName(), citizen.getVaccinationCenterId()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(citizenResponses);
    }

    @PostMapping(value = "save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CitizenResponse> saveCitizenData(@RequestBody Citizen input){
        Citizen citizen = citizenService.saveCitizen(input);
        if(citizen != null){
            CitizenResponse citizenResponse = new CitizenResponse(
                citizen.getId(),
                citizen.getName(),
                citizen.getVaccinationCenterId()
            );

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(citizen.getId())
                .toUri();

            return ResponseEntity.created(location).body(citizenResponse);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllCitizenRecords(){
        citizenService.deleteAllCitizenRecords();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted all the citizen records");
    }
}
