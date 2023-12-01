package com.teenboutique.web.controllers;

import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.repositories.CartRepository;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.services.CartService;
import com.teenboutique.web.services.OrderDetailService;
import com.teenboutique.web.services.OrderService;
import com.teenboutique.web.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

	private final CartService cartService;
	private CustomerRepository customerRepository;
	private CartRepository cartRepository;
	@Autowired
	private OrderService orderService; 
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ProductService productService;

	@Autowired
	public CartController(CartService cartService, CustomerRepository customerRepository,CartRepository cartRepository) {
		this.cartRepository = cartRepository;
		this.cartService = cartService;
		this.customerRepository = customerRepository;
	}

	@GetMapping("/cart")
	public String viewCart(Model model) {
		List<CartItem> cartItems = cartService.getCartItemsByCustomer();
		double totalPrice = cartService.calculateTotalPrice();
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalPrice", totalPrice);
		return "cart";
	}

	@GetMapping("/cart/remove/{id}")
	public String removeCartItem(@PathVariable("id") Long productId) {
		cartService.deleteCartItemByCustomerAndProduct(productId);
		return "redirect:/cart";
	}

	@GetMapping("/cart/checkout")
	public String checkoutCart(Model model) {
		Customer customer = customerRepository.findById(cartService.getCustomerId()).orElse(null);
		List<CartItem> cartItems = cartService.getCartItemsByCustomer();
		float totalPrice = cartService.calculateTotalPrice();
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("customer", customer);

		return "/checkout";
	}

	@GetMapping("/cart/thanhToan")
	public String thanhToan(Model model) {
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
//		customerOrder.setId(orderService.lastItem().getId() + 1L);
		orderService.save(customerOrder);
		
		orderDetailService.insertItem(cartItem);
		cartService.deleteAllCartItemByCustomer();
		return "/main";
	}
}
