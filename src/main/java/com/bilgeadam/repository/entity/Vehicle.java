package com.bilgeadam.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String numberPlate;
    private String driver;
    @OneToMany(mappedBy = "vehicle")
    List<ParkDetail> parkDetails;


}
