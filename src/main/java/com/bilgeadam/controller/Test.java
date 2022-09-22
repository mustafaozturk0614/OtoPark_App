package com.bilgeadam.controller;

import com.bilgeadam.repository.ParkPlaceRepository;
import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.repository.entity.ParkPlace;
import com.bilgeadam.repository.entity.Vehicle;
import com.bilgeadam.service.ParkAreaService;
import com.bilgeadam.service.ParkDetailService;
import com.bilgeadam.service.ParkPlaceService;
import com.bilgeadam.service.VehicleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        VehicleService vehicleService=new VehicleService();
        ParkAreaService parkAreaService=new ParkAreaService();
        ParkPlaceService parkPlaceService=new ParkPlaceService(new ParkPlaceRepository());
        ParkDetailService parkDetailService=new ParkDetailService();


        Vehicle vehicle=Vehicle.builder().brand("Opel").numberPlate("06-xxx-xx").driver("Mustafa").build();
        Vehicle vehicle2=Vehicle.builder().brand("Volskwagen").numberPlate("06-xxx-xx").driver("Mert").build();
        vehicleService.save(vehicle);
        vehicleService.save(vehicle2);

        ParkArea parkArea=ParkArea.builder().capacity(40).isFull(false).location("KIZILAY").build();
        ParkArea parkArea2=ParkArea.builder().capacity(50).isFull(false).location("Bahçelievler").build();
        List<ParkPlace> parkPlaces1=new ArrayList<>();
        List<ParkPlace> parkPlaces2=new ArrayList<>();

        for (int i = 0; i < parkArea.getCapacity(); i++) {
            ParkPlace parkPlace=ParkPlace.builder().isParked(false).parkArea(parkArea).build();
            parkPlaces1.add(parkPlace);
        }
        parkArea.setParkPlaces(parkPlaces1);

        for (int i = 0; i < parkArea2.getCapacity(); i++) {

            ParkPlace parkPlace=ParkPlace.builder().isParked(false).parkArea(parkArea2).build();
            parkPlaces2.add(parkPlace);
        }
        parkArea2.setParkPlaces(parkPlaces2);


        ParkDetail parkDetail=ParkDetail.builder().vehicle(vehicleService.findById(1L).get()).
                startedTime(LocalDateTime.now().minusHours(5))
                .finishedTime(LocalDateTime.now().minusHours(4)).price(20)
                .build();

        ParkDetail parkDetai2=ParkDetail.builder().vehicle(vehicleService.findById(2L).get()).
                startedTime(LocalDateTime.now().minusHours(6))
                .finishedTime(LocalDateTime.now().minusHours(4)).price(30)
                .build();


        parkPlaces1.get(0).getParkDetails().add(parkDetail);
        parkPlaces1.get(1).getParkDetails().add(parkDetai2);

        parkDetailService.save(parkDetail);
        parkDetailService.save(parkDetai2);
        parkAreaService.save(parkArea);
        parkAreaService.save(parkArea2);
    }


}
