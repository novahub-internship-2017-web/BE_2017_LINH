package com.linhtran.springboot.booksmanagement.repository;

import com.linhtran.springboot.booksmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
}
