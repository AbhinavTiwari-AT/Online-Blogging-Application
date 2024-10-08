package com.abhinav.blog.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.abhinav.blog.app.Security.CustomUserDetailService;
import com.abhinav.blog.app.Security.JwtAuthenticationEntryPoint;
import com.abhinav.blog.app.Security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
	//old technique is extends WebSecurityConfigurerAdapter
	//new technique is:-> Spring Security without the WebSecurityConfigurerAdapter
	//use filterChain Method
	
	@Autowired
	CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// spring security provide form based authentication by default. By writing this
		// we have implemented basic authentication 
		// and remove form based authentication.
		// default form based credential form turn into js alert based form.
		// from here to
		http
		.csrf()
		.disable()
		
		.authorizeHttpRequests()
        .requestMatchers("/api/auth/login").permitAll()
        //.requestMatchers("/v3/api-docs", "/swagger-ui.html", "/swagger-ui/**").permitAll()
        
        
        //now, any one can access GET APIs. No need to login. Now, GET APIs can access publicly
        .requestMatchers(HttpMethod.GET).permitAll()  
		.anyRequest()
		.authenticated()
		.and()
		
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// to here
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// http.authenticationProvider(daoAuthenticationProvider());

		DefaultSecurityFilterChain build = http.build();
		return build;
	}

//		@Bean
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
//		}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	//see this...
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager(); 

	}

	}
    



