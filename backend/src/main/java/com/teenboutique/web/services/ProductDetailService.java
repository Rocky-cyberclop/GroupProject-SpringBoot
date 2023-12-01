package com.teenboutique.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.repositories.ProductDetailRepository;
import com.teenboutique.web.repositories.ProductRepository;

@Service
public class ProductDetailService {
	@Autowired
	private ProductDetailRepository proDeRepo;
	
	public ProductDetail add(ProductDetail pd) {
		pd.setId(pd.getProduct().getId(), pd.getSize().getId());;
		return proDeRepo.save(pd);
	}
	
	public ProductDetail findByIdAndSize(Long id, Long size) {
		ProductDetail pd = proDeRepo.findByProductAndSize(id, size);
		return pd;
	}
	
	public ProductDetail importDetail(Long id, Long size, int quantity) {
		ProductDetail pd = this.findByIdAndSize(id, size);
		pd.setInventory(quantity+pd.getInventory());
		return proDeRepo.save(pd);
	}
	
	public ProductDetail exportDetail(Long id, Long size, int quantity) {
		ProductDetail pd = this.findByIdAndSize(id, size);
		if(pd.getInventory()>0)pd.setInventory(pd.getInventory()-quantity);
		return proDeRepo.save(pd);
	}
	
}
