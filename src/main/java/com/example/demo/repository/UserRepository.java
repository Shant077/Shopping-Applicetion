package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    //    Users findUsersByPasswordAndEmail(String password,String email);
    Users findUsersByEmail(String email);

    Users findUsersByPassword(String password);

    Users findUsersByIsAdmin(Boolean type);

    Users findUsersById(Long id);

}
