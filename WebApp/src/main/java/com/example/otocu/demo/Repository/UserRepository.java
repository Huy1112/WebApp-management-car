package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u from User u WHERE u.email = ?1 AND u.name = ?2")
    User findUser( String email , String name);
    @Query("SELECT u from User u WHERE u.name = ?1")
    User finndName(String name);
    @Query("SELECT SUM(u.catUser.id) FROM User u WHERE u.catUser.id = 1")
    int getSumUser();
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User getLogin(String email,String password);
}
