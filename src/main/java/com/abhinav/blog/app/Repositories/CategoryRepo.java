package com.abhinav.blog.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.blog.app.Entities.Category;


	
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

