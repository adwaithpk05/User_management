package com.apk.user.service;

import com.apk.user.dto.SignupRequest;
import com.apk.user.entity.Users;
import com.apk.user.repository.Userrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Userservice {
    private final Userrepository userrepository;
    private final PasswordEncoder encoder;

    public List<Users> getAllUsers(){
        return userrepository.findAll();
    }

    public void addSignupUser(SignupRequest request){
        if(userrepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        Users user = new Users();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobNumber(request.getMobNumber());
        user.setPassword(encoder.encode(request.getPassword()));
        userrepository.save(user);


    }

    public void addUser(Users user){
        userrepository.save(user);
    }

    public boolean emailExists(String email, int id) {
        return userrepository.existsByEmailAndIdNot(email, id);
    }

    public boolean phoneExists(String mobNumber, int id) {
        return userrepository.existsByMobNumberAndIdNot(mobNumber, id);
    }
    public boolean emailExists(String email) {
        return userrepository.existsByEmail(email);
    }

    public boolean phoneExists(String mobNumber) {
        return userrepository.existsByMobNumber(mobNumber);
    }
    public boolean idExists(int id) {
        return userrepository.existsById(id);
    }

    public String deleteUser(Integer id){
        if(!
        userrepository.existsById(id)){
            return "ID does not exist.Enter a valid Id";
        }
            userrepository.deleteById(id);
        return "User succeccfully deleted";
}
    public List<Users> getSortedUsers(String field, String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(field).descending();
        } else {
            sort = Sort.by(field).ascending();
        }

        return userrepository.findAll(sort);
    }
public String updateUser(int id,Users newUser)
{
    Optional<Users> optionalUser= userrepository.findById(id);
    if(optionalUser.isPresent()){
       Users user = optionalUser.get();
       user.setName(newUser.getName());
       user.setEmail(newUser.getEmail());
       user.setMobNumber(newUser.getMobNumber());
       userrepository.save(user);
       return "Succesfully updated";
    }
    return "Id is not valid";
}
    public List<Users> searchUsers(String keyword) {
        return userrepository.searchUsers(keyword);
    }


}

