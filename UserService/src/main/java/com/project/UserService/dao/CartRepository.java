package com.project.UserService.dao;








import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.UserService.entity.CartEntity;
import com.project.UserService.entity.CartId;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartId>{
	

	
}