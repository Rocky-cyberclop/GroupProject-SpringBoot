package com.teenboutique.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.teenboutique.web.entities.Category;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService proSer;
	
	@Autowired
	private CategoryService cateSer;
	
	@GetMapping("/main/product/{page}")
	public String showProduct(Model model, @PathVariable("page") int currentPage) {	
		Page<Product> page = proSer.findProductPage(currentPage);
		int totalPages = page.getTotalPages();
		List<Product> products = page.getContent();		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("products", products);
		return "product";
	}
	
	@GetMapping("/main/product/{category}/{page}")
	public String showProductCate(Model model, @PathVariable("category") Long cateid, @PathVariable("page") int currentPage) {	
		
		Page<Product> page = proSer.findProductCatePage(cateid, currentPage);	
		
		int totalPages = page.getTotalPages();
		Category c = cateSer.findById(cateid);
        model.addAttribute("category", c);
        
		List<Product> products = page.getContent();		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("products", products);
		return "product";
	}
	
	
}
