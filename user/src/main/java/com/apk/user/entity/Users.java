package com.apk.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        private String email;

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid phone number")
        private String mobNumber;

        @NotBlank(message = "password is required")
        @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}")
        private String password;

}