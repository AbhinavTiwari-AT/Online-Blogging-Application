package com.abhinav.blog.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.blog.app.Entities.Comment;


	
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	}


