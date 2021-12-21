package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {



//    SELECT * FROM News n,comment c WHERE c.id_new = n.id_new AND n.id_car = 20
}
