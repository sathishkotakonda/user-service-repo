package com.ecom.user.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.user.dto.userrequestdto;

public interface userservice {
		
	public Object createuser(userrequestdto dto);
	
	public Object getuser(int id);  
}
