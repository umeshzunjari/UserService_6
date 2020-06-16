package com.project.UserService.model;

public class Wishlist {
	private int buyerId;
	private int prodId;
	
	
	@Override
	public String toString() {
		return "Wishlist [buyerId=" + buyerId + ", prodId=" + prodId + "]";
	}
	
	
	public Wishlist(int buyerId, int prodId) {
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
