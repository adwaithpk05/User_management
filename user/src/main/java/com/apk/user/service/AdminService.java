package com.apk.user.service;

import com.apk.user.entity.Admin;
import com.apk.user.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);

        }
}
