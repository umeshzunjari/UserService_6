package com.project.UserService.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserService.exception.UserException;
import com.project.UserService.model.Wishlist;
import com.project.UserService.service.WishListService;

@RestController
public class WishListController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WishListController.class);

	@Autowired
	WishListService wishListService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "api/wishlist/add")
	public ResponseEntity<String> addToWishList(@RequestBody Wishlist wishlist) {
		ResponseEntity<String> responseEntity = null;

		try {
			
			
			wishListService.addToWishList(wishlist);
			String successMessage = environment.getProperty("wishlist.ADD_SUCCESS");
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
	
	@DeleteMapping(value = "api/wishlist/remove")
	public ResponseEntity<String> removeFromWishList(@RequestBody Wishlist wishlist) {
		ResponseEntity<String> responseEntity = null;

		try {
			
			
			wishListService.removeFromWishList(wishlist);
			String successMessage = environment.getProperty("wishlist.REMOVE_SUCCESS");
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
	
	@GetMapping(value = "api/wishlist")
	public List<Wishlist> getAllWishList(@RequestParam(name ="buyerId") int buyerId) throws UserException
	{
		
		
			List<Wishlist> successMessage =wishListService.getAllWishList(buyerId);
			
			return successMessage;
			
	}
	
	
	
	@PutMapping(value = "api/wishlist/move/{quantity}")
	public ResponseEntity<String> addToCartFromWishlist(@RequestBody Wishlist wishlist,@PathVariable int quantity) throws UserException {

		ResponseEntity<String> responseEntity = null;

		try {
			
			
			wishListService.wishListMoveToCart(wishlist,quantity);
			String successMessage = environment.getProperty("wishlist.MOVE_TO_CART_SUCCESS");
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
