package com.teenboutique.web.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teenboutique.web.dto.StatisticDto;
import com.teenboutique.web.entities.CustomerOrder;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long>{
	@Query(value = "SELECT * FROM customer_order WHERE status is null", nativeQuery=true)
	Page<CustomerOrder> findAllOrderUnexamined(Pageable page);
	
	@Query(value = "SELECT date, sum(total) as sellPerDay FROM customer_order WHERE date >= :start and date <= :end group by date order by date", nativeQuery=true)
	List<StatisticDto> findAllOrderWithDate(@Param("start") String start, @Param("end") String end);
	
	@Query(value = "select * from customer_order order by id DESC limit 1", nativeQuery=true)
	CustomerOrder customerOrderLast();
	
	@Transactional
	@Modifying
	@Query(value = "insert into customer_order_item( customer_order_id, point, price, quantity, rate_content, rate_date, product_id, size_id) value(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	void customerOrderItemIntsert(Long customer_order_id, Integer point, Long price, Integer quantity, String rate_content, String rate_date, Long product_id, Long size_id);
}
