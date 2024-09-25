package com.abhinav.blog.app.Entities;


import lombok.Data;
	

@Data
public class JwtAuthRequest {
	
	private String Username;
	
	private  String Password;

}



