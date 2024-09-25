package com.abhinav.blog.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.blog.app.Entities.Category;
import com.abhinav.blog.app.Entities.Post;
import com.abhinav.blog.app.Entities.User;


public interface PostRepo extends JpaRepository<Post,Integer>{
	
	List<Post>findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post>findByTitleContaining(String title);
	
}
