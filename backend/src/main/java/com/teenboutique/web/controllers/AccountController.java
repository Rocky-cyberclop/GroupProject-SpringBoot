package com.teenboutique.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.repositories.CustomerOrderItemRepository;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.repositories.ProductRepository;
import com.teenboutique.web.repositories.SizeRepository;
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
		Customer customer = customerRepository.findById(idus).orElse(null);

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

//Lấy khách hàng thông qua id
		Customer customer = customerRepository.findById(idus).orElse(null);

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
		Customer customer = customerRepository.findById(idus).orElse(null);
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

		List<CustomerOrderItem> customerOrderItems = customerOderItemRepository.findByCustomerOrderId(id);
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

}
