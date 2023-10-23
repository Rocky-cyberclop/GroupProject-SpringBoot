package com.teenboutique.web.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teenboutique.web.dto.StatisticDto;
import com.teenboutique.web.entities.CustomerOrder;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long>{
	@Query(value = "SELECT * FROM customer_order WHERE status is null", nativeQuery=true)
	Page<CustomerOrder> findAllOrderUnexamined(Pageable page);
	
	@Query(value = "SELECT date, sum(total) as sellPerDay FROM customer_order WHERE date >= :start and date <= :end group by date order by date", nativeQuery=true)
	List<StatisticDto> findAllOrderWithDate(@Param("start") String start, @Param("end") String end);
}
