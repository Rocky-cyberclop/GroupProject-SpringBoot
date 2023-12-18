package com.teenboutique.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.entities.Category;
import com.teenboutique.web.entities.Product;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService cateSer;
	
	@Autowired
	private ProductService proSer;
	
	 @Autowired
	 public HomeController(CategoryService cateSer, ProductService proSer) {
	    this.cateSer = cateSer;
	    this.proSer = proSer;
	 }
	
	@GetMapping
	public String showMainPage(Model model) {
		List<Category> categories = cateSer.findAllCategory();
		List<Product> products = proSer.getTrendyProduct();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return "/main";
	}
	

}
