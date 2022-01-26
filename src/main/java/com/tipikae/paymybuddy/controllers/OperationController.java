package com.tipikae.paymybuddy.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tipikae.paymybuddy.dto.OperationDTO;
import com.tipikae.paymybuddy.exception.OperationForbiddenException;
import com.tipikae.paymybuddy.exceptions.UserNotFoundException;
import com.tipikae.paymybuddy.services.IOperationService;

/**
 * Operation Controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
public class OperationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);
	
	@Autowired
	private IOperationService operationService;
	
	/**
	 * Save operation for deposit and withdrawal.
	 * @param request
	 * @param model
	 * @param typeOperation
	 * @param amount
	 * @return String
	 */
	@PostMapping("/operation")
	public String saveOperation(
			@ModelAttribute("operation") @Valid OperationDTO operationDTO,
			Errors errors,
			HttpServletRequest request) {
		
		LOGGER.debug("Saving operation");
		if(errors.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			errors.getAllErrors().stream().forEach(e -> sb.append(e.getDefaultMessage() + ", "));
			LOGGER.debug("has errors:" + sb);
			return "redirect:/home?error=invalid field";
		}
		
		Principal principal = request.getUserPrincipal();
		try {
			switch(operationDTO.getTypeOperation()) {
				case "DEP":
					operationService.deposit(principal.getName(), operationDTO.getAmount());
					break;
				case "WIT":
					operationService.withdrawal(principal.getName(), operationDTO.getAmount());
					break;
			}
		} catch (UserNotFoundException e) {
			LOGGER.debug("User not found: " + e.getMessage());
			return "redirect:/home?error=" + e.getMessage();
		} catch (OperationForbiddenException e) {
			LOGGER.debug("Operation forbidden: " + e.getMessage());
			return "redirect:/home?error=" + e.getMessage();
		} catch (Exception e) {
			LOGGER.debug("Unable to process operation: " + e.getMessage());
			return "redirect:/home?error=" + e.getMessage();
		}
		
		return "redirect:/home";
	}
	
	@PostMapping("/transfer")
	public String saveTransfer() {
		LOGGER.debug("Saving transfer");
		return null;
		
	}
}
