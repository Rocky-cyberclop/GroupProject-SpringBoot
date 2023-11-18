package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
