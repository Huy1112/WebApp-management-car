package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.CatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatUserRepository extends JpaRepository<CatUser,Integer> {
}
