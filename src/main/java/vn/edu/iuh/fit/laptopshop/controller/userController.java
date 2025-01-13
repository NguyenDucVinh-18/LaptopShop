package vn.edu.iuh.fit.laptopshop.controller;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.iuh.fit.laptopshop.domain.user;

@Controller
public class userController {

    @RequestMapping
    public String index(Model model){
        String message = "Hello Spring Boot + JSP";
        model.addAttribute("message", message);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getCreateUserPage(Model model){
        model.addAttribute("newUser", new user());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") user newUser){
       System.out.println(newUser);
        return "hello";
    }
}
