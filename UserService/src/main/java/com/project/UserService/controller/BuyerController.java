package com.project.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserService.model.Buyer;
import com.project.UserService.service.BuyerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@PropertySource("classpath:configuration.properties")
public class BuyerController {

	@Autowired
	BuyerService buyerservice;

	@Autowired
	Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(BuyerController.class);

	@PostMapping(value = "api/buyer/register")
	public ResponseEntity<String> registerBuyer(@RequestBody Buyer buyer) {
		ResponseEntity<String> responseEntity = null;

		try {
			
			LOGGER.info("Buyer Registration is being done by "+buyer.getName());
			buyer.setActive(true);
			buyer.setPrivileged(false);
			buyer.setRewardPoints(0);
			buyerservice.buyerRegisterion(buyer);
			String successMessage = environment.getProperty("BuyerRegistration.REGISTRATION_SUCCESS");
			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

		} catch (Exception exception) {
			LOGGER.error("Error: " + exception.getMessage(), exception);

			String errorMessage;

			if (exception.getMessage() == null) {
				errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			} else {
				errorMessage = environment.getProperty(exception.getMessage());

				if (errorMessage == null)
					errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			}
		}

		return responseEntity;
	}

	@PostMapping(value = "api/buyer/login")
	public ResponseEntity<String> buyerLogin(@RequestBody Buyer buyer) {

		ResponseEntity<String> responseEntity = null;

		try {
			
			
			buyerservice.buyerLogin(buyer);
			String successMessage = environment.getProperty("BuyerLogin.LOGIN_SUCCESS");
			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

		} catch (Exception exception) {
			LOGGER.error("Error: " + exception.getMessage(), exception);

			String errorMessage;

			if (exception.getMessage() == null) {
				errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			} else {
				errorMessage = environment.getProperty(exception.getMessage());

				if (errorMessage == null)
					errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			}
		}

		return responseEntity;
	}

	

	@PostMapping(value = "api/buyer/deactivate")
	public ResponseEntity<String> deactivateBuyer(@RequestBody Buyer buyer) {

		ResponseEntity<String> responseEntity = null;

		try {
			
			
			buyerservice.deactivateBuyer(buyer);
			String successMessage = environment.getProperty("Buyer.DEACTIVATE_SUCCESS");
			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

		} catch (Exception exception) {
			LOGGER.error("Error: " + exception.getMessage(), exception);

			String errorMessage;

			if (exception.getMessage() == null) {
				errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			} else {
				errorMessage = environment.getProperty(exception.getMessage());

				if (errorMessage == null)
					errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			}
		}

		return responseEntity;
	}
	
	
	@PutMapping(value = "api/buyer/privilege/{email}/{privilege}")
	public ResponseEntity<String> updateBuyerPrivilege(@PathVariable String email ,@PathVariable boolean privilege) {

		ResponseEntity<String> responseEntity = null;

		try {
			
			
			buyerservice.updateBuyerPrivilege(email, privilege);
			String successMessage = environment.getProperty("Buyer.PRIVILEGE_UPDATE_SUCCESS");
			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

		} catch (Exception exception) {
			LOGGER.error("Error: " + exception.getMessage(), exception);

			String errorMessage;

			if (exception.getMessage() == null) {
				errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			} else {
				errorMessage = environment.getProperty(exception.getMessage());

				if (errorMessage == null)
					errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			}
		}

		return responseEntity;

	}
	
	
	
	
	
	@GetMapping(value = "api/rewardPoint/{buyerId}")
	public int getRewardPoints(@PathVariable int buyerId) {
		System.out.println("inside buyer controller :"+ buyerId);
		
		return buyerservice.getRewardPoint(buyerId);

	}

	@PutMapping(value = "api/rewardPoint/update/{buyerId}/{point}")
	public String updateRewardPoint(@PathVariable int buyerId,@PathVariable int point) {
	
		buyerservice.updateRewardPoint(buyerId, point);
		return "Updated reward points";

	}

	@GetMapping(value = "api/buyer/isPrivilege/{buyerId}")
	public boolean isBuyerPrivileged(@PathVariable int buyerId) {
		System.out.println("inside buyer privilege");
		return buyerservice.IsPrivileged(buyerId);
	}

	

	@GetMapping(value = "api/buyer/{email}")
	public ResponseEntity<String> getBuyerDetails(@PathVariable String email) {
		
		ResponseEntity<String> responseEntity = null;

		try {
			
			
			
			String successMessage =buyerservice.getBuyerDetails(email).toString();
			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

		} catch (Exception exception) {
			LOGGER.error("Error: " + exception.getMessage(), exception);

			String errorMessage;

			if (exception.getMessage() == null) {
				errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			} else {
				errorMessage = environment.getProperty(exception.getMessage());

				if (errorMessage == null)
					errorMessage = environment.getProperty("General.EXCEPTION");
				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
				return responseEntity;

			}
		}

		return responseEntity;
		
		

	}

}
