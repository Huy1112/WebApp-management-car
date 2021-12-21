package com.example.otocu.demo.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "car")
@Data
@ToString
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "create_car")
    private String create_car;
    @Column(name = "nation")
    private String nation;
    @Column(name = "form")
    private String form;
    @Column(name = "state")
    private String state;
    @Column(name = "gear")
    private String gear;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "gone")
    private String gone;
    @Column(name = "quality")
    private String quality;
    @Column(name = "price")
    private String price;
    @Column(name = "city")
    private String city;
    @Column(name = "brand")
    private String brand;
    @OneToMany(mappedBy = "car1",cascade = CascadeType.ALL)
    private List<PicCar> picCars;
    @OneToOne(mappedBy = "carNew",cascade = CascadeType.ALL)
    private New news;

    public Car() {
    }

    public Car(int id, String name, String create_car, String nation, String form, String state, String gear, String fuel, String gone, String quality, String price, String city, String brand) {
        this.id = id;
        this.name = name;
        this.create_car = create_car;
        this.nation = nation;
        this.form = form;
        this.state = state;
        this.gear = gear;
        this.fuel = fuel;
        this.gone = gone;
        this.quality = quality;
        this.price = price;
        this.city = city;
        this.brand = brand;
    }

    public Car(String name, String create_car, String nation, String form, String state, String gear, String fuel, String gone, String quality, String price, String city, String brand) {
        this.name = name;
        this.create_car = create_car;
        this.nation = nation;
        this.form = form;
        this.state = state;
        this.gear = gear;
        this.fuel = fuel;
        this.gone = gone;
        this.quality = quality;
        this.price = price;
        this.city = city;
        this.brand = brand;
    }

}
