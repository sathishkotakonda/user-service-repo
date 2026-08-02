package com.ecom.user.service;


import java.util.List;

import com.ecom.user.dto.userrequestdto;
import com.ecom.user.dto.userresponsedto;
import com.ecom.user.entity.user;

public interface userservice {
		
	public userresponsedto createuser(userrequestdto dto) throws Exception;
	
	public userresponsedto getuser(long id); 
	
	public List<userresponsedto> getallusers();
	
	public String LoginApi(userrequestdto req);
}
