package com.food.foodService.controllers;


import java.io.Console;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.food.foodService.models.Food;
import com.food.foodService.models.User;
import com.food.foodService.respositories.UserRepository;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public String userRegister(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		try {
			String password = user.getPassword()+user.getFname();
			String hashedPassword = passwordEncoder.encode(password);
			user.setToken(hashedPassword);
			if (userRepo.findByUserName(user.getUsername()).isEmpty()) {
				userRepo.save(user);
				return "{\"status\": 200, \"message\": \"successful\", \"token\": \""+hashedPassword+"."+user.getId()+"\" }";
			}else {
				return "{\"status\": 200, \"error\": \"exists\"}";
			}
			
		} catch (Exception e) {
			return "{\"status\": 500, \"message\": \""+e.getMessage() +"\"}";			
					
		}
		
		//return message.toString();
			
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/authenticate")
	public String authenticate(@RequestBody User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		User authUser=userRepo.findForAuth(user.getUsername(),user.getPassword());
		if (authUser!=null){
			
			String password = authUser.getPassword()+authUser.getFname();
			String hashedPassword = passwordEncoder.encode(password);
			authUser.setToken(hashedPassword);
			userRepo.save(authUser);
			
			String message= "{\"status\":200, \"token\": \"" + hashedPassword+"."+authUser.getId() +"\"}";
			
			return message;
		}
			String message= "{\"status\":500}";
			return message;
		
		
	}
	
	
}
