package com.teenboutique.web.restcontrollers;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.teenboutique.web.entities.Employee;

import com.teenboutique.web.dto.OrderDetailDto;
import com.teenboutique.web.dto.AuthAdminDto;
import com.teenboutique.web.dto.AuthResponseAdminDto;
import com.teenboutique.web.dto.EmployeeAndRoles;
import com.teenboutique.web.dto.EmployeeDto;
import com.teenboutique.web.dto.EmployeesDto;
import com.teenboutique.web.dto.OrderDto;
import com.teenboutique.web.dto.OrdersDto;
import com.teenboutique.web.dto.ProductDetailDto;
import com.teenboutique.web.dto.ProductDto;
import com.teenboutique.web.dto.ProductEditDto;
import com.teenboutique.web.dto.ProductSizeDto;
import com.teenboutique.web.dto.ProductsDto;
import com.teenboutique.web.entities.Category;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.entities.Role;
import com.teenboutique.web.entities.Size;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.services.CategoryService;

import com.teenboutique.web.services.EmployeeService;
import com.teenboutique.web.services.OrderService;
import com.teenboutique.web.services.ProductDetailService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.services.RoleService;
import com.teenboutique.web.services.SizeService;

import jakarta.validation.constraints.Null;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
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
	
	@PostMapping("/auth")
	public ResponseEntity<AuthResponseAdminDto> login(@RequestBody AuthAdminDto authAdminDto){
		AuthResponseAdminDto authResponseAdminDto = new AuthResponseAdminDto();
		String res = employeeService.loginEncr(Long.parseLong(authAdminDto.getId()), authAdminDto.getPass());
		if("Not found".equals(res)) {
			authResponseAdminDto.setJwt("Fail");
			return new ResponseEntity<AuthResponseAdminDto>(authResponseAdminDto, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		if("Wrong pass".equals(res)) {
			authResponseAdminDto.setJwt("Fail");
			return new ResponseEntity<AuthResponseAdminDto>(authResponseAdminDto, HttpStatus.NOT_ACCEPTABLE);
		}
		authResponseAdminDto.setJwt(res);
		return new ResponseEntity<AuthResponseAdminDto>(authResponseAdminDto, HttpStatus.OK);
	}
	
	//**Management part
	// *Employee
	
	@GetMapping("/management/employees")
	public ResponseEntity<EmployeesDto> showAllEmployee() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		return showAllEmployeeOnePage(1);
	}

	@GetMapping("/management/employees/{page}")
	public ResponseEntity<EmployeesDto> showAllEmployeeOnePage(@PathVariable("page") int currentPage) {
		Page<Employee> page = employeeService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<Employee> emps = page.getContent();
		EmployeesDto eDto = new EmployeesDto();
		eDto.setCurrentPage(currentPage);
		eDto.setTotalPage(totalPages);
		eDto.setEmployees(emps);
		return new ResponseEntity<EmployeesDto>(eDto, HttpStatus.OK);
	}
	
	@PostMapping("/management/employee")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setName(employeeDto.getName());
		employee.setRole(roleService.findById(Long.parseLong(employeeDto.getRole())));
		employee.setAddress(employeeDto.getAddress());
		employee.setEmail(employeeDto.getEmail());
		employee.setDob(employeeDto.getDob());
		employee.setGender(employeeDto.isGender());
		employee.setPhone(employeeDto.getPhone());
		employee.setStarting_date(employeeDto.getStarting_date());
		employee.setResigning_date(employeeDto.getResigning_date());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Long lastEmp = employeeService.findAll().get(employeeService.findAll().size() - 1).getId() + 1;
		employee.setPassword(bCryptPasswordEncoder.encode(lastEmp.toString()));
		employeeService.save(employee);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}
	
	@GetMapping("/management/employee/{id}")
	public ResponseEntity<EmployeeDto> showDetailEmployeeForm(@PathVariable("id") Long id) {
		EmployeeDto e = new EmployeeDto();
		e.ConvertEmployeeToDto(employeeService.getEmpById(id));
		
		return new ResponseEntity<EmployeeDto>(e, HttpStatus.OK);
	}
	
	@PutMapping("/management/employee")
	public ResponseEntity<EmployeeDto> upDateEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = employeeService.getEmpById(employeeDto.getId());
		employee.setName(employeeDto.getName());
		employee.setRole(roleService.findById(Long.parseLong(employeeDto.getRole())));
		employee.setAddress(employeeDto.getAddress());
		employee.setEmail(employeeDto.getEmail());
		employee.setDob(employeeDto.getDob());
		employee.setGender(employeeDto.isGender());
		employee.setPhone(employeeDto.getPhone());
		employee.setStarting_date(employeeDto.getStarting_date());
		employee.setResigning_date(employeeDto.getResigning_date());
		employeeService.save(employee);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}
	
	@GetMapping("/management/employeeAndRoles/{id}")
	public ResponseEntity<EmployeeAndRoles> showAllEntityRole(@PathVariable("id") Long id) {
		List<Role> roles = roleService.findAllRole();
		EmployeeDto e = new EmployeeDto();
		e.ConvertEmployeeToDto(employeeService.getEmpById(id));
		return new ResponseEntity<EmployeeAndRoles>(new EmployeeAndRoles(e, roles), HttpStatus.OK);
	}
	
	@DeleteMapping("/management/employee")
	public ResponseEntity<EmployeeDto> deleteEmployee(@RequestBody Long id) {
		Employee employee = employeeService.getEmpById(id);
		employee.setResigning_date(LocalDate.now());
		employeeService.save(employee);
		return new ResponseEntity<EmployeeDto>(new EmployeeDto(), HttpStatus.OK);
	}
	
	@PostMapping("/management/role")
	public ResponseEntity<Role> showCreateRoleForm(@RequestBody String name) {
		Role role = new Role();
		role.setName(name);
		return new ResponseEntity<Role>(roleService.save(role), HttpStatus.OK);
	}
	
	//*Product
	@GetMapping("/management/products")
	public ResponseEntity<ProductsDto> showAllProduct() {
		showAllProductOnePage(1);
		return showAllProductOnePage(1);
	}

	@GetMapping("/management/products/{page}")
	public ResponseEntity<ProductsDto> showAllProductOnePage(@PathVariable("page") int currentPage) {
		return new ResponseEntity<ProductsDto>(productService.findPageProductDto(currentPage), HttpStatus.OK);
	}
	
	@GetMapping("/management/product/id")
	public ResponseEntity<String> lastId() {
		Long last = productService.getLatsId() + 1L;
		return new ResponseEntity<String>(last.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/management/category")
	public ResponseEntity<ProductDto> createCategory(@RequestBody String name) {
		Category category = new Category();
		category.setName(name);
		categoryService.add(category);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@PostMapping("/management/size")
	public ResponseEntity<ProductDto> createSize(@RequestBody String name) {
		Size size = new Size();
		size.setName(name);
		sizeService.add(size);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@GetMapping("/management/product/{id}")
	public ResponseEntity<ProductDto> showDetailProductForm(@PathVariable("id") Long id) {
		return new ResponseEntity<ProductDto>(productService.findProductDto(id), HttpStatus.OK);
	}
	
	@GetMapping("/management/product/edit/{id}")
	public ResponseEntity<ProductEditDto> showEditProductForm(@PathVariable("id") Long id) {		
		return new ResponseEntity<ProductEditDto>(new ProductEditDto(productService.findProductDto(id), categoryService.findAllDto()), HttpStatus.OK);
	}
	
	@PostMapping("/management/product")
	public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setCategory(categoryService.findById(productDto.getCategory().getId()));
		return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
	}
	
	@PostMapping("/management/product/create/image")
	public ResponseEntity<ProductDto> createImageProduct(@RequestPart("product") String product, @RequestPart("file") MultipartFile file) {
		productService.createProduct(productService.findById(Long.parseLong(product)), file, null);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@PostMapping("/management/product/create/images")
	public ResponseEntity<ProductDto> createImagesProduct(@RequestPart("product") String product, @RequestPart("files") MultipartFile[] files) {
		productService.createProduct(productService.findById(Long.parseLong(product)), null, files);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@PutMapping("/management/product")
	public ResponseEntity<ProductDto> upDateProduct(@RequestBody ProductDto productDto) {
		Product existedProduct = productService.findById(productDto.getId());
		existedProduct.setCategory(categoryService.findById(productDto.getCategory().getId()));
		existedProduct.setDescription(productDto.getDescription());
		existedProduct.setName(productDto.getName());
		existedProduct.setPrice(productDto.getPrice());
		productService.editOne(productDto.getId(), existedProduct, null);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@PostMapping("/management/product/update/image")
	public ResponseEntity<ProductDto> upDateImageProduct(@RequestPart("product") String product, @RequestPart("file") MultipartFile file) {
		productService.editOne(Long.parseLong(product), productService.findById(Long.parseLong(product)), file);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
	@GetMapping("/management/sizes")
	public ResponseEntity<List<Size>> getAllSizes() {		
		return new ResponseEntity<List<Size>>(sizeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/management/categories")
	public ResponseEntity<List<Category>> getAllCategories() {		
		return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/management/product/detail")
	public ResponseEntity<ProductSizeDto> addDetail(@RequestBody ProductSizeDto productSizeDto) {
		ProductDetail pd = new ProductDetail();
		pd.setInventory(productSizeDto.getQuantity());
		pd.setProduct(productService.findById(productSizeDto.getId()));
		pd.setSize(sizeService.findById(productSizeDto.getSize()));
		productDetailService.add(pd);
		return new ResponseEntity<ProductSizeDto>(new ProductSizeDto(), HttpStatus.OK);
	}
	
	@PutMapping("/management/product/detail")
	public ResponseEntity<ProductSizeDto> importDetail(@RequestBody ProductSizeDto productSizeDto) {
		productDetailService.importDetail(productSizeDto.getId(), productSizeDto.getSize(), productSizeDto.getQuantity());
		return new ResponseEntity<ProductSizeDto>(new ProductSizeDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/management/product/detail")
	public ResponseEntity<ProductSizeDto> exportDetail(@RequestBody ProductSizeDto productSizeDto) {
		productDetailService.exportDetail(productSizeDto.getId(), productSizeDto.getSize(), productSizeDto.getQuantity());
		return new ResponseEntity<ProductSizeDto>(new ProductSizeDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/management/product")
	public ResponseEntity<ProductDto> removeProduct(@RequestBody Long id) {
		Product p = productService.findById(id);
		productService.stopSell(p);
		return new ResponseEntity<ProductDto>(new ProductDto(), HttpStatus.OK);
	}
	
//	**Receipt part
	@GetMapping("/receipts")
	public ResponseEntity<OrdersDto> showAllOrder() {
		return showAllOrderOnePage(1);
	}

	@GetMapping("/receipts/{page}")
	public ResponseEntity<OrdersDto> showAllOrderOnePage(@PathVariable("page") int currentPage) {
		Page<CustomerOrder> page = orSer.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<OrderDto> ors = new ArrayList<OrderDto>();
		for(CustomerOrder c:page.getContent()) {
			OrderDto orderDto = new OrderDto(c.getId(), c.getPayment_code(), c.getDate(), c.getTotal(), 
					c.getStatus(), null, c.getCustomer().getId().toString());
			ors.add(orderDto);
		}
		OrdersDto ordersDto = new OrdersDto(ors, currentPage, totalPages);
		

		return new ResponseEntity<OrdersDto>(ordersDto, HttpStatus.OK);
	}
	
	@GetMapping("/receipt/{id}")
	public ResponseEntity<List<OrderDetailDto>> takeOrder(@PathVariable("id") Long id) {
		List<OrderDetailDto> orderDetailDtos = new ArrayList<OrderDetailDto>();
		for(CustomerOrderItem c:orSer.findById(id).getOrder_items()) {
			orderDetailDtos.add(new OrderDetailDto(c.getProduct_detail().getProduct().getId(), 
					c.getProduct_detail().getSize().getId(), c.getQuantity(), c.getPrice()));
		}
		return new ResponseEntity<List<OrderDetailDto>>(orderDetailDtos, HttpStatus.OK);
	}
	
	@PutMapping("/receipt")
	public ResponseEntity<Integer> takedOrder(@RequestBody Long id) {
		orSer.take(id);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
//	**Statistic part
	@GetMapping("/statistic/all/{start}/{end}")
	public ResponseEntity<String> getAllStatistic(@PathVariable("start") LocalDate start, @PathVariable("end") LocalDate end) {
		return new ResponseEntity<String>(orSer.findOrderInRange(start, end).toString(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
