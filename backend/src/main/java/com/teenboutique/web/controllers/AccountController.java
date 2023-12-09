package com.teenboutique.web.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.repositories.CustomerOrderItemRepository;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.repositories.ProductRepository;
import com.teenboutique.web.repositories.SizeRepository;
import com.teenboutique.web.services.CustomerService;
import com.teenboutique.web.services.CustomerSevices;

@Controller
@RequestMapping("/account")
public class AccountController {

	private CustomerRepository customerRepository;
	private CustomerSevices customerSevices;
	private CustomerOrderItemRepository customerOderItemRepository;
	private ProductRepository productRepository;
	private SizeRepository sizeRepository;
	private long idus = 293767;

	@Autowired
	private CustomerService customerService;

	public AccountController(CustomerRepository customerRepository, CustomerSevices customerSevices,
			CustomerOrderItemRepository customerOderItemRepository, ProductRepository productRepository,
			SizeRepository srepository) {
		super();
		this.customerRepository = customerRepository;
		this.customerSevices = customerSevices;
		this.customerOderItemRepository = customerOderItemRepository;
		this.productRepository = productRepository;
		this.sizeRepository = srepository;
	}

	@GetMapping()
	public String shownDashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();			
		Long iduser =  customerService.getCusByEmail(name).getId();	
		
		Customer customer = customerRepository.findById(iduser).orElse(null);

		if (customer != null) {
			model.addAttribute("user", customer);
			List<CustomerOrder> customerOrders = customer.getOrders();
			int sl[] = new int[customerOrders.size()];
			for (int i = 0; i < customerOrders.size(); i++) {
				sl[i] = customerOrders.get(i).getOrder_items().size();
				model.addAttribute("sl", sl);
				model.addAttribute("customerOrders", customerOrders);
			}
		}

		return "account/dashboard";
	}

	@GetMapping("/order")
	public String shownOrder(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();			
		Long iduser =  customerService.getCusByEmail(name).getId();	
		
//Lấy khách hàng thông qua id
		Customer customer = customerRepository.findById(iduser).orElse(null);

		if (customer != null) {
			List<CustomerOrder> customerOrders = customer.getOrders();
			int sl[] = new int[customerOrders.size()];
			for (int i = 0; i < customerOrders.size(); i++) {
				sl[i] = customerOrders.get(i).getOrder_items().size();

			}
			model.addAttribute("sl", sl);
			model.addAttribute("customerOrders", customerOrders);
		}

		return "account/order";
	}

	@GetMapping("/profileDetails")
	public String shownProfileDetails(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();			
		Long iduser =  customerService.getCusByEmail(name).getId();	
		
		Customer customer = customerRepository.findById(iduser).orElse(null);
		model.addAttribute("user", customer);
		return "account/profiledetails";
	}

	// Lớp nội tại tĩnh
	public static class TemporaryClass {
		String productName;
		int productQuantity;
		float price;
		String size;

		public TemporaryClass(String productName, int productQuantity, float price, String size) {
			super();
			this.productName = productName;
			this.productQuantity = productQuantity;
			this.price = price;
			this.size = size;

		}

		public TemporaryClass() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getProductQuantity() {
			return productQuantity;
		}

		public void setProductQuantity(int productQuantity) {
			this.productQuantity = productQuantity;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

	}

	@GetMapping("/orderItemDetail/{id}")
	public String shownOrderItemDetail(@PathVariable("id") long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();			
		Long iduser =  customerService.getCusByEmail(name).getId();	
		
		
		List<CustomerOrderItem> customerOrderItems = customerOderItemRepository.findByCustomerOrderId(iduser);
		List<TemporaryClass> list = new ArrayList<>();
		float sum = 0;
		for (CustomerOrderItem customerOrderItem : customerOrderItems) {
			TemporaryClass class1 = new TemporaryClass();
			long product_id = customerOrderItem.getId().getProduct_detail_id().getProduct_id();
			class1.setProductName(productRepository.findById(product_id).orElse(null).getName());
			class1.setProductQuantity(customerOrderItem.getQuantity());
			class1.setPrice(customerOrderItem.getPrice());
			sum += (customerOrderItem.getPrice() * customerOrderItem.getQuantity());
			long size_id = customerOrderItem.getId().getProduct_detail_id().getSize_id();
			class1.setSize(sizeRepository.findById(size_id).orElse(null).getName());
			list.add(class1);

		}
		model.addAttribute("sum", sum);
		model.addAttribute("lists", list);
		model.addAttribute("id", id);
		return "account/orderItemDetail";
	}

	@GetMapping("/Update_personal_information/{id}")
	public String personal_information_edit(Model model, @PathVariable("id") Long id) {
		Customer customer = new Customer();
		customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			return "account/err";
		}

		model.addAttribute("customer", customer);
		return "account/updatePersonalInformation";

	}

	@PostMapping("/Update_personal_information/{id}")
	public String Update_personal_information(Model model, @PathVariable("id") Long id,
			@RequestParam(value = "hoTen", required = false) String hoTen,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "soDienThoai", required = true) String soDienThoai,
			@RequestParam(value = "ngayThang", required = true) String ngayThang,
			@RequestParam(value = "diaChi", required = true) String diaChi,
			@RequestParam(value = "gioiTinh", required = true) boolean gioiTinh) {
		System.out.println(id);
		System.out.println(hoTen);
		System.out.println(email);
		System.out.println(soDienThoai);
		System.out.println(ngayThang);
		System.out.println(diaChi);
		System.out.println(gioiTinh);

		
		
		Customer customer = new Customer();
		customer = customerRepository.findById(id).orElse(customer);
		customer.setAddress(diaChi);
		customer.setEmail(email);
		customer.setGender(gioiTinh);
		customer.setName(hoTen);
		customer.setPhone(soDienThoai);

		// Định dạng ngày tháng
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// Chuyển chuỗi sang LocalDate
		LocalDate localDate = LocalDate.parse(ngayThang, formatter);
		customer.setDob(localDate);
		customerRepository.save(customer);
		String path = "redirect:/account/profileDetails";
		return path;
	}

}
