package com.tipikae.paymybuddy.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * User entity.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "users_roles", 
			  joinColumns = @JoinColumn(name = "email_user"), 
			  inverseJoinColumns = @JoinColumn(name = "role"))
	private List<Role> roles;
	
	@OneToOne(mappedBy = "user")
	private Account account;
}
