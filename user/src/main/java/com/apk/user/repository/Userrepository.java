package com.apk.user.repository;

import com.apk.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<Users,Integer> {
}
