package com.example.otocu.demo.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "loai_tin")
public class LoaiTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "The name of product can't be null")
    private String name;

    @Column(name = "price")
    @NotNull
    @Min(value = 1000)
    private int price;
    @Column(name = "upload")
    private String upload;
//
//    @OneToMany(mappedBy = "loaiTin")
//    private List<TinhTong> tinhTongs;

}
