package com.project.UserService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="buyer")
public class BuyerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buyerid",nullable = false,updatable = false)
	private int buyerId;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "phonenumber",nullable = false)
	private String phoneNumber;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "isprivileged")
	private boolean isPrivileged;
	
	@Column(name = "rewardpoints")
	private int rewardPoints;
	
	@Column(name = "isactive")
	private boolean isActive;
	
	
	
	public BuyerEntity() {
		super();
	}
	
	



	@Override
	public String toString() {
		return "BuyerEntity [buyerId=" + buyerId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", password=" + password + ", isPrivileged=" + isPrivileged + ", rewardPoints="
				+ rewardPoints + ", isActive=" + isActive + "]";
	}





	public int getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isPrivileged() {
		return isPrivileged;
	}



	public void setPrivileged(boolean isPrivileged) {
		this.isPrivileged = isPrivileged;
	}



	public int getRewardPoints() {
		return rewardPoints;
	}



	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}
