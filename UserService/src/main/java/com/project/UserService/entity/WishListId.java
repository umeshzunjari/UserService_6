package com.project.UserService.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class WishListId implements Serializable{
	
	@Column(name = "buyerid",nullable = false,updatable = false)
	private int buyerId;
	
	@Column(name = "prodid",nullable = false,updatable=false)
	private int prodId;
	
	
	
	public WishListId() {
		super();
	}

	public WishListId(int buyerId, int prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	
	
	

}
