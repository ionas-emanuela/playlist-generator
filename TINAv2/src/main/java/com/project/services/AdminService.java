package com.project.services;

import com.project.entities.Admin;
import com.project.services.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin findById(int id) {

        return adminRepository.findById(id).orElse(null);
    }

    public Admin findByUsername(String username) {

        return adminRepository.findByUsername(username);
    }

    public Admin loginAdmin(String username, String password) {

        Admin admin = findByUsername(username);

        if(null != admin){
            if(admin.getPassword().equals(password)){
                return admin;
            }
        }

        return null;
    }

}
