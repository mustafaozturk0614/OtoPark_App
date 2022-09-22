package com.bilgeadam.controller;

import com.bilgeadam.repository.ParkPlaceRepository;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.repository.entity.ParkPlace;
import com.bilgeadam.service.ParkAreaService;
import com.bilgeadam.service.ParkDetailService;
import com.bilgeadam.service.ParkPlaceService;
import com.bilgeadam.service.VehicleService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    Scanner scanner=new Scanner(System.in);
    VehicleService vehicleService=new VehicleService();
    ParkAreaService parkAreaService=new ParkAreaService();
    ParkPlaceService parkPlaceService=new ParkPlaceService(new ParkPlaceRepository());
    ParkDetailService parkDetailService=new ParkDetailService();



    public void menu (){

        int input=0;
        do {
            parkAreaService.findAll().forEach(s-> System.out.println(s.getId()+"-"+s.getLocation()));
            getAvailableParkPlace();

            exitParkPlace(selectedParkPlace());


        }while (input!=0);

    }

    private void exitParkPlace(ParkPlace parkPlace) {
//        int input=0;
//        do {
//            System.out.println("Park alanını terk etmek istiyormusunuz");
//            input=Integer.parseInt(scanner.nextLine());
//        }while (input!=0);
//        parkPlace.setParked(false);
//        parkPlace.getParkDetails().get(parkPlace.getParkDetails().size()-1).setFinishedTime(LocalDateTime.now());
//    parkPlaceService.save(parkPlace);
//    parkDetailService.save(parkPlace.getParkDetails().get(parkPlace.getParkDetails().size()-1));

    }

    private ParkPlace selectedParkPlace() {
        System.out.println("Bir yer seçiniz");
        Long id= Long.parseLong(scanner.nextLine());
        ParkDetail parkDetail=ParkDetail.builder().startedTime(LocalDateTime.now())
                .vehicle(vehicleService.findById(1L).get())
                .build();
              parkDetailService.save(parkDetail);
      ParkPlace parkPlace= parkPlaceService.selectParkPlace(id,parkDetail);
      return  parkPlace;
    }

    private void getAvailableParkPlace() {
        System.out.println("Bir Park Alanı seçiniz");
        Long id= Long.parseLong(scanner.nextLine());
        parkPlaceService.findByIdAndIsParked(id).forEach(s-> System.out.println(s.getId()+"-"+s.isParked()));
    }


    public static void main(String[] args) {
        Menu menu=new Menu();
        menu.menu();
    }


}
