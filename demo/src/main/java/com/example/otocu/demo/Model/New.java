package com.example.otocu.demo.Model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "News")
@ToString
public class New{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_new")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "supportfordrive")
    private String support;

    @Transient
    private int idUser;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car carNew;

    @OneToMany(mappedBy = "newComment",cascade = CascadeType.ALL)
    private List<Comment> comments;




}
