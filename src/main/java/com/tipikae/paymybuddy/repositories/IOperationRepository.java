package com.tipikae.paymybuddy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tipikae.paymybuddy.entities.Account;
import com.tipikae.paymybuddy.entities.Operation;
import com.tipikae.paymybuddy.entities.Transfer;

/**
 * Operation repository interface.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface IOperationRepository extends JpaRepository<Operation, Integer> {

	/**
	 * Find operations by account.
	 * @param account
	 * @return List
	 */
	List<Operation> findByAccount(Account account);
	
	@Query(
		value = "SELECT * FROM operation WHERE type = 'TRA' AND email_src_connection = :emailSrc",
		nativeQuery = true)
	List<Transfer> findTransfersByEmailSrc(@Param("emailSrc") String emailSrc);
}
