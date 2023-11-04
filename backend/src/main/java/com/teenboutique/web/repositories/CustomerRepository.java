package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teenboutique.web.entities.Customer;

/* Based on my understanding this is where I need to do thing with the database which include SQL
query which explained why this is called repository */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query(value = "SELECT * FROM customer WHERE email = :email AND password = :password", nativeQuery = true)
	
	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

}
