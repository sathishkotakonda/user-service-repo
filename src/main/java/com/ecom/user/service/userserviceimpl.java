package com.ecom.user.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecom.user.GlobalExceptions.InvalidUserCredentialsExceptionhandler;
import com.ecom.user.dto.userrequestdto;
import com.ecom.user.dto.userresponsedto;
import com.ecom.user.entity.user;
import com.ecom.user.repostitory.userrepository;

@Service
public class userserviceimpl implements userservice {

	@Autowired
	private userrepository repo;

//	@Autowired
//    private KafkaTemplate<String, userresponsedto> kafkaTemplate;

	@Override
	public userresponsedto createuser(userrequestdto req) throws Exception {

		// dto to entity
		Optional userindb = repo.findByEmail(req.email());

		if (userindb.isPresent()) {
			throw new Exception("user already present");
		}

		user entity = new user();
		entity.setName(req.name());
		entity.setEmail(req.email());
		entity.setPassword(req.password());

		user saved = repo.save(entity);

		// entity to dto

		userresponsedto dtoresp = new userresponsedto(saved.getId(), saved.getName(), saved.getEmail()

		);

//	kafkaTemplate.send("user-service",dtoresp);

		return dtoresp;

	}

	@Override
	public userresponsedto getuser(long id) {

		Optional<user> optionalUser = repo.findById((int) id);

		if (optionalUser.isPresent()) {

			user u = optionalUser.get();

			userresponsedto dtoresp = new userresponsedto(u.getId(), u.getName(), u.getEmail());

			return dtoresp;
		}

		throw new RuntimeException("User not found with id: " + id);
	}

	@Override
	public List<userresponsedto> getallusers() {
		List<user> dbresponse = repo.findAll();

		return dbresponse.stream().map(user -> new userresponsedto(user.getId(), user.getName(), user.getEmail()

		)).toList();
	}

	@Override
	public String LoginApi(userrequestdto req) {
		
		 user dbUser = repo.findByEmail(req.email())
		            .orElseThrow(() -> new InvalidUserCredentialsExceptionhandler("User not found"));

		    if (!dbUser.getPassword().equals(req.password())) {
		        throw new InvalidUserCredentialsExceptionhandler("Invalid user credentials");
		    }

		    return "Login Successful";
	}
}
