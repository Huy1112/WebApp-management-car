package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {

}
