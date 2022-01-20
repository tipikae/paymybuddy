package com.tipikae.paymybuddy.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.paymybuddy.repositories.UserRepository;

@SpringBootTest
class UserIT {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void testCount() {
		assertEquals(3, userRepository.count());
	}

}
