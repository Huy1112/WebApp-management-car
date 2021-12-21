package com.example.otocu.demo.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tinh_tong")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "quantity")
    @NotNull
    private int quantity;
    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "state")
    @NotNull
    private String state;

    @Transient
    @NotNull
    private int idNew;

    @ManyToOne
    @JoinColumn(name = "id_new")
    private New newTinhTong;

    @Transient
    @NotNull
    private int idLoaiTin;

    @ManyToOne
    @JoinColumn(name = "id_loaitin")
    private LoaiTin loaiTin;


}
