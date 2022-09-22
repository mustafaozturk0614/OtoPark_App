package com.bilgeadam.service;

import com.bilgeadam.repository.ParkPlaceRepository;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.repository.entity.ParkPlace;
import com.bilgeadam.utility.MyFactoryRepository;
import com.bilgeadam.utility.MyFactoryService;

import java.util.List;

public class ParkPlaceService extends MyFactoryService<MyFactoryRepository , ParkPlace,Long> {

    ParkPlaceRepository repository;
    public ParkPlaceService(ParkPlaceRepository repository) {

        super(repository);
        this.repository=repository;
    }

    public List<ParkPlace> findByIdAndIsParked(Long id) {

     return  repository.findByIdAndIsParked(id);
    }

    public ParkPlace selectParkPlace(Long id, ParkDetail parkDetail) {
            ParkPlace parkPlace=repository.findById(id).get();
            parkPlace.setParked(true);
            parkPlace.getParkDetails().add(parkDetail);
           return repository.save(parkPlace);

    }
}
