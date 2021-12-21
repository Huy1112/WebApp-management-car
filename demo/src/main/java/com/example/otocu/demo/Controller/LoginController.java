package com.example.otocu.demo.Controller;


import com.example.otocu.demo.Model.User;
import com.example.otocu.demo.Repository.UserRepository;
import com.example.otocu.demo.Singleton.UserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"","/","/index"})
    public String index(Model model){
        model.addAttribute("user",new User());
        return "index";
    }
    @PostMapping(value = {"/index"})
    public String userSubmit(@ModelAttribute("user")User user,
                             Model model){
        User user1 = userRepository.getLogin(user.getEmail(),user.getPassword());
        if(user1 == null){
            model.addAttribute("notification","Tài khoản hoặc mật khẩu sai, vui lòng nhập lại !");
            return "index";
        }
        UserInstance.getInstance().setUser(user1);
        model.addAttribute("user",user1);
        return "doc/index";
    }
    @GetMapping("/forgot")
    public String getForgot(){
        return "forgot";
    }

    @GetMapping("/logout")
    public String getLogout(Model model){
        User user = new User();
        UserInstance.getInstance().setUser(user);
        model.addAttribute("user",user);
        return "index";
    }

}
