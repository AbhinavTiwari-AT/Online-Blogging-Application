package com.abhinav.blog.app.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhinav.blog.app.Entities.User;
import com.abhinav.blog.app.Exceptions.ResourceNotFoundException;
import com.abhinav.blog.app.Repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
				User user = this.userRepo.findByEmail(username)
						.orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));

				return user;
	}

}
