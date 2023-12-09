package com.teenboutique.web.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.dto.ProductDto;
import com.teenboutique.web.dto.ProductSizeDto;
import com.teenboutique.web.dto.ThanhThanhProDetailDto;
import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.EmployeeService;
import com.teenboutique.web.services.OrderService;
import com.teenboutique.web.services.ProductDetailService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.services.RoleService;
import com.teenboutique.web.services.SizeService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/main")
public class ProDetailRestController {
	@Autowired
	private OrderService orSer;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private ProductDetailService productDetailService;
	
	private long iduser = 857;

	@PostMapping("/productdetail")
	public ResponseEntity<ThanhThanhProDetailDto> addToCart(@RequestBody ThanhThanhProDetailDto ttProDetailDto) {
		System.out.println(ttProDetailDto.getSize());		
		CartItem c = productDetailService.findById(iduser, ttProDetailDto.getId(),ttProDetailDto.getSize());
	
		if (c != null) {
			productDetailService.addtoCart(iduser,ttProDetailDto.getSize(),ttProDetailDto.getId(),ttProDetailDto.getQuantity());
		}
		if (c== null) {
			productDetailService.createCartItem(iduser,ttProDetailDto.getSize(),ttProDetailDto.getId(),ttProDetailDto.getQuantity());
		}

		return new ResponseEntity<ThanhThanhProDetailDto>(new ThanhThanhProDetailDto(), HttpStatus.OK);
	}
	
	
	
}
