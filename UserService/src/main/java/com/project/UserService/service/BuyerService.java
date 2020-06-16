package com.project.UserService.service;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.UserService.controller.BuyerController;
import com.project.UserService.dao.BuyerRepository;
import com.project.UserService.entity.BuyerEntity;
import com.project.UserService.exception.AlreadyEmailIdExistException;
import com.project.UserService.exception.AlreadyNotPrivilegeException;
import com.project.UserService.exception.AlreadyPhoneNumberExistException;
import com.project.UserService.exception.AlreadyPrivilegeException;
import com.project.UserService.exception.InSufficientRewardPoint;
import com.project.UserService.exception.InvalidEmailIdException;
import com.project.UserService.exception.InvalidNameException;
import com.project.UserService.exception.InvalidPasswordException;
import com.project.UserService.exception.InvalidPhoneNumberException;
import com.project.UserService.exception.UserException;
import com.project.UserService.exception.WrongEmailException;
import com.project.UserService.exception.WrongPasswordException;
import com.project.UserService.model.Buyer;

@Service
@PropertySource(value = { "classpath:configuration.properties" })
public class BuyerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BuyerService.class);
	@Autowired
	private Environment env;
	@Autowired
	BuyerRepository buyerRepository;

	public void buyerRegisterion(Buyer buyer) throws Exception {
		LOGGER.info("Buyer details are going to validate");
		validateBuyer(buyer);
		LOGGER.info("Buyer details are validated successfully");
		BuyerEntity buyerEntity = new BuyerEntity();
		BeanUtils.copyProperties(buyer, buyerEntity);
		buyerRepository.save(buyerEntity);
		LOGGER.info("Buyer details are saved in DB successfully");
		
	}

	private void validateBuyer(Buyer buyer) throws Exception {
		LOGGER.info("Buyer details are being validated");
		// TODO Auto-generated method stub
		if(!isValidName(buyer.getName()))
			throw new InvalidNameException("BuyerRegistration.INVALID_NAME");
		if(!isValidEmail(buyer.getEmail()))
			throw new InvalidEmailIdException("BuyerRegistration.INVALID_EMAIL");
		if(!isValidPhoneNumber(buyer.getPhoneNumber()))
			throw new InvalidPhoneNumberException("BuyerRegistration.INVALID_PHONENUMBER");
		if(!isvalidPassword(buyer.getPassword()))
			throw new InvalidPasswordException("BuyerRegistration.INVALID_PASSWORD");
		if(!isAlreadyPhoneNumberExist(buyer.getPhoneNumber()))
			throw new AlreadyPhoneNumberExistException("BuyerRegistration.ALREADY_PH_EXISTS");
		if(!isAlreadyEmailIdExist(buyer.getEmail()))
			throw new AlreadyEmailIdExistException("BuyerRegistration.ALREADY_EMAIL_EXISTS");
		
		
	}

	private boolean isAlreadyEmailIdExist(String email) {
		// TODO Auto-generated method stub
		BuyerEntity buyerEntity=buyerRepository.findByEmail(email);
		if (buyerEntity!=null)
			return false;
		return true;
	}

	private boolean isAlreadyPhoneNumberExist(String phoneNumber) {
		// TODO Auto-generated method stub
		BuyerEntity buyerEntity=buyerRepository.findByPhoneNumber(phoneNumber);
		if (buyerEntity!=null)
			return false;
		return true;
	}

	private boolean isvalidPassword(String password) {
		// TODO Auto-generated method stub
		return Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,20}$",password);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return Pattern.matches("^\\d{10}$", phoneNumber);
	}

	private boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$",email);
	}

	private boolean isValidName(String name) {
		// TODO Auto-generated method stub
		return Pattern.matches("^[a-zA-Z]+[-a-zA-Z\\s]+([-a-zA-Z]+)$", name);
	}
	
	

	public void buyerLogin(Buyer buyer) throws UserException {

		BuyerEntity buyerEntity = buyerRepository.findByEmail(buyer.getEmail());
		if(buyerEntity!=null) {
				if (buyerEntity.isActive()) {
					if (buyerEntity.getPassword().equals(buyer.getPassword())) {
						
					} else {
						throw new WrongPasswordException("BuyerLogin.INVALID_PASSWORD");
					}
		
				} else {
					throw new WrongEmailException("Buyer.INVALID_EMAIL1");
				}
		}
		else {
			throw new WrongEmailException("Buyer.INVALID_EMAILID");
			
		}

	}

	public void deactivateBuyer(Buyer buyer) throws UserException {

		BuyerEntity buyerEntity = buyerRepository.findByEmail(buyer.getEmail());
		if (buyerEntity != null) {
			buyerEntity.setActive(false);
			buyerRepository.save(buyerEntity);
			

		} else {
			throw new InvalidEmailIdException("Buyer.INVALID_EMAIL");
		}

	}
	
	public int getRewardPoint(int buyerId) {
		System.out.println("inside buyer service"+buyerId);
		BuyerEntity buyerEntity=buyerRepository.findByBuyerId(buyerId);
		System.out.println(buyerEntity);
		return buyerEntity.getRewardPoints();

		
	}
	
	public void updateRewardPoint(int buyerId, int point) {
		BuyerEntity buyerEntity =buyerRepository.findByBuyerId(buyerId);
		Integer pre=buyerEntity.getRewardPoints();
		buyerEntity.setRewardPoints(point+pre);
		buyerRepository.save(buyerEntity);
		
	}
	
	public boolean IsPrivileged(int buyerId) {
		
		BuyerEntity buyerEntity= buyerRepository.findByBuyerId(buyerId);
		
		if((buyerEntity.isPrivileged())==false) {
			
			return false;
		}
		else {
			
			return true;
		}
		
	}
	
	public void updateBuyerPrivilege(String email,boolean privilege) throws UserException {
		BuyerEntity buyerEntity= buyerRepository.findByEmail(email);
		System.out.println("=====service========");
		if(buyerEntity!=null){
			if(buyerEntity.isPrivileged()) {
				if(!privilege) {
					buyerEntity.setPrivileged(privilege);
				    buyerRepository.save(buyerEntity);
					
				}
				else {
				throw new AlreadyPrivilegeException("Buyer.ALREADY_PRIVILEGE");
				}
			}
			else if(!(buyerEntity.isPrivileged())) {
				if(privilege) {
					if(buyerEntity.getRewardPoints()<10000) {
						throw new InSufficientRewardPoint("Buyer.INSUFFICIENT_REWARD_POINTS");
					}
					else {
						buyerEntity.setPrivileged(privilege);
						buyerEntity.setRewardPoints(buyerEntity.getRewardPoints()-10000);
						buyerRepository.save(buyerEntity);
					}
					
				}
				else {
					
					throw new AlreadyNotPrivilegeException("Buyer.ALREADY_NOT_PRIVILEGE");
					
				}
			}
		buyerEntity.setPrivileged(privilege);
	    buyerRepository.save(buyerEntity);
		}
		else {
			throw new InvalidEmailIdException("Buyer.INVALID_EMAIL");
		}
		
	}
	
	public Buyer getBuyerDetails(String email) throws UserException {
		BuyerEntity buyerEntity= buyerRepository.findByEmail(email);
		if(buyerEntity!=null) {
		Buyer buyer=new Buyer();
		BeanUtils.copyProperties(buyerEntity, buyer);
		return buyer;
		}
		else {
			throw new InvalidEmailIdException("Buyer.INVALID_EMAIL");
		}
		
	}
	
	
	
	
	
	
	
}
