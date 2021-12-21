package com.example.otocu.demo.Repository;

import com.example.otocu.demo.Model.LoaiTin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoaiTinRepository extends JpaRepository<LoaiTin,Integer> {


//    @Query("SELECT l FROM LoaiTin l, HoaDon t WHERE t.loaiTin.id = l.id ORDER BY t.LoaiTin.id")
//    Iterable<LoaiTin> findAllWithBest();
}
