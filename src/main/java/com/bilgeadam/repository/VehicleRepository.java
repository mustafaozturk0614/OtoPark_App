package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Vehicle;
import com.bilgeadam.utility.MyFactoryRepository;

public class VehicleRepository extends MyFactoryRepository<Vehicle,Long> {


    public VehicleRepository() {
        super(new Vehicle());
    }
}
