package com.apk.user.repository;

import com.apk.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Userrepository extends JpaRepository<Users,Integer> {
    @Query("""
    SELECT u FROM Users u
    WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR u.mobNumber LIKE CONCAT('%', :keyword, '%')
""")
    List<Users> searchUsers(@Param("keyword") String keyword);
    boolean existsById(int id);
    boolean existsByEmailAndIdNot(String email,int id);
    boolean existsByMobNumberAndIdNot(String mobNumber, int id);
    boolean existsByEmail(String email);
    boolean existsByMobNumber(String mobNumber);


}

