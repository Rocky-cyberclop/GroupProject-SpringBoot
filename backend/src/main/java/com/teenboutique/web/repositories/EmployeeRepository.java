package com.teenboutique.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teenboutique.web.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value = "SELECT * FROM employee WHERE RESIGNING_DATE IS NULL", nativeQuery=true)
	Page<Employee> findAllStillWorking(Pageable page);
	
	Employee findByIdAndPassword(Long id, String pass);
}
