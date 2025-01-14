package vn.edu.iuh.fit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.iuh.fit.laptopshop.domain.User;
import vn.edu.iuh.fit.laptopshop.service.impl.UserService;

import java.util.List;

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
}
