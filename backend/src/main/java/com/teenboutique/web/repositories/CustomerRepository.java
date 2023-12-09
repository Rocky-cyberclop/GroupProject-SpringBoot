package com.teenboutique.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.Customer;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT * FROM customer WHERE email = :email AND password = :password", nativeQuery = true)
	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);
	
	@Query(value = "SELECT * FROM customer WHERE email = :email", nativeQuery = true)
	Optional<Customer> findByEmailOpt(String email); 

}
