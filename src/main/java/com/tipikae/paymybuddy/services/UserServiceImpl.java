package com.tipikae.paymybuddy.services;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tipikae.paymybuddy.converters.IConverterUserToHomeDTO;
import com.tipikae.paymybuddy.converters.IConverterUserToProfileDTO;
import com.tipikae.paymybuddy.dto.HomeDTO;
import com.tipikae.paymybuddy.dto.ProfileDTO;
import com.tipikae.paymybuddy.dto.NewUserDTO;
import com.tipikae.paymybuddy.entities.Account;
import com.tipikae.paymybuddy.entities.Role;
import com.tipikae.paymybuddy.entities.User;
import com.tipikae.paymybuddy.exceptions.ConverterException;
import com.tipikae.paymybuddy.exceptions.UserAlreadyExistException;
import com.tipikae.paymybuddy.exceptions.UserNotFoundException;
import com.tipikae.paymybuddy.repositories.IUserRepository;

/**
 * Implementation of IUserService.
 * @author tipikae
 * @version 1.0
 *
 */
@Transactional
@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * UserRepository.
	 */
	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * PasswordEncoder bean.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IConverterUserToHomeDTO converterUserToHomeDTO;
	
	@Autowired
	private IConverterUserToProfileDTO converterUserToProfileDTO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User registerNewUser(NewUserDTO userDTO) throws UserAlreadyExistException {
		LOGGER.debug("Registering new user");
		if(emailExists(userDTO.getEmail())) {
			LOGGER.debug("An user with email address: " + userDTO.getEmail()
				+ " already exists.");
			throw new UserAlreadyExistException("An user with email address: " + userDTO.getEmail()
				+ " already exists.");
		}

		User user = new User();
		Account account = new Account();
		Role role = new Role();
		
		role.setRole("USER");
		
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRoles(Arrays.asList(role));
		user.setAccount(account);
		
		account.setBalance(0);
		account.setDateCreated(new Date());
		account.setUser(user);
		
		userRepository.save(user);
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HomeDTO getHomeDetails(String email) throws UserNotFoundException, ConverterException {
		LOGGER.debug("GetHomeDetails: email=" + email);
		User user = isUserExists(email);
		
		return converterUserToHomeDTO.convertToDTO(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProfileDTO getProfileDetails(String email) throws UserNotFoundException, ConverterException {
		LOGGER.debug("GetProfile: email=" + email);
		User user = isUserExists(email);
		
		return converterUserToProfileDTO.convertToDTO(user);
	}

    private boolean emailExists(String email) {
    	Optional<User> optional = userRepository.findByEmail(email);
    	if(optional.isPresent()) {
    		return true;
    	}
		return false;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public User isUserExists(String email) throws UserNotFoundException {
		Optional<User> optional = userRepository.findByEmail(email);
		if(!optional.isPresent()) {
			LOGGER.debug("isUserExists: user with email=" + email + " not found.");
			throw new UserNotFoundException("User with email=" + email + " not found.");
		}
		return optional.get();
	}
}
