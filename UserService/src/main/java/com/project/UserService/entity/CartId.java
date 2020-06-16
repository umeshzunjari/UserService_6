package com.project.UserService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CartId implements Serializable {
	
	@Column(name = "buyerid",nullable = false)
	private int buyerId;
	
	@Column(name = "prodid",nullable = false)
	private int prodId;
	
	
	public CartId(int buyerId, int prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
		
	}

	public CartId() {
		super();
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
