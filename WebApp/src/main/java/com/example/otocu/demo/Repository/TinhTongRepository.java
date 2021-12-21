package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TinhTongRepository extends JpaRepository<HoaDon,Integer> {

    @Query("SELECT SUM(t.totalPrice) FROM HoaDon t")
    long getSum();



//    SELECT n.title, t.quantity , t.total_price, l.name FROM News n,loai_tin l, TinhTong t WHERE n.id_new = t.id_new AND t.id_loaitin = l.id AND n.id_new = 3
}
