package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.Car;
import com.example.otocu.demo.Model.PicCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PicCarRepository extends JpaRepository<PicCar,Integer> {
//
//    @Query("Select p from Car c, PicCar p WHERE c.id = p.idCar AND c = ?1")
//    PicCar getPicByIdCar(Car car);






}
