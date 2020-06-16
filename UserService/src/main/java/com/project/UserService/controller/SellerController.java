package com.project.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserService.model.Seller;
import com.project.UserService.service.SellerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SellerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SellerController.class);
	@Autowired
	private SellerService sellerService;
	@Autowired
	Environment environment;

	@PostMapping(value = "/api/seller/register")
	public ResponseEntity<String> registerSeller(@RequestBody Seller seller) {

		ResponseEntity<String> responseEntity = null;

		try {

			LOGGER.info("Seller Registration is being done by " + seller.getName());
			seller.setActive(true);
			sellerService.sellerRegisterion(seller);
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

	@PostMapping(value = "/api/seller/login")
	public ResponseEntity<String> sellerLogin(@RequestBody Seller seller) {
		ResponseEntity<String> responseEntity = null;

		try {

			LOGGER.info("Seller login is being done by " + seller.getName());

			sellerService.sellerLogin(seller);
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

	@PostMapping(value = "api/seller/deactivate")
	public ResponseEntity<String> deactivateSeller(@RequestBody Seller seller) {
		ResponseEntity<String> responseEntity = null;

		try {

			LOGGER.info("Seller deactivation is being done by " + seller.getName());

			sellerService.deactivateSeller(seller);
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

	@GetMapping(value = "api/seller/{email}")
	public ResponseEntity<String> getSellerDetails(@PathVariable String email) {

		ResponseEntity<String> responseEntity = null;

		try {
			
			
			
			String successMessage =sellerService.getSellerDetails(email).toString();
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
