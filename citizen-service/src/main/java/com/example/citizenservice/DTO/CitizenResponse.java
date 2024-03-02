package com.example.citizenservice.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenResponse {
    int id;
    String name;
    int vaccinate_center_id;
}
