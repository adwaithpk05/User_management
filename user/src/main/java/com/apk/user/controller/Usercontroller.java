package com.apk.user.controller;

import com.apk.user.Users;
import com.apk.user.service.Userservice;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Usercontroller {
    @Autowired
    private Userservice userservice;
    @GetMapping("/users")
    public String listUsers(Model model){
        List<Users> users=userservice.getAllUsers();
        model.addAttribute("users",users);
        return "users";
    }
    @GetMapping("/users/add")
    public String showAddUserForm() {
        return "add_user";
    }
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute Users user, Model model) {

        userservice.addUser(user);

        model.addAttribute("message", "User successfully added!");

        return "succes";
    }

}

