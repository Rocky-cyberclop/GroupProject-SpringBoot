package com.teenboutique.web.services;

import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.repositories.CartRepository;
import com.teenboutique.web.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

	private final CustomerRepository customerRepository;

	private final CartRepository cartRepository;

    private Long customerId = 392L;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public List<CartItem> getCartItemsByCustomer() {
        // Trả về danh sách các sản phẩm trong giỏ hàng của khách hàng
        return cartRepository.findByCustomer(customerId);
    }

    public void deleteCartItemByCustomerAndProduct(Long productId) {
        cartRepository.deleteCartItemByCustomerAndProduct(customerId, productId);
    }
    
    public void deleteAllCartItemByCustomer() {
        cartRepository.deleteAllCartItemByCustomer(customerId);
    }

    public float calculateTotalPrice() {
        // Tính tổng tiền của giỏ hàng
        List<CartItem> cartItems = cartRepository.findByCustomer(customerId);
        float totalPrice = 0L;
        for (CartItem item : cartItems) {
            totalPrice += item.getProduct_detail().getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
