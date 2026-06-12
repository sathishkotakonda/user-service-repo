package com.ecom.user.repostitory;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.user.dto.userresponsedto;
import com.ecom.user.entity.user;

public interface userrepository extends JpaRepository<user,Integer > {

	//Object save(userresponsedto res);

	
	

}
