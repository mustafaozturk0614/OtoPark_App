package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.repository.entity.Vehicle;
import com.bilgeadam.utility.MyFactoryRepository;

public class ParkDetailRepository extends MyFactoryRepository<ParkDetail,Long> {


    public ParkDetailRepository() {
        super(new ParkDetail());
    }
}
