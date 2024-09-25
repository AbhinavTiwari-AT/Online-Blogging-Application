package com.abhinav.blog.app.Dto;

import java.util.ArrayList;
import java.util.List;

import com.blog.app.Dto.CommentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	
	@NoArgsConstructor
	@Getter
	@Setter
	public class UserDto {
		
		private int id;
		
		@NotEmpty(message ="Please Enter name")
		private String name;
		
		@Email(message ="Email address is not valid")
		private String email;
		
		@NotEmpty
		@Size(min = 3, max = 10, message ="Password must be be minimmum 3 char")
		private String password;
		
		@NotNull(message = "Please Enter something about user")
		private String about;
		
	//	private List<CommentDto> comments = new ArrayList<>();

		

}
