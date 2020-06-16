package com.project.UserService.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
public class WishlistEntity {
	
	@EmbeddedId
	private WishListId id;

	
	
    
	@Override
	public String toString() {
		return "WishlistEntity [id=" + id + "]";
	}

	public WishlistEntity() {
		super();
	}

	public WishlistEntity(WishListId id) {
		super();
		this.id = id;
	}

	public WishListId getId() {
		return id;
	}

	public void setId(WishListId id) {
		this.id = id;
	}


    
	
	
	
	

}
