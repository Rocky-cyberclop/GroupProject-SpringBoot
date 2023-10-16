package com.teenboutique.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.services.CustomerSevices;

@Controller
@RequestMapping("/account")
public class AccountController {

	CustomerRepository customerRepository;
	CustomerSevices customerSevices;

	public AccountController(CustomerRepository customerRepository, CustomerSevices customerSevices) {
		super();
		this.customerRepository = customerRepository;
		this.customerSevices = customerSevices;
	}

	@GetMapping()
	public String shownDashboard(Model model) {
		Customer customer = customerRepository.findById(490L).orElse(null);

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
		Customer customer = customerRepository.findById(490L).orElse(null);

		if (customer != null) {
			List<CustomerOrder> customerOrders = customer.getOrders();
			int sl[] = new int[customerOrders.size()];
			for (int i = 0; i < customerOrders.size(); i++) {
				sl[i] = customerOrders.get(i).getOrder_items().size();
				model.addAttribute("sl", sl);
				model.addAttribute("customerOrders", customerOrders);
			}
		}

		return "account/order";
	}

	@GetMapping("/profileDetails")
	public String shownProfileDetails(Model model) {
		Customer customer = customerRepository.findById(490L).orElse(null);
		model.addAttribute("user", customer);
		return "account/profiledetails";
	}

}
