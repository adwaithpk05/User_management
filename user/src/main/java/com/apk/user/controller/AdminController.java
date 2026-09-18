package com.apk.user.controller;



import com.apk.user.entity.Admin;
import com.apk.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/admin/login")
    public String showAdminLogin(){
        return "show_admin_login";

    }
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<Admin> admin = adminService.findAdminByEmail(email);
        if (admin.isPresent()) {
            if (password.equals(admin.get().getPassword())) {
                model.addAttribute("bodytext","succesfully login");
                return "home";

            }
        }
            model.addAttribute("error","Email or password is wrong");
            return "show_admin_login";



    }


}
