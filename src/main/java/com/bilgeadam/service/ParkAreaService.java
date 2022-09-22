package com.bilgeadam.service;

import com.bilgeadam.repository.ParkAreaRepository;
import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.utility.MyFactoryRepository;
import com.bilgeadam.utility.MyFactoryService;

public class ParkAreaService  extends MyFactoryService<MyFactoryRepository , ParkArea,Long> {


    public ParkAreaService() {
        super(new ParkAreaRepository());
    }
}
