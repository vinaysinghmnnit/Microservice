package com.example.citizenservice.Repository;

import com.example.citizenservice.Entity.Citizen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen, Integer> {
}
