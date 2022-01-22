package com.tipikae.paymybuddy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipikae.paymybuddy.entities.Account;

/**
 * Account repository interface.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

	/**
	 * Find an account by email.
	 * @param email
	 * @return Optional<Account>
	 */
	Optional<Account> findByEmailUser(String email);
	
	/**
	 * Delete an account by email.
	 * @param email
	 */
	void deleteByEmailUser(String email);
}
