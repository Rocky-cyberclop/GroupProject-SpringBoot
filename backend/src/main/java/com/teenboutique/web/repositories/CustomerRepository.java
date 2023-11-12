package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teenboutique.web.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query(value = "SELECT * FROM customer WHERE email = :email AND password = :password", nativeQuery = true)
	
	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

}
