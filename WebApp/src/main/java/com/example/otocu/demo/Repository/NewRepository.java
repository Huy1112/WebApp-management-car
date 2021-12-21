package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewRepository extends JpaRepository<New,Integer> {
    @Query("SELECT n FROM New n WHERE n.id = ?1")
    New findByIdNew(int id);

}
