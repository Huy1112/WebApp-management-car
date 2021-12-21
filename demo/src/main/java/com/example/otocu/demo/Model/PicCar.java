package com.example.otocu.demo.Model;

import com.sun.tools.javah.Gen;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_pic")
public class PicCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pic")
    private int id;
    @Column(name = "name")
    private String name;
    @Transient
    private int idCar;
    @ManyToOne
    @JoinColumn(name = "id_car ")
    private Car car1;

}
