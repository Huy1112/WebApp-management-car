package com.example.otocu.demo.Model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "The name of user can't be null")
    @Size(min = 5,max = 20)
    private String name;

    @Column(name = "sdt")
    @NotNull
    private int sdt;

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;
    @Transient
    private int idCat;
    @ManyToOne
    @JoinColumn(name = "id_cat")
    private CatUser catUser;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<New> news;
}
