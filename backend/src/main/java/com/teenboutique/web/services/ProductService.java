package com.teenboutique.web.services;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductImage;
import com.teenboutique.web.helpers.Helper;
import com.teenboutique.web.repositories.ProductImageRepository;
import com.teenboutique.web.repositories.ProductRepository;



@Service
public class ProductService {
	@Autowired
	private ProductRepository proRepo;
	@Autowired
	private ProductImageRepository proImgRepo;
	
	public List<Product> getAll(){
		return proRepo.findAll();
	}
	
	public Page<Product> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);
		return proRepo.findAllProductStillSell(pageable);
	}
	
	public Product findById(Long id) {
		return proRepo.findById(id).get();
	}
	
//	If avatar or not_avatars is null this function will fail, need constrain on those data
	public Product createProduct(Product p, MultipartFile avatar, MultipartFile[] not_avatars) {
		String path = System.getProperty("user.dir") + "/src/main/resources/static/uploads/images/";
		String fileName = Helper.generateRandomString(20)+avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.'));
		Path uploadPath = Paths.get(path+fileName);
		Path filePath = uploadPath.resolve(uploadPath);
		try {
			Files.copy(avatar.getInputStream(), filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProductImage pi1 = new ProductImage();
		pi1.setAvatar(true);
		pi1.setUrl(fileName);
		pi1.setProduct(p);
		List<ProductImage> pis = new ArrayList<ProductImage>();
		pis.add(pi1);
		
		for(MultipartFile not_avatar : not_avatars) {
			fileName = Helper.generateRandomString(20)+not_avatar.getOriginalFilename().substring(not_avatar.getOriginalFilename().lastIndexOf('.'));
			uploadPath = Paths.get(path+fileName);
			filePath = uploadPath.resolve(uploadPath);
			try {
				Files.copy(not_avatar.getInputStream(), filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProductImage pii = new ProductImage();
			pii.setAvatar(false);
			pii.setUrl(fileName);;
			pii.setProduct(p);
			pis.add(pii);
		}
		p.setImages(pis);
		return proRepo.save(p);
	}
	
	public Product editOne(Long id, Product p, MultipartFile file) {
		Product existed = proRepo.findById(id).get();
		existed.setName(p.getName());
		existed.setCategory(p.getCategory());
		existed.setPrice(p.getPrice());
		existed.setDescription(p.getDescription());
		if(file.getSize()!=0) {
			String path = System.getProperty("user.dir") + "/src/main/resources/static/uploads/images/";
			String fileName = Helper.generateRandomString(20)+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			Path uploadPath = Paths.get(path+fileName);
			Path deletePath = Paths.get(path+existed.getImages().get(0).getUrl());
			existed.getImages().get(0).setUrl(fileName);			
			Path filePath = uploadPath.resolve(uploadPath);
			Path fileDeletePath = deletePath.resolve(deletePath);
			System.out.println(fileDeletePath);
			System.out.println(filePath);
	        if(Helper.checkImg(deletePath.toString())) {
	        	try {
		        	Files.delete(fileDeletePath);
				}
		        catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		proRepo.save(existed);
		return null;
	}
	
	public Product stopSell(Product p) {
		p.setStop_sale(true);
		List<ProductImage> images = p.getImages();
		for(ProductImage img:images) {
			if(!Helper.checkImg(img.getUrl())) {
				String path = System.getProperty("user.dir") + "/src/main/resources/static/uploads/images/";
				Path deletePath = Paths.get(path+img.getUrl());
				Path fileDeletePath = deletePath.resolve(deletePath);
				try {
		        	Files.delete(fileDeletePath);
				}
		        catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return proRepo.save(p);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
