package vn.edu.iuh.fit.laptopshop.controller.admin;

import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.laptopshop.domain.Role;
import vn.edu.iuh.fit.laptopshop.domain.User;
import vn.edu.iuh.fit.laptopshop.repository.RoleRepository;
import vn.edu.iuh.fit.laptopshop.service.impl.UploadService;
import vn.edu.iuh.fit.laptopshop.service.impl.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @RequestMapping
    public String index(Model model){
        String message = "Hello Spring Boot + JSP";
        model.addAttribute("message", message);
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }


    @RequestMapping("/admin/user")
    public String getAllUser(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @PostMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model, @ModelAttribute("newUser") User newUser, @RequestParam("file") MultipartFile file){


        String avatar =  uploadService.handleSaveUploadFile(file, "avatar");
        String hasdPassword = passwordEncoder.encode(newUser.getPassword());
        Role role = roleRepository.findByName(newUser.getRole().getName());

        newUser.setAvatar(avatar);
        newUser.setPassword(hasdPassword);
        newUser.setRole(role);


        userService.save(newUser);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getViewUser(Model model, @PathVariable("id") long id){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "/admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable("id") long id){
        User currentUser = userService.findById(id);
        model.addAttribute("newUser", currentUser);
        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("newUser") User user){
        User currentUser = userService.findById(user.getId());
        if(currentUser != null){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            currentUser.setPassword(currentUser.getPassword());


            userService.save(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable("id") long id){
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("newUser") User user){
            userService.deleteById(user.getId());
//        System.out.println(user.getId());
        return "redirect:/admin/user";
    }



}
