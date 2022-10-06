package com.crudApplication.crudAppbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudApplication.crudAppbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
