package vn.edu.iuh.fit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.laptopshop.domain.User;
import vn.edu.iuh.fit.laptopshop.service.impl.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        return "admin/user/table-user";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String getCreateUserPage(Model model, @ModelAttribute("newUser") User newUser){
        userService.save(newUser);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getViewUser(Model model, @PathVariable("id") long id){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "/admin/user/show";
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
