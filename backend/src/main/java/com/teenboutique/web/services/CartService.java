package com.teenboutique.web.services;

import com.teenboutique.web.dto.CartItemDto;
import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.repositories.CartRepository;
import com.teenboutique.web.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

	private final CustomerRepository customerRepository;
	
	private final CustomerService customerService;

	private final CartRepository cartRepository;

//    private Long customerId = 293915L;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    public List<CartItem> getCartItemsByCustomer() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        // Trả về danh sách các sản phẩm trong giỏ hàng của khách hàng
        return cartRepository.findByCustomer(customerId);
    }
	
	public List<CartItemDto> getCartItemsByCustomerId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        List<CartItem> cartItems = cartRepository.findByCustomer(customerId);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setProduct_id(cartItem.getProduct_detail().getProduct().getId());
            cartItemDto.setName(cartItem.getProduct_detail().getProduct().getName());
            cartItemDto.setPrice(cartItem.getProduct_detail().getProduct().getPrice());
            cartItemDto.setSize_id(cartItem.getProduct_detail().getSize().getId());
            cartItemDto.setCustomer_id(cartItem.getCustomer().getId());
            cartItemDto.setQuantity(cartItem.getQuantity());
            cartItemDtos.add(cartItemDto);
        }
        return cartItemDtos;
    }

    public void deleteCartItemByCustomerAndProduct(Long productId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        cartRepository.deleteCartItemByCustomerAndProduct(customerId, productId);
    }
    
    public void deleteAllCartItemByCustomer() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        cartRepository.deleteAllCartItemByCustomer(customerId);
    }

    public float calculateTotalPrice() {
        // Tính tổng tiền của giỏ hàng
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        List<CartItem> cartItems = cartRepository.findByCustomer(customerId);
        float totalPrice = 0L;
        for (CartItem item : cartItems) {
            totalPrice += item.getProduct_detail().getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
	}

	public Long getCustomerId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
		return customerId;
	}
	
	public Customer getCustomerById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		Long customerId =  customerService.getCusByEmail(authentication.getName()).getId();
        return customerRepository.findById(customerId).orElse(null);
    }
}
