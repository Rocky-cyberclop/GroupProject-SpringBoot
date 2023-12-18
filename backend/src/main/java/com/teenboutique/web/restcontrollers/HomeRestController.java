package com.teenboutique.web.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.entities.Category;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.dto.CategoryDto;
import com.teenboutique.web.dto.ProductDto;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class HomeRestController {
	@Autowired
	private CategoryService cateSer;
	
	@Autowired
	private ProductService proSer;
	
//	@GetMapping("/main/getCate")
//    public ResponseEntity<List<CategoryDto>> showCateData() {
//		return new ResponseEntity<List<CategoryDto>>(cateSer.findAllDto(), HttpStatus.OK);		
//    }
//	
//	@GetMapping("/main/getPro")
//    public ResponseEntity<List<ProductDto>> showProductData() {
//		return new ResponseEntity<List<ProductDto>>(proSer.findTrendyDto(), HttpStatus.OK);	
//    }
	
	@GetMapping("/main")
	public ResponseEntity<HomeResponse> showMainData() {
		ResponseEntity<List<CategoryDto>> categoryDtos = new ResponseEntity<List<CategoryDto>>(cateSer.findAllDto(), HttpStatus.OK);
		ResponseEntity<List<ProductDto>> productDtos = new ResponseEntity<List<ProductDto>>(proSer.findTrendyDto(), HttpStatus.OK);
		HomeResponse homeRes = new HomeResponse(categoryDtos, productDtos);
		return new ResponseEntity<HomeResponse>(homeRes, HttpStatus.OK);	
	}
	
	private static class HomeResponse {
        private final ResponseEntity<List<CategoryDto>> categoryDtos;
        private final ResponseEntity<List<ProductDto>> productDtos;

        public HomeResponse(ResponseEntity<List<CategoryDto>> categoryDtos, ResponseEntity<List<ProductDto>> productDtos) {
            this.categoryDtos = categoryDtos;
            this.productDtos = productDtos;
        }

        public ResponseEntity<List<CategoryDto>> getCategories() {
            return categoryDtos;
        }

        public ResponseEntity<List<ProductDto>> getProducts() {
            return productDtos;
        }
    }
	
}
