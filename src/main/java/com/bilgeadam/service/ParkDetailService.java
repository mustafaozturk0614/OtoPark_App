package com.bilgeadam.service;

import com.bilgeadam.repository.ParkAreaRepository;
import com.bilgeadam.repository.ParkDetailRepository;
import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.utility.MyFactoryRepository;
import com.bilgeadam.utility.MyFactoryService;

public class ParkDetailService extends MyFactoryService<MyFactoryRepository , ParkDetail,Long> {


    public ParkDetailService() {
        super(new ParkDetailRepository());
    }
}
