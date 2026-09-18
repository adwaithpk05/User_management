package com.apk.user.controller;

import com.apk.user.dto.SignupRequest;
import com.apk.user.entity.Users;
import com.apk.user.service.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class Usercontroller {
    @GetMapping("/entry")
    public String entry(){ return "entry.html";}

    @GetMapping("/user/login")
    public String userLogin(){return "user_login";}

    @GetMapping("/user/signup")
    public String viewUserSignup(){return "user_signup";}

    @PostMapping("/user/signup")
    public String userSignup( @Valid @ModelAttribute("signuprequest") SignupRequest signupRequest,
                              BindingResult result,
                              Model model) {
        if (userservice.emailExists(signupRequest.getEmail())) {
            result.rejectValue(
                    "email",
                    "duplicate",
                    "Email already exists"
            );
        }

        if (userservice.phoneExists(signupRequest.getMobNumber())) {
            result.rejectValue(
                    "mobNumber",
                    "duplicate",
                    "Mobile number already exists"
            );
        }

        if (result.hasErrors()) {
            return "user_signup";
        }
        if (signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            userservice.addSignupUser(signupRequest);
            return "home";
        }
        return "user_signup";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @Autowired
    private Userservice userservice;
    @GetMapping("/users")
    public String listUsers(Model model){
        List<Users> users=userservice.getAllUsers();
        model.addAttribute("users",users);
        return "users";
    }
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new Users());
        return "add_user";
    }

    @PostMapping("/users/add")
    public String addUser(
            @Valid @ModelAttribute("user") Users user,
            BindingResult result,
            Model model) {

        if (userservice.emailExists(user.getEmail())) {
            result.rejectValue(
                    "email",
                    "duplicate",
                    "Email already exists"
            );
        }

        if (userservice.phoneExists(user.getMobNumber())){
            result.rejectValue(
                    "mobNumber",
                    "duplicate",
                    "Mobile number already exists"
            );
        }

        if (result.hasErrors()) {
            return "add_user";
        }

        userservice.addUser(user);

        model.addAttribute("message", "User successfully added");

        return "succes";
    }@GetMapping("/users/delete")
    public String showDeleteForm(){
        return "delete_user";
    }

    @PostMapping("/users/delete")
    public String delete(@RequestParam int id,Model model){
        String message=userservice.deleteUser(id);
        model.addAttribute("message",message);
        return "deleted";
    }
    @GetMapping("/users/update")
    public String showUpdateForm(Model model){
        model.addAttribute("user",new Users());
        return "update_user";}

    @PostMapping("/users/update")
    public String update(
        @Valid @ModelAttribute("user") Users user,
        BindingResult result,
        Model model) {
        if (!userservice.idExists(user.getId())) {
            result.rejectValue(
                    "id",
                    "Not exist",
                    "Id not exists"
            );

            if (userservice.emailExists(user.getEmail(), user.getId())) {
                result.rejectValue(
                        "email",
                        "duplicate",
                        "Email already exists"
                );
            }

        }

            if (userservice.phoneExists(user.getMobNumber(),user.getId())){
                result.rejectValue(
                        "mobNumber",
                        "duplicate",
                        "Mobile number already exists"
                );
            }

            if (result.hasErrors()) {
                return "update_user";
            }

            String message=userservice.updateUser(user.getId(),user);
        model.addAttribute("message",message);
        return "updated";
    }
    @GetMapping("/users/sort")
    public String sort(
            @RequestParam String sortBy,
            @RequestParam String direction,
            Model model) {

        List<Users> users =
                userservice.getSortedUsers(sortBy, direction);

        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/users/search")
    public String search(@RequestParam String keyword,Model model){
        List<Users> users=userservice.searchUsers(keyword);
        model.addAttribute("users",users);
        return "users";

    }


}

