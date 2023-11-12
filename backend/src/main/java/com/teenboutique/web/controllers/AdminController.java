package com.teenboutique.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.teenboutique.web.entities.Category;
import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.entities.Role;
import com.teenboutique.web.entities.Size;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.services.CategoryService;
import com.teenboutique.web.services.EmployeeService;
import com.teenboutique.web.services.OrderDetailService;
import com.teenboutique.web.services.OrderService;
import com.teenboutique.web.services.ProductDetailService;
import com.teenboutique.web.services.ProductService;
import com.teenboutique.web.services.RoleService;
import com.teenboutique.web.services.SizeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EmployeeService empSer;

	@Autowired
	private RoleService roleSer;

	@Autowired
	private ProductService proSer;

	@Autowired
	private ProductDetailService proDeSer;

	@Autowired
	private CategoryService cateSer;

	@Autowired
	private SizeService sizeSer;
	
	@Autowired
	private OrderService orSer;
	
	@Autowired
	private OrderDetailService orDeSer;

	@GetMapping
	public String showMainPage() {
		return "admin/main";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "admin/login";
	}

	@PostMapping("/login")
	public String login(Model model, @RequestParam("id") String id, @RequestParam("pass") String pass) {
		if (pass.charAt(0) == 's' && pass.charAt(1) == 'b') {
			if (empSer.login(Long.parseLong(id), pass) != null)
				return "admin/main";
		}
		Employee e = empSer.getEmpById(Long.parseLong(id));
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (bCryptPasswordEncoder.matches(pass, e.getPassword()))
			return "admin/main";
		model.addAttribute("error", "Bạn đã nhập sai mã nhân viên hoặc mật khẩu");
		return "admin/login";
	}

	// **Management part
	// *Employee

	@GetMapping("/management/employees")
	public String showAllEmployee(Model model) {
		showAllEmployeeOnePage(model, 1);
		return "admin/employee";
	}

	@GetMapping("/management/employees/{page}")
	public String showAllEmployeeOnePage(Model model, @PathVariable("page") int currentPage) {
		Page<Employee> page = empSer.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<Employee> emps = page.getContent();

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("emps", emps);

		return "admin/employee";
	}

	@GetMapping("/management/employee/create")
	public String showCreateEmployeeForm(Model model) {
		Employee employee = new Employee();
		List<Role> roles = roleSer.findAllRole();
		model.addAttribute("roles", roles);
		model.addAttribute("employee", employee);
		return "admin/employee-create";
	}

	@PostMapping("/management/employee/create")
	public String showCreateEmployeeForm(Employee e) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Long lastEmp = empSer.findAll().get(empSer.findAll().size() - 1).getId() + 1;
		e.setPassword(bCryptPasswordEncoder.encode(lastEmp.toString()));
		empSer.save(e);
		return "redirect:/admin/management/employees";
	}

	@GetMapping("/management/employee/detail/{id}")
	public String showDetailEmployeeForm(Model model, @PathVariable("id") Long id) {
		Employee e = empSer.getEmpById(id);
		model.addAttribute("employee", e);
		return "admin/employee-detail";
	}

	@GetMapping("/management/employee/edit/{id}")
	public String showEditEmployeeForm(Model model, @PathVariable("id") Long id) {
		Employee e = empSer.getEmpById(id);
		List<Role> roles = roleSer.findAllRole();
		model.addAttribute("employee", e);
		model.addAttribute("roles", roles);
		return "admin/employee-edit";
	}

	@PostMapping("/management/employee/edit/{id}")
	public String editEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee e) {
		Employee existedEmp = empSer.getEmpById(id);
		existedEmp.setName(e.getName());
		existedEmp.setAddress(e.getAddress());
		existedEmp.setPhone(e.getPhone());
		existedEmp.setEmail(e.getEmail());
		existedEmp.setPhone(e.getPhone());
		existedEmp.setDob(e.getDob());
		existedEmp.setPhone(e.getPhone());
		existedEmp.setStarting_date(e.getStarting_date());
		existedEmp.setResigning_date(e.getResigning_date());
		existedEmp.setGender(e.isGender());
		existedEmp.setRole(e.getRole());
		empSer.save(existedEmp);
		return "redirect:/admin/management/employees";
	}

	@GetMapping("/management/employee/delete/{id}")
	public String showCreateEmployeeForm(@ModelAttribute("id") Long id) {
		Employee employee = empSer.getEmpById(id);
		employee.setResigning_date(LocalDate.now());
		empSer.save(employee);
		return "redirect:/admin/management/employees";
	}

	@GetMapping("/management/role/create")
	public String showCreateRoleForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		return "admin/role-create";
	}

	@PostMapping("/management/role/create")
	public String showCreateRoleForm(Role role) {
		roleSer.save(role);
		return "redirect:/admin/management/employees";
	}

	// *Product
	@GetMapping("/management/products")
	public String showAllProduct(Model model) {
		showAllProductOnePage(model, 1);
		return "admin/products";
	}

	@GetMapping("/management/products/{page}")
	public String showAllProductOnePage(Model model, @PathVariable("page") int currentPage) {
		Page<Product> page = proSer.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<Product> products = page.getContent();

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("products", products);

		return "admin/products";
	}

	@GetMapping("/management/category/create")
	public String showCreateCateForm(Model model) {
		Category cate = new Category();
		model.addAttribute("cate", cate);
		return "admin/category-create";
	}

	@PostMapping("/management/category/create")
	public String createCate(Model model, @ModelAttribute("cate") Category c) {
		cateSer.add(c);
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/size/create")
	public String showCreateSizeForm(Model model) {
		Size size = new Size();
		model.addAttribute("size", size);
		return "admin/size-create";
	}

	@PostMapping("/management/size/create")
	public String createSize(Model model, @ModelAttribute("size") Size size) {
		sizeSer.add(size);
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product/create")
	public String showCreateProductForm(Model model) {
		Product p = new Product();
		List<Category> c = cateSer.findAll();
		model.addAttribute("product", p);
		model.addAttribute("categories", c);
		return "admin/product-create";
	}

	@PostMapping("/management/product/create")
	public String createProduct(@ModelAttribute("product") Product p, @RequestParam("avatar") MultipartFile avatar,
			@RequestParam("not_avatar") MultipartFile[] not_avatar) {
		proSer.createProduct(p, avatar, not_avatar);
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product/detail/{id}")
	public String showDetailProductForm(Model model, @PathVariable("id") Long id) {
		Product p = proSer.findById(id);
		model.addAttribute("product", p);
		model.addAttribute("localPic", Helper.checkImg(p.getImages().get(0).getUrl()));
		return "admin/product-detail";
	}

	@GetMapping("/management/product/edit/{id}")
	public String showEditProductForm(Model model, @PathVariable("id") Long id) {
		Product p = proSer.findById(id);
		List<Category> categories = cateSer.findAll();
		model.addAttribute("product", p);
		model.addAttribute("localPic", Helper.checkImg(p.getImages().get(0).getUrl()));
		model.addAttribute("categories", categories);
		return "admin/product-detail-edit";
	}

	@PostMapping("/management/product/edit/{id}")
	public String editProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product p,
			@RequestParam("avatar") MultipartFile file) {

		proSer.editOne(id, p, file);
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product_detail/create/{id}")
	public String showAddDetailProductForm(Model model, @PathVariable("id") Long id) {
		ProductDetail pd = new ProductDetail();
		pd.setProduct(proSer.findById(id));
		List<Size> sizes = sizeSer.findAll();
		model.addAttribute("product_detail", pd);
		model.addAttribute("sizes", sizes);
		return "admin/product-detail-create";
	}

	@PostMapping("/management/product_detail/create")
	public String addDetailProduct(@ModelAttribute("product_detail") ProductDetail pd) {
		proDeSer.add(pd);
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product_detail/import/{id}/{size}")
	public String showImportDetailProductForm(Model model, @PathVariable("id") Long id,
			@PathVariable("size") Long size_id) {
		ProductDetail pd = proDeSer.findByIdAndSize(id, size_id);
		model.addAttribute("product_detail", pd);
		model.addAttribute("id", id);
		model.addAttribute("size_id", size_id);
		return "admin/product-detail-import";
	}

	@GetMapping("/management/product_detail/import/save/{id}/{size}")
	public String importDetailProduct(@ModelAttribute("product_detail") ProductDetail pd, @PathVariable("id") Long id,
			@PathVariable("size") Long size_id) {
		proDeSer.importDetail(id, size_id, pd.getInventory());
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product_detail/export/{id}/{size}")
	public String showExportDetailProductForm(Model model, @PathVariable("id") Long id,
			@PathVariable("size") Long size_id) {
		ProductDetail pd = proDeSer.findByIdAndSize(id, size_id);
		model.addAttribute("product_detail", pd);
		model.addAttribute("id", id);
		model.addAttribute("size_id", size_id);
		return "admin/product-detail-export";
	}

	@GetMapping("/management/product_detail/export/save/{id}/{size}")
	public String exportDetailProduct(@ModelAttribute("product_detail") ProductDetail pd, @PathVariable("id") Long id,
			@PathVariable("size") Long size_id) {
		proDeSer.exportDetail(id, size_id, pd.getInventory());
		return "redirect:/admin/management/products";
	}

	@GetMapping("/management/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		Product p = proSer.findById(id);
		proSer.stopSell(p);
		return "redirect:/admin/management/products";
	}
	
//	**Receipt part
	@GetMapping("/receipts")
	public String showAllOrder(Model model) {
		showAllOrderOnePage(model, 1);
		return "admin/receipts";
	}

	@GetMapping("/management/receipts/{page}")
	public String showAllOrderOnePage(Model model, @PathVariable("page") int currentPage) {
		Page<CustomerOrder> page = orSer.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<CustomerOrder> ors = page.getContent();

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orders", ors);

		return "admin/receipts";
	}
	
	@GetMapping("/receipt/take/{id}")
	public String showOrderDetailForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("order_details", orDeSer.findDetail(id));
		model.addAttribute("order_id", id);
		return "admin/receipt-detail";
	}
	
	@GetMapping("/receipt/taked/{id}")
	public String takeOrder(@PathVariable("id") Long id) {
		orSer.take(id);
		return "redirect:/admin/receipts";
	}
	
//	**Statistic part
	@GetMapping("/statistic/all")
	public String statisticAll() {
		return "admin/statistic-all";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
