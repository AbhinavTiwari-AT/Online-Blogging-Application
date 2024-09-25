package com.abhinav.blog.app.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.blog.app.Dto.UserDto;
import com.abhinav.blog.app.Entities.User;
import com.abhinav.blog.app.Exceptions.ResourceNotFoundException;
import com.abhinav.blog.app.Repositories.UserRepo;
import com.abhinav.blog.app.Services.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtotoUser(userDto);
		
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 =this.userToDto(updatedUser);
        
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users =  this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

	User user =	this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
		
	}
	
	public  User dtotoUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		
	//	User user = new User();
	//	user.setId(userDto.getId());
	//	user.setName(userDto.getName());
	//	user.setEmail(userDto.getEmail());
	//	user.setPassword(userDto.getPassword());
	//	user.setAbout(userDto.getAbout());
		return user;	
	}
	
	public UserDto userToDto(User user)
	{
	      UserDto userDto =	this.modelMapper.map(user, UserDto.class);
		
		
	//	UserDto userDto=new UserDto();
		
	//	userDto.setId(user.getId());
	//	userDto.setName(user.getName());
	//	userDto.setEmail(user.getEmail());
	//	userDto.setPassword(user.getPassword());
	//	userDto.setAbout(user.getAbout());
		
		return userDto;
	}
}
