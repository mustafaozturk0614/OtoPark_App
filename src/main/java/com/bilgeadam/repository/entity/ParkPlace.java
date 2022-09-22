package com.bilgeadam.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  boolean isParked;
    @ManyToOne
    ParkArea parkArea;
    @OneToMany
    @JoinColumn(name = "park_place_id",referencedColumnName = "id")
    List<ParkDetail> parkDetails;


    public List<ParkDetail> getParkDetails() {
        if (parkDetails==null){
            parkDetails=new ArrayList<>();
        }
        return parkDetails;
    }
}
