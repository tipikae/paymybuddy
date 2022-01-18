package com.tipikae.paymybuddy.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tipikae.paymybuddy.repositories.InfoBankAccountRepository;

@SpringBootTest
class InfoBankAccountTest {
	
	@Autowired
	private InfoBankAccountRepository infoBankAccountRepository;

	@Test
	void testFindAll() {
		assertEquals(5, infoBankAccountRepository.findAll().size());
	}
	
	@Transactional
	@Test
	void testFindByIban() {
		assertEquals("hector@hector.com", infoBankAccountRepository.getById("ES147258369").getEmailUser());
	}
}
