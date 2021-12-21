package com.example.otocu.demo.Controller;


import com.example.otocu.demo.Model.CatUser;
import com.example.otocu.demo.Model.User;
import com.example.otocu.demo.Repository.CatUserRepository;
import com.example.otocu.demo.Repository.UserRepository;
import com.example.otocu.demo.Singleton.UserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/doc")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatUserRepository catUserRepository;
    public static int numberUser = 0;

    @GetMapping("/table-data-table")
    public String getAll(Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-table";
    }
    @GetMapping("/form-add-nhan-vien")
    public String getAddUser(User user, Model model, CatUser catUser){
        model.addAttribute("cat" , catUser);
        model.addAttribute("userCreate", user);
        model.addAttribute("cats", catUserRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-add-nhan-vien";
    }
    @PostMapping("/form-add-nhan-vien")
    public String addUser(@ModelAttribute("userCreate")@Valid User user, BindingResult rs, Model model,CatUser catUser){
        if (rs.hasErrors()){
            model.addAttribute("cat" , catUser);
            model.addAttribute("userCreate", user);
            model.addAttribute("cats", catUserRepository.findAll());
            model.addAttribute("user",UserInstance.getInstance().getUser());
            return "doc/form-add-nhan-vien";
        }
        CatUser catUserSaved = catUserRepository.getOne(user.getIdCat());
        user.setCatUser(catUserSaved);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-table";
    }
    @PostMapping("/add-cat-user")
    public String addCat(@ModelAttribute("cat") CatUser catUser, Model model, User user){
        catUserRepository.save(catUser);
        model.addAttribute("userCreate" , user);
        model.addAttribute("cats" , catUserRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-add-nhan-vien";
    }
    @GetMapping("/form-edit-nhan-vien/{id}")
    public String editUser(@PathVariable("id") int id,Model model){
        User user = userRepository.getOne(id);
        CatUser catUser = user.getCatUser();
        user.setIdCat(catUser.getId());
        numberUser = id;
        model.addAttribute("userEdited",user);
        model.addAttribute("cats",catUserRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-edit-nhan-vien";
    }
    @PostMapping("/form-edit-nhan-vien")
    public String updateUser(@ModelAttribute("userEdited")@Valid User user1,BindingResult rs, Model model){
        User user = userRepository.getOne(numberUser);
        if (rs.hasErrors()){
            CatUser catUser = user.getCatUser();
            user.setIdCat(catUser.getId());
            model.addAttribute("userCreate", user);
            model.addAttribute("user",UserInstance.getInstance().getUser());
            model.addAttribute("cats", catUserRepository.findAll());
            return "doc/form-edit-nhan-vien";
        }
        CatUser catUser = catUserRepository.getOne(user1.getIdCat());
        user.setName(user1.getName());
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        user.setSdt(user1.getSdt());
        user.setCatUser(catUser);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-table";
    }
    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        userRepository.deleteById(id);
        return "doc/table-data-table";
    }
}
