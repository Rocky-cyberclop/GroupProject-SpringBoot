package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Role;
import com.teenboutique.web.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired private RoleRepository roleRepo;
	
	public List<Role> findAllRole(){
		return roleRepo.findAll();
	}
	
	public Role save(Role role) {
		return roleRepo.save(role);
	}
}
