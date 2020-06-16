package com.project.UserService.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity {
	
	@EmbeddedId
	private CartId cartId;
	
	
	private int quantity;
	
	public CartEntity(CartId cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
	}
	

	public CartEntity() {
		super();
	}
	@Override
	public String toString() {
		return "CartEntity [cartId=" + cartId + ", quantity=" + quantity + "]";
	}
	public CartId getCartId() {
		return cartId;
	}
	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
