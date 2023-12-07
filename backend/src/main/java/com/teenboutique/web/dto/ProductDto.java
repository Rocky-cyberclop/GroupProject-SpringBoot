package com.teenboutique.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.helpers.Helper;

public class ProductDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private long price;
	
	private String description;

	private CategoryDto category;
	
	private String image;
	
	private List<ProductDetailDto> detailDtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<ProductDetailDto> getDetailDtos() {
		return detailDtos;
	}

	public void setDetailDtos(List<ProductDetailDto> detailDtos) {
		this.detailDtos = detailDtos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductDto() {
		super();
	}
	
	public ProductDto(Product p) {
		this.id = p.getId();
		this.description = p.getDescription();
		this.image = p.getImages().get(0).getUrl();
		this.name = p.getName();
		this.price = p.getPrice();
		this.image = Helper.checkImg(p.getImages().get(0).getUrl()) ? 
				p.getImages().get(0).getUrl():
					"http://localhost:8080/uploads/images/"+p.getImages().get(0).getUrl();
		this.category = new CategoryDto(p.getCategory());
		this.detailDtos = new ArrayList<ProductDetailDto>();
		for(ProductDetail pd:p.getProduct_details()) {
			ProductDetailDto tmp = new ProductDetailDto(pd);
			this.detailDtos.add(tmp);
		}
	}

	public void convertProductToDto(Product p) {
		this.id = p.getId();
		this.description = p.getDescription();
		this.image = Helper.checkImg(p.getImages().get(0).getUrl()) ? 
				p.getImages().get(0).getUrl():
					"http://localhost:8080/uploads/images/"+p.getImages().get(0).getUrl();
		this.name = p.getName();
		this.price = p.getPrice();
		this.category = new CategoryDto(p.getCategory());
		this.detailDtos = new ArrayList<ProductDetailDto>();
		for(ProductDetail pd:p.getProduct_details()) {
			ProductDetailDto tmp = new ProductDetailDto(pd);
			this.detailDtos.add(tmp);
		}
	}

}
