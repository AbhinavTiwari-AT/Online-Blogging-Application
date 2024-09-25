package com.abhinav.blog.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinav.blog.app.Dto.CommentDto;
import com.abhinav.blog.app.Services.CommentService;


@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
	{
		 CommentDto created  = this.commentService.createComment(comment, postId);
		 return new ResponseEntity<CommentDto>(created,HttpStatus.CREATED);
	
		
	}
	
	
	@PostMapping("/comments/{commentId}")

	public String deleteComment(@PathVariable Integer commentId)
	{
        this.commentService.deleteComment(commentId); 

        return "deleted";
		
	}

}
