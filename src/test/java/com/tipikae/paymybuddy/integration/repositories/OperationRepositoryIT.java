package com.tipikae.paymybuddy.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.paymybuddy.entities.Account;
import com.tipikae.paymybuddy.repositories.IAccountRepository;
import com.tipikae.paymybuddy.repositories.IOperationRepository;

@SpringBootTest
class OperationRepositoryIT {
	
	@Autowired
	private IOperationRepository operationRepository;
	@Autowired
	private IAccountRepository accountRepository;

	@Test
	void testFindAll() {
		assertEquals(7, operationRepository.findAll().size());
	}

	@Transactional
	@Test
	void testFindByAccount() {
		Account account = accountRepository.getById(1);
		assertEquals(3, operationRepository.findByAccount(account).size());
	}
}
