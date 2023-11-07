package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Size;
import com.teenboutique.web.repositories.SizeRepository;

@Service
public class SizeService {
	@Autowired
	private SizeRepository sizeRepo;
	
	public Size add(Size s) {
		return sizeRepo.save(s);
	}
	
	public List<Size> findAll() {
		return sizeRepo.findAll();
	}
}
