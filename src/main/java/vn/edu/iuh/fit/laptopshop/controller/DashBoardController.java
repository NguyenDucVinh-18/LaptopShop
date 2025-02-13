package vn.edu.iuh.fit.laptopshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/admin")
    public String getDashBoard() {
        return "/admin/dashboard/show";
    }
}
