package com.linhtran.springboot.booksmanagement.repository;

import com.linhtran.springboot.booksmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
