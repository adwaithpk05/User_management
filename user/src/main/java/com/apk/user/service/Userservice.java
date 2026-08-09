package com.apk.user.service;

import com.apk.user.Users;
import com.apk.user.repository.Userrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Userservice {
    private final Userrepository userrepository;

    public List<Users> getAllUsers(){
        return userrepository.findAll();
    }
    public void addUser(Users user){
        userrepository.save(user);
    }
    public void deleteUser(Integer id){
            userrepository.deleteById(id);
}


}

