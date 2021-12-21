package com.example.otocu.demo.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id;
    @Column(name = "name_comment")
    private String name;
    @Column(name = "message")
    private String message;
    @Column(name = "created_date")
    private Date created;
//    @Column(name = "id_new")
    @Transient
    private int idNew;
    @ManyToOne
    @JoinColumn(name = "id_new")
    private New newComment;


}
