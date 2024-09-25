package com.abhinav.blog.app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.blog.app.Entities.User;


public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
}