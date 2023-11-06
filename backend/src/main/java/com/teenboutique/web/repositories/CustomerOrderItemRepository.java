package com.teenboutique.web.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teenboutique.web.entities.CustomerOrderItem;

public interface CustomerOrderItemRepository extends JpaRepository<CustomerOrderItem, Long> {
    
    @Query("SELECT coi FROM CustomerOrderItem coi WHERE coi.id.customer_order_id = :customerOrderId")
    List<CustomerOrderItem> findByCustomerOrderId(@Param("customerOrderId") Long customerOrderId);
}
