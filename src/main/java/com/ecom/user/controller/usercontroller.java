package com.ecom.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.dto.userrequestdto;
import com.ecom.user.dto.userresponsedto;
import com.ecom.user.service.userservice;

@RestController
@RequestMapping("users")
public class usercontroller {
	
	@Autowired
	private  userservice  userservice;
	
	@PostMapping("/createuser")
	public Object createuser(@RequestBody userrequestdto reqdto) {
		
		Object response =userservice.createuser(reqdto);
		return response ;
		
	}
	
	@GetMapping("/getuser/{id}")
	public Object getuser(@PathVariable int id) {
		
	Object response=userservice.getuser(id);
		return response;
	}
	
	@GetMapping("/getuser/v1/{id}")
	public Object getusers(@PathVariable int id) {
		
	Object response=userservice.getuser(id);
		return response;
	}
	
	

}
