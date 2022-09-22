package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.utility.MyFactoryRepository;

public class ParkAreaRepository extends MyFactoryRepository<ParkArea,Long> {


    public ParkAreaRepository() {
        super(new ParkArea());
    }
}
