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

import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.entities.Role;
import com.teenboutique.web.services.EmployeeService;
import com.teenboutique.web.services.RoleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EmployeeService empSer;

	@Autowired
	private RoleService roleSer;

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
		if(pass.charAt(0)=='s'&&pass.charAt(1)=='b') {
			if(empSer.login(Long.parseLong(id), pass)!=null)return "admin/main";
		}
		Employee e = empSer.getEmpById(Long.parseLong(id));
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if(bCryptPasswordEncoder.matches(pass, e.getPassword()))return "admin/main";
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

		model.addAttribute("currentPage", emps);
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
		Long lastEmp = empSer.findAll().get(empSer.findAll().size()-1).getId()+1;
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
	public String showCreateEmployeeForm(@ModelAttribute("id")Long id) {
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

}
