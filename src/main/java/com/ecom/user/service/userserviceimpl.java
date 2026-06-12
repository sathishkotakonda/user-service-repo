package com.ecom.user.service;




import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;   

import com.ecom.user.dto.userrequestdto;
import com.ecom.user.dto.userresponsedto;
import com.ecom.user.entity.user;
import com.ecom.user.repostitory.userrepository;

@Service
public class userserviceimpl implements userservice {
	
	@Autowired
	private userrepository repo;

	@Override
	public Object createuser(userrequestdto req) {
		
		// dto to entity		
		user entity = new user();
		entity.setName(req.getName());
		entity.setEmail(req.getEmail());
		
		
	      user saved=    repo.save(entity);
	
	// entity to dto
	
	userresponsedto dtoresp = new userresponsedto();
	
	dtoresp.setName(saved.getName());
	dtoresp.setEmail(saved.getEmail());
	dtoresp.setId(saved.getId());
	
	
	
	return dtoresp;
	
	
	
	
	}

	@Override
	public userresponsedto getuser(int id) {

	    Optional<user> optionalUser = repo.findById((int) id);

	    if (optionalUser.isPresent()) {

	        user u = optionalUser.get();

	        userresponsedto dtoresp = new userresponsedto();
	        dtoresp.setName(u.getName());
	        dtoresp.setEmail(u.getEmail());
	        dtoresp.setId(u.getId());
	        

	        return dtoresp;
	    }

	    throw new RuntimeException("User not found with id: " + id);
	}



}
