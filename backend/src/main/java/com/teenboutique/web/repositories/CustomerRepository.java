package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
