package com.bilgeadam.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  int capacity;
    private boolean isFull;
    private String location;

    @OneToMany(mappedBy = "parkArea",cascade = CascadeType.ALL)
    List<ParkPlace> parkPlaces;




}
