package com.teenboutique.web.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.teenboutique.web.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {
	@Query(value = "SELECT * FROM product WHERE stop_sale = false", nativeQuery=true)
	Page<Product> findAllProductStillSell(Pageable page);
  
	 @Query(value = "SELECT product.* FROM product inner join (select product_id from customer_order_item group by product_id order by sum(quantity) desc limit 9) my on product.id = my.product_id;", nativeQuery=true)
	List<Product> findTrendyProduct();

	 @Query(value = "SELECT * FROM product WHERE stop_sale = false AND category_id = ?1", nativeQuery=true)
	Page<Product> findAllProductCate(Long id, Pageable page);

}

