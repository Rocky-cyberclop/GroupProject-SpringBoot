package com.teenboutique.web.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.teenboutique.web.dto.ProductsDto;
import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	@Autowired
	private ProductService proSer;
	
	@Autowired
	private CategoryService cateSer;
	
	@GetMapping("/main/product/{page}")
	public ResponseEntity<ProductsDto> showProduct(@PathVariable("page") int currentPage) {
		return new ResponseEntity<ProductsDto>(proSer.findPageDto(currentPage), HttpStatus.OK);	
	}
	
	@GetMapping("/main/product")
	public ResponseEntity<ProductsDto> showAllProduct() {
		showProduct(1);
		return showProduct(1);
	}
	
	@GetMapping("/main/product/category/{id}")
	public ResponseEntity<ProductsCategory> showProductCate(@PathVariable("id") Long id) {
		showProductCatePage(id,1);
		return showProductCatePage(id,1);
	}
	
	@GetMapping("/main/product/category/{id}/{page}")
	public ResponseEntity<ProductsCategory> showProductCatePage(@PathVariable("id") Long id , @PathVariable("page") int currentPage) {
		ProductsCategory productsCategory = new ProductsCategory(proSer.findCateDto(id, currentPage), cateSer.findById(id).getName());
		return new ResponseEntity<ProductsCategory>(productsCategory, HttpStatus.OK);	
	}
	
	public class ProductsCategory {
	    public ProductsDto productsDto;
	    public String categoryName;
	    
	    public ProductsCategory(ProductsDto productsDto, String categoryName) {
	    	this.productsDto = productsDto;
	    	this.categoryName = categoryName;
	    }
	    
	    public ProductsDto getProductsDto() {
	    	return this.productsDto;
	    }
	    
	    public String getCategory() {
	    	return this.categoryName;
	    }
	    
	}
	
}
