package com.bilgeadam.service;

import com.bilgeadam.repository.ParkAreaRepository;
import com.bilgeadam.repository.VehicleRepository;
import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.repository.entity.Vehicle;
import com.bilgeadam.utility.MyFactoryRepository;
import com.bilgeadam.utility.MyFactoryService;

public class VehicleService extends MyFactoryService<MyFactoryRepository , Vehicle,Long> {


    public VehicleService() {
        super(new VehicleRepository());
    }
}
