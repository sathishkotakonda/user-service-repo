package com.ecom.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
public class usercontroller {

	@Autowired
	private userservice userservice;

	@PostMapping("/createuser")
	public ResponseEntity<Map<String, Object>> createuser(@RequestBody userrequestdto reqdto) throws Exception {
		userresponsedto userdata = userservice.createuser(reqdto);
		Map<String, Object> response = new HashMap<String, Object>();

		response.put("Result", "Success");
		response.put("Body", userdata);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<Map<String, Object>> getuser(@PathVariable long id) {

		userresponsedto dbresponse = userservice.getuser(id);

		Map<String, Object> mapdata = new HashMap<String, Object>();

		mapdata.put("Result", "Success");
		mapdata.put("Body", dbresponse);

		return ResponseEntity.status(HttpStatus.OK).body(mapdata);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<Map<String, Object>> getallusers() {
		List<userresponsedto> allusers = userservice.getallusers();

		Map<String, Object> allmap = new HashMap<String, Object>();

		allmap.put("Result", "Success");
		allmap.put("Body", allusers);

		return ResponseEntity.status(HttpStatus.OK).body(allmap);

	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> LoginApi(@RequestBody userrequestdto req) {

		String status = userservice.LoginApi(req);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", "Success");
		map.put("Body", status);
		return ResponseEntity.ok(map);

	}

}
