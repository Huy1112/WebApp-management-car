package com.example.otocu.demo.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cat_user")
@Data
public class CatUser {
    @Id
    @Column(name = "id_cat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "catUser",cascade = CascadeType.ALL)
    private List<User> usersList;


}
