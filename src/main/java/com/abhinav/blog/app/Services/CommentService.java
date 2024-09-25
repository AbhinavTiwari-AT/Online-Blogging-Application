package com.abhinav.blog.app.Services;

import com.abhinav.blog.app.Dto.CommentDto;

public interface CommentService {
		
		
		 CommentDto createComment(CommentDto commentDto , Integer postId
				 );
		 
		   void deleteComment(Integer commentId);
		
		

}
