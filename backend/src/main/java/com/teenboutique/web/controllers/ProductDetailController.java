package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.entities.Size;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.services.CustomerService;
import com.teenboutique.web.services.ProductDetailService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.services.SizeService;
import java.util.List;

@Controller
public class ProductDetailController {

	@Autowired
	private ProductService proSer;

	@Autowired
	private ProductDetailService proDeSer;

	@Autowired
	private SizeService sizeSer;

	@Autowired
	private CustomerService customerService;


	@GetMapping("/main/productdetail/{id}")
	public String showDetailProductForm(Model model, @PathVariable("id") Long id) {
		Product p = proSer.findById(id);
		model.addAttribute("product", p);
		model.addAttribute("localPic", Helper.checkImg(p.getImages().get(0).getUrl()));

		List<Size> sizes = sizeSer.findAll();
		model.addAttribute("sizes", sizes);
		return "productdetail";
	}

	@GetMapping("/main/productdetail/AddToCart/{id}")
	public String addToCart(Model model, @PathVariable("id") Long id, @RequestParam("quantity") int quantity,
			@RequestParam("size") Long s) {		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();			
		Long iduser =  customerService.getCusByEmail(name).getId();				
		CartItem c = proDeSer.findById(iduser, id, s);
				
		if (c != null) {
			proDeSer.addtoCart(iduser, s, id, quantity);
		}
		if (c== null) {
			proDeSer.createCartItem(iduser, s, id, quantity);
		}


		return "redirect:/main/productdetail/"+id;
	}

}
