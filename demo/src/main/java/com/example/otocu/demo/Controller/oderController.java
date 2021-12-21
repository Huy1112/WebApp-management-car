package com.example.otocu.demo.Controller;


import com.example.otocu.demo.Model.*;
import com.example.otocu.demo.ModelData.Oder;
import com.example.otocu.demo.Repository.NewRepository;
import com.example.otocu.demo.Repository.PicCarRepository;
import com.example.otocu.demo.Repository.UserRepository;
import com.example.otocu.demo.Repository.CarRepository;
import com.example.otocu.demo.Singleton.UserInstance;
import com.example.otocu.demo.Singleton.newInstance;
import com.example.otocu.demo.utils.FileUploadUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/doc")
public class oderController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PicCarRepository picCarRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewRepository newRepository;


    @GetMapping("/table-data-oder")
    public String index(Model model) {
        model.addAttribute("news", newRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-oder";
    }

    @GetMapping("/form-add-don-hang")
    public String indexOder(Model model, Oder oder) {
        oder.setNameUser(UserInstance.getInstance().getUser().getName());
        model.addAttribute("oder", oder);
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-add-don-hang";
    }

    @PostMapping("/form-add-don-hang")
    public String saveOder(@ModelAttribute("oder")@Valid Oder oder, BindingResult rs,
                           Model model, New newCreate) throws IOException {


        Car car = new Car(oder.getNameCar(),oder.getCreate_car(),oder.getNation(),oder.getForm(),oder.getState(),oder.getGear(),oder.getFuel(),oder.getGone(),oder.getQuality(),oder.getPrice(),oder.getCity(),oder.getBrand());
        carRepository.save(car);
        PicCar picCar = new PicCar();
        MultipartFile multipartFile = oder.getFile1();
        MultipartFile multipartFile1 = oder.getFile2();
        MultipartFile multipartFile2 = oder.getFile3();

        if (multipartFile.isEmpty() && multipartFile1.isEmpty() && multipartFile2.isEmpty() ){
            model.addAttribute("oder",oder);
            model.addAttribute("user",UserInstance.getInstance().getUser());
            model.addAttribute("error", "Upload image entire 3 image of car to create New");
            return "doc/form-add-don-hang";
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        picCar.setName(fileName);
        picCar.setCar1(car);
        picCarRepository.save(picCar);
        String uploadDir = "src/main/resources/static/car-photos/" + picCar.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        PicCar picCar1 = new PicCar();

        String fileName1 = StringUtils.cleanPath(Objects.requireNonNull(multipartFile1.getOriginalFilename()));
        picCar1.setName(fileName1);
        picCar1.setCar1(car);
        picCarRepository.save(picCar1);
        String uploadDir1 = "src/main/resources/static/car-photos/" + picCar1.getId();
        FileUploadUtil.saveFile(uploadDir1, fileName1, multipartFile1);


        PicCar picCar2 = new PicCar();

        String fileName2 = StringUtils.cleanPath(Objects.requireNonNull(multipartFile2.getOriginalFilename()));
        picCar2.setName(fileName2);
        picCar2.setCar1(car);
        picCarRepository.save(picCar2);
        String uploadDir2 = "src/main/resources/static/car-photos/" + picCar2.getId();
        FileUploadUtil.saveFile(uploadDir2, fileName2, multipartFile2);

        // userRepository.finndName(oder.getNameUser())
        User user = UserInstance.getInstance().getUser();
        //validation abundan
//        if( user == null){
//            model.addAttribute("oder" , oder);
//            model.addAttribute("notfound1","Cant found user with name like that");
//            return "doc/form-add-don-hang";
//        }


        if(rs.hasErrors()){
            model.addAttribute("oder",oder);
            model.addAttribute("user",UserInstance.getInstance().getUser());
            return "doc/form-add-don-hang";
        }

        newCreate.setTitle(oder.getTitle());
        newCreate.setDescription(oder.getDes());
        newCreate.setSupport("chua");
        newCreate.setCarNew(car);
        newCreate.setUser(user);

        newRepository.save(newCreate);
        model.addAttribute("news", newRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-oder";
    }

    @GetMapping("/deleteNew/{id}")
    public String delete(@PathVariable("id") int id,Model model){
        New new2 = newRepository.getOne(id);
        Car car = new2.getCarNew();
        List<PicCar> picCars = car.getPicCars();
        String filename1 = "src/main/resources/static/car-photos/"+picCars.get(0).getId()+"/" + picCars.get(0).getName();
        String filename2 = "src/main/resources/static/car-photos/"+picCars.get(1).getId()+"/" + picCars.get(1).getName();
        String filename3 = "src/main/resources/static/car-photos/"+picCars.get(2).getId()+"/" + picCars.get(2).getName();

        String filename11 = "src/main/resources/static/car-photos/"+picCars.get(0).getId();
        String filename22 = "src/main/resources/static/car-photos/"+picCars.get(1).getId();
        String filename33 = "src/main/resources/static/car-photos/"+picCars.get(2).getId();
        try {
            boolean result = Files.deleteIfExists(Paths.get(filename1));
            boolean result1 = Files.deleteIfExists(Paths.get(filename2));
            boolean result2 = Files.deleteIfExists(Paths.get(filename3));
            Files.deleteIfExists(Paths.get(filename11));
            Files.deleteIfExists(Paths.get(filename22));
            Files.deleteIfExists(Paths.get(filename33));
            if(!result && !result1 && !result2) {
                model.addAttribute("news" , newRepository.findAll());
                model.addAttribute("user",UserInstance.getInstance().getUser());
                return "doc/data-table-oder";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        picCarRepository.deleteAll(picCars);
        newRepository.delete(new2);
        model.addAttribute("news", newRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-oder";
    }
    @GetMapping("/form-edit-don-hang/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        New new1 = newRepository.findByIdNew(id);
        newInstance.getInstance().setRs(new1);
        User user = new1.getUser();
        Car car = new1.getCarNew();
        List<PicCar> picCars = car.getPicCars();
        model.addAttribute("piccar" ,picCars.get(0));
        model.addAttribute("piccar1" ,picCars.get(1));
        model.addAttribute("piccar2" ,picCars.get(2));
        Oder oder = new Oder(car.getName(), car.getCreate_car(), car.getNation(), car.getForm(), car.getState(), car.getGear(), car.getFuel(), car.getGone(), car.getQuality(), car.getPrice(), car.getCity(), car.getBrand(), new1.getTitle(), new1.getDescription(), new1.getSupport() , user.getName());
        model.addAttribute("oderEdited", oder);
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-edit-don-hang";
    }
    @PostMapping("/form-edit-don-hang")
    public String updateUser(@ModelAttribute("oderEdited") Oder oder
                            , Model model) throws IOException {
        New newEdit = newInstance.getInstance().getRs();
        Car car = newEdit.getCarNew();
        car.setName(oder.getNameCar());
        car.setCreate_car(oder.getCreate_car());
        car.setNation(oder.getNation());
        car.setForm(oder.getForm());
        car.setState(oder.getState());
        car.setGear(oder.getGear());
        car.setFuel(oder.getFuel());
        car.setGone(oder.getGone());
        car.setQuality(oder.getQuality());
        car.setPrice(oder.getPrice());
        car.setCity(oder.getCity());
        car.setBrand(oder.getBrand());
        carRepository.save(car);
        List<PicCar> picCars = car.getPicCars();
        MultipartFile multipartFile = oder.getFile1();
        MultipartFile multipartFile1 = oder.getFile2();
        MultipartFile multipartFile2 = oder.getFile3();
        if(multipartFile.isEmpty() && multipartFile1.isEmpty() && multipartFile2.isEmpty()){
            model.addAttribute("oderEdited",oder);
            model.addAttribute("piccar" ,picCars.get(0));
            model.addAttribute("piccar1" ,picCars.get(1));
            model.addAttribute("piccar2" ,picCars.get(2));
            model.addAttribute("user",UserInstance.getInstance().getUser());
            model.addAttribute("error", "Upload image entire 3 image of car to edit New");
            return "doc/form-edit-don-hang";
        }

        String filename1 = "src/main/resources/static/car-photos/"+picCars.get(0).getId()+"/" + picCars.get(0).getName();
        String filename2 = "src/main/resources/static/car-photos/"+picCars.get(1).getId()+"/" + picCars.get(1).getName();
        String filename3 = "src/main/resources/static/car-photos/"+picCars.get(2).getId()+"/" + picCars.get(2).getName();

        String filename11 = "src/main/resources/static/car-photos/"+picCars.get(0).getId();
        String filename22 = "src/main/resources/static/car-photos/"+picCars.get(1).getId();
        String filename33 = "src/main/resources/static/car-photos/"+picCars.get(2).getId();

        try {
            boolean result = Files.deleteIfExists(Paths.get(filename1));
            boolean result1 = Files.deleteIfExists(Paths.get(filename2));
            boolean result2 = Files.deleteIfExists(Paths.get(filename3));

            Files.deleteIfExists(Paths.get(filename11));
            Files.deleteIfExists(Paths.get(filename22));
            Files.deleteIfExists(Paths.get(filename33));
             if(!result && !result1 && !result2) {
                 model.addAttribute("noti","Sorry, unable to delete the file.");
                 model.addAttribute("oderEdited",oder);
                 model.addAttribute("piccar" ,picCars.get(0));
                 model.addAttribute("piccar1" ,picCars.get(1));
                 model.addAttribute("piccar2" ,picCars.get(2));
                 model.addAttribute("user",UserInstance.getInstance().getUser());
                 return "doc/form-edit-don-hang";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        picCars.get(0).setName(fileName);
        picCars.get(0).setCar1(car);
        picCarRepository.save(picCars.get(0));
        String uploadDir = "src/main/resources/static/car-photos/" + picCars.get(0).getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        String fileName1 = StringUtils.cleanPath(Objects.requireNonNull(multipartFile1.getOriginalFilename()));
        picCars.get(1).setName(fileName1);
        picCars.get(1).setCar1(car);
        picCarRepository.save(picCars.get(1));
        String uploadDir1 = "src/main/resources/static/car-photos/" + picCars.get(1).getId();
        FileUploadUtil.saveFile(uploadDir1, fileName1, multipartFile1);

        String fileName2 = StringUtils.cleanPath(Objects.requireNonNull(multipartFile2.getOriginalFilename()));
        picCars.get(2).setName(fileName2);
        picCars.get(2).setCar1(car);
        picCarRepository.save(picCars.get(2));
        String uploadDir2 = "src/main/resources/static/car-photos/" + picCars.get(2).getId();
        FileUploadUtil.saveFile(uploadDir2, fileName2, multipartFile2);
//        userRepository.finndName(oder.getNameUser())
        User user = UserInstance.getInstance().getUser();
//        if( user == null){
//
//            model.addAttribute("piccar" ,picCars.get(0));
//            model.addAttribute("piccar1" ,picCars.get(1));
//            model.addAttribute("piccar2" ,picCars.get(2));
//            model.addAttribute("oderEdited" , oder);
//            model.addAttribute("user",user_logging);
//            model.addAttribute("notfound1","Cant found user with name like that");
//            return "doc/form-edit-don-hang";
//        }
        newEdit.setTitle(oder.getTitle());
        newEdit.setDescription(oder.getDes());
        newEdit.setSupport(oder.getSupport());
        newEdit.setCarNew(car);
        newEdit.setUser(user);
        newRepository.save(newEdit);
        model.addAttribute("news",newRepository.findAll());
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/table-data-oder";
    }
    @GetMapping("/form-edit-car/{id}")
    public String getCar(@PathVariable("id") int id, Model model){
        Car car = carRepository.getOne(id);
        List<PicCar> picCars =  car.getPicCars();
        model.addAttribute("carEdit",car);
        model.addAttribute("piccar" ,picCars.get(0));
        model.addAttribute("piccar1" ,picCars.get(1));
        model.addAttribute("piccar2" ,picCars.get(2));
        model.addAttribute("user",UserInstance.getInstance().getUser());
        return "doc/form-edit-car";
    }




}
