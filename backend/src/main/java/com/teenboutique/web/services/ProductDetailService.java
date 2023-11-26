package com.teenboutique.web.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.entities.ProductImage;
import com.teenboutique.web.entities.Size;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.repositories.CartItemRepository;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.repositories.ProductDetailRepository;
import com.teenboutique.web.repositories.ProductRepository;

@Service
public class ProductDetailService {
	@Autowired
	private ProductDetailRepository proDeRepo;

	@Autowired
	private CartItemRepository cartRepo;

	@Autowired
	private CustomerRepository cusRepo;

	public ProductDetail add(ProductDetail pd) {
		pd.setId(pd.getProduct().getId(), pd.getSize().getId());
		;
		return proDeRepo.save(pd);
	}

	public ProductDetail findByIdAndSize(Long id, Long size) {
		ProductDetail pd = proDeRepo.findByProductAndSize(id, size);
		return pd;
	}

	public ProductDetail importDetail(Long id, Long size, int quantity) {
		ProductDetail pd = this.findByIdAndSize(id, size);
		pd.setInventory(quantity + pd.getInventory());
		return proDeRepo.save(pd);
	}

	public ProductDetail exportDetail(Long id, Long size, int quantity) {
		ProductDetail pd = this.findByIdAndSize(id, size);
		pd.setInventory(pd.getInventory() - quantity);
		return proDeRepo.save(pd);
	}
	//////

	public CartItem findById(Long id, Long product, Long size) {
		CartItem pd = cartRepo.findByIdAndProductAndSize(id, product, size);
		return pd;
	}

	public CartItem addtoCart(Long customer_id, Long size, Long productid, int quantity) {
		CartItem pd = cartRepo.findByIdAndProductAndSize(customer_id, productid, size);
		pd.setQuantity(pd.getQuantity() + quantity);
		return cartRepo.save(pd);
	}

	public CartItem createCartItem(Long customer_id, Long size, Long productid, int quantity) {
		cartRepo.createCartItem(customer_id, quantity, productid, size);

		
		return null;
		
	}

	/////
	public List<ProductDetail> getAll() {
		return proDeRepo.findAll();
	}

}
