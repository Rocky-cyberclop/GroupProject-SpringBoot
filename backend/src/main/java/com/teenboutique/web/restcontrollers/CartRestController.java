package com.teenboutique.web.restcontrollers;

import com.teenboutique.web.dto.CartItemDto;
import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.repositories.CartRepository;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.services.CartService;
import com.teenboutique.web.services.OrderDetailService;
import com.teenboutique.web.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	@GetMapping("/items")
    public ResponseEntity<List<CartItemDto>> getCartItemsByCustomerId() {
        List<CartItemDto> cartItemDtos = cartService.getCartItemsByCustomerId();
        return ResponseEntity.ok(cartItemDtos);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> deleteCartItemByCustomerAndProduct(@PathVariable Long productId) {
        cartService.deleteCartItemByCustomerAndProduct(productId);
        return new ResponseEntity<>("Cart item deleted", HttpStatus.OK);
    }
    
    @GetMapping("/customers")
    public ResponseEntity<Customer> getCustomerById() {
        Customer customer = cartService.getCustomerById(); 
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/totalPrice")
	public Map<String, Object> totalPrice() {
		Map<String, Object> response = new HashMap<>();
		float totalPrice = cartService.calculateTotalPrice();
		
		response.put("totalPrice", totalPrice);

		return response;
	}
    
    @GetMapping("/checkout")
	public Map<String, Object> thanhToan() {
		Map<String, Object> response = new HashMap<>();
		
		float totalPrice = cartService.calculateTotalPrice();
		Customer customer = customerRepository.findById(cartService.getCustomerId()).orElse(null);
		List<CartItem> cartItem = cartRepository.findByCustomer(customer.getId());
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer(customer);
		customerOrder.setDate(LocalDate.now());
		customerOrder.setStatus(null);
		customerOrder.setTotal((long)totalPrice);
		customerOrder.setEmployee(null);
		customerOrder.setPayment_code(null);
		orderService.save(customerOrder);
		
		orderDetailService.insertItem(cartItem);
		cartService.deleteAllCartItemByCustomer();
		
		response.put("message", "Đã thanh toán thành công");
		
		return response;
	}
}
