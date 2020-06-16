package com.project.UserService.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.UserService.entity.WishListId;
import com.project.UserService.entity.WishlistEntity;

public interface WishListRepository extends CrudRepository<WishlistEntity, WishListId>{
	public List<WishlistEntity> findByIdBuyerId(int buyerId);
	
	public WishlistEntity findByIdBuyerIdAndIdProdId(int buyerId,int ProdId);
}
