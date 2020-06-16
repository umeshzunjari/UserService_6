package com.project.UserService.service;

import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.UserService.dao.SellerRepository;
import com.project.UserService.entity.BuyerEntity;
import com.project.UserService.entity.SellerEntity;
import com.project.UserService.exception.AlreadyEmailIdExistException;
import com.project.UserService.exception.AlreadyPhoneNumberExistException;
import com.project.UserService.exception.InvalidEmailIdException;
import com.project.UserService.exception.InvalidNameException;
import com.project.UserService.exception.InvalidPasswordException;
import com.project.UserService.exception.InvalidPhoneNumberException;
import com.project.UserService.exception.UserException;
import com.project.UserService.exception.WrongEmailException;
import com.project.UserService.exception.WrongPasswordException;
import com.project.UserService.model.Seller;



@Service
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;

	public void sellerRegisterion(Seller seller) throws UserException {
		validateSeller(seller);
		SellerEntity sellerEntity = new SellerEntity();
		BeanUtils.copyProperties(seller, sellerEntity);
		sellerRepository.save(sellerEntity);
	}
	
	private void validateSeller(Seller seller) throws UserException {
		// TODO Auto-generated method stub
		
		if(!isValidName(seller.getName()))
			throw new InvalidNameException("BuyerRegistration.INVALID_NAME");
		if(!isValidEmail(seller.getEmail()))
			throw new InvalidEmailIdException("BuyerRegistration.INVALID_EMAIL");
		if(!isValidPhoneNumber(seller.getphoneNumber()))
			throw new InvalidPhoneNumberException("BuyerRegistration.INVALID_PHONENUMBER");
		if(!isvalidPassword(seller.getPassword()))
			throw new InvalidPasswordException("BuyerRegistration.INVALID_PASSWORD");
		if(!isAlreadyPhoneNumberExist(seller.getphoneNumber()))
			throw new AlreadyPhoneNumberExistException("BuyerRegistration.ALREADY_PH_EXISTS");
		if(!isAlreadyEmailIdExist(seller.getEmail()))
			throw new AlreadyEmailIdExistException("BuyerRegistration.ALREADY_EMAIL_EXISTS");
		
	}

	private boolean isAlreadyEmailIdExist(String email) {
		// TODO Auto-generated method stub
		SellerEntity sellerEntity=sellerRepository.findByEmail(email);
		if (sellerEntity!=null)
			return false;
		return true;
	}

	private boolean isAlreadyPhoneNumberExist(String phoneNumber) {
		// TODO Auto-generated method stub
		SellerEntity sellerEntity=sellerRepository.findByPhoneNumber(phoneNumber);
		if (sellerEntity!=null)
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
	
	

	public void sellerLogin(Seller  seller) throws UserException {
		SellerEntity sellerEntity=sellerRepository.findByEmail(seller.getEmail());
		if(sellerEntity!= null) {
		       if (sellerEntity.isActive()) {
			if(sellerEntity.getPassword().equals(seller.getPassword())) {
				
			}
			else {
				throw new WrongPasswordException("BuyerLogin.INVALID_PASSWORD");
			}
		} else {
					throw new WrongEmailException("Buyer.INVALID_EMAIL1");
				}
			
		}
		else {
			throw new WrongEmailException("BuyerLogin.INVALID_EMAILID");
		}
	}
	
	
	public void deactivateSeller(Seller seller) throws UserException {
		SellerEntity sellerEntity=sellerRepository.findByEmail(seller.getEmail());
		if(sellerEntity!= null) {
			sellerEntity.setActive(false);
			sellerRepository.save(sellerEntity);
			
		}
		else {
			throw new WrongEmailException("BuyerLogin.INVALID_EMAILID");
		}
				
	}
	
	public Seller getSellerDetails(String email) throws UserException {
		SellerEntity sellerEntity=sellerRepository.findByEmail(email);
		Seller seller = new Seller();
		if(sellerEntity!= null) {
		
		BeanUtils.copyProperties(sellerEntity, seller);
		return seller;
		}
		else {
			throw new WrongEmailException("BuyerLogin.INVALID_EMAILID");
			
		}
	}


}
