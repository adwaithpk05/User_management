package com.apk.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String mob_number;

}