package com.example.otocu.demo.Controller;


import com.example.otocu.demo.Model.HoaDon;
import com.example.otocu.demo.Model.LoaiTin;
import com.example.otocu.demo.Repository.LoaiTinRepository;
import com.example.otocu.demo.Repository.NewRepository;
import com.example.otocu.demo.Repository.TinhTongRepository;
import com.example.otocu.demo.Repository.UserRepository;
import com.example.otocu.demo.Singleton.UserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/doc")
public class BaoCaoController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private LoaiTinRepository loaiTinRepository;
    @Autowired
    private TinhTongRepository tinhTongRepository;

    @GetMapping("/quan-ly-bao-cao")
    public String index(Model model) {
        int sumUser = userRepository.getSumUser();
        long sumProduct = loaiTinRepository.count();
        long sumNew = newRepository.count();
        long sumIncome = tinhTongRepository.getSum();
        List<HoaDon> hoaDons = tinhTongRepository.findAll();
        List<LoaiTin> loaiTinList = loaiTinRepository.findAll();
        model.addAttribute("sumIncome",sumIncome);
        model.addAttribute("sumNew", sumNew);
        model.addAttribute("sumProduct",sumProduct);
        model.addAttribute("sumUser", sumUser);
        model.addAttribute("hoadons",hoaDons);
        model.addAttribute("loaitins" , loaiTinList);
        model.addAttribute("user", UserInstance.getInstance().getUser());
        return "doc/quan-ly-bao-cao";
    }
}
