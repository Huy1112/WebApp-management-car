package com.example.otocu.demo.Controller;


import com.example.otocu.demo.Model.LoaiTin;
import com.example.otocu.demo.Model.New;
import com.example.otocu.demo.Model.HoaDon;
import com.example.otocu.demo.Model.User;
import com.example.otocu.demo.Repository.LoaiTinRepository;
import com.example.otocu.demo.Repository.NewRepository;
import com.example.otocu.demo.Repository.TinhTongRepository;
import com.example.otocu.demo.Singleton.UserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/doc")
public class ReceiptController {

    @Autowired
    private TinhTongRepository tinhTongRepository;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private LoaiTinRepository loaiTinRepository;



    public static int numberTinhTong = 0;

    @GetMapping("/table-data-hoa-don")
    public String index(Model model){
        model.addAttribute("user",UserInstance.getInstance().getUser());
        model.addAttribute("hoadons",tinhTongRepository.findAll());
        return "doc/table-data-hoa-don";
    }
    @GetMapping("/form-add-hoa-don")
    public String getForm(Model model, HoaDon hoadon){

        User user = UserInstance.getInstance().getUser();
//        if(Objects.equals(user.getCatUser().getName(), "Admin")){
//            List<New> news = newRepository.findAll();
//            List<LoaiTin> loaiTins = loaiTinRepository.findAll();
//            model.addAttribute("hoadon" , hoadon);
//            model.addAttribute("news",news);
//            model.addAttribute("loaitins" ,loaiTins);
//
//            return "doc/form-add-hoa-don";
//        }else {
            List<New> news = user.getNews();
            List<LoaiTin> loaiTins = loaiTinRepository.findAll();
            model.addAttribute("hoadon" , hoadon);
            model.addAttribute("news",news);
            model.addAttribute("loaitins" ,loaiTins);
            model.addAttribute("user",UserInstance.getInstance().getUser());

            return "doc/form-add-hoa-don";
//        }
    }
    @PostMapping("/form-add-hoa-don")
    public String save(@ModelAttribute("hoadon")@Valid HoaDon hoadon, BindingResult rs, Model model){
        if(rs.hasErrors()){
            User user = UserInstance.getInstance().getUser();
            List<New> news = user.getNews();
            List<LoaiTin> loaiTins = loaiTinRepository.findAll();
            model.addAttribute("hoadon" , hoadon);
            model.addAttribute("news",news);
            model.addAttribute("loaitins" ,loaiTins);
            model.addAttribute("user",UserInstance.getInstance().getUser());
            return "doc/form-add-hoa-don";
        }
        New new1 = newRepository.getOne(hoadon.getIdNew());
        new1.setSupport("Đã thanh toán");
        LoaiTin loaiTin = loaiTinRepository.getOne(hoadon.getIdLoaiTin());
        hoadon.setLoaiTin(loaiTin);
        hoadon.setNewTinhTong(new1);
        hoadon.setState(hoadon.getState());
        hoadon.setTotalPrice(hoadon.getQuantity()*loaiTin.getPrice());
        tinhTongRepository.save(hoadon);
        model.addAttribute("hoadons",tinhTongRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-hoa-don";
    }

    @GetMapping("/form-edit-hoa-don/{id}")
    public String getHoaDon(@PathVariable("id") int id,Model model){
        HoaDon hoadon = tinhTongRepository.getOne(id);
        numberTinhTong = id;
        hoadon.setIdNew(hoadon.getNewTinhTong().getId());
        hoadon.setIdLoaiTin(hoadon.getLoaiTin().getId());
        User user = UserInstance.getInstance().getUser();
        model.addAttribute("news",user.getNews());
        model.addAttribute("loaitins" ,loaiTinRepository.findAll());
        model.addAttribute("hoadon", hoadon);
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-edit-hoa-don";
    }
    @PostMapping("/form-edit-hoa-don")
    public String editHoadon(@ModelAttribute("hoadon")@Valid HoaDon hoadon,BindingResult errors, Model model){
        if (errors.hasErrors()){
            User user = UserInstance.getInstance().getUser();
            hoadon.setIdNew(hoadon.getNewTinhTong().getId());
            hoadon.setIdLoaiTin(hoadon.getLoaiTin().getId());
            model.addAttribute("news",user.getNews());
            model.addAttribute("loaitins" ,loaiTinRepository.findAll());
            model.addAttribute("hoadon", hoadon);
            model.addAttribute("user",UserInstance.getInstance().getUser());
            return "doc/form-edit-hoa-don";
        }
        HoaDon rs = tinhTongRepository.getOne(numberTinhTong);
        New new1 = rs.getNewTinhTong();
        LoaiTin loaiTin = rs.getLoaiTin();
        rs.setState(hoadon.getState());
        rs.setTotalPrice(hoadon.getQuantity()* loaiTin.getPrice());
        rs.setNewTinhTong(new1);
        rs.setLoaiTin(loaiTin);
        rs.setQuantity(hoadon.getQuantity());
        tinhTongRepository.save(rs);
        model.addAttribute("hoadons",tinhTongRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-hoa-don";
    }
    @GetMapping("/delete-hoa-don/{id}")
    public String deleteHoadon(@PathVariable("id")int id,Model model){
        tinhTongRepository.deleteById(id);
        model.addAttribute("hoadons", tinhTongRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-hoa-don";
    }

}
