package com.abhinav.blog.app.Services.Impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.blog.app.Dto.CommentDto;
import com.abhinav.blog.app.Entities.Comment;
import com.abhinav.blog.app.Entities.Post;
import com.abhinav.blog.app.Exceptions.ResourceNotFoundException;
import com.abhinav.blog.app.Repositories.CommentRepo;
import com.abhinav.blog.app.Repositories.PostRepo;
import com.abhinav.blog.app.Repositories.UserRepo;
import com.abhinav.blog.app.Services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
          Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
    //     User user = this.userRepo.getById(userId); //.orElseThrow(()-> new ResourceNotFoundException("USer", "user Id", userId));
          Comment comment = this.modelMapper.map(commentDto, Comment.class);
          comment.setPost(post);
      //   comment.setUser(user.getId());
          Comment savedComment =  this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
             
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id",commentId));
		this.commentRepo.delete(comment);
		
		
	}
}
