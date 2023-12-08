package com.teenboutique.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.teenboutique.web.entities.Product;
import com.teenboutique.web.entities.ProductDetail;
import com.teenboutique.web.entities.ProductImage;
import com.teenboutique.web.helpers.Helper;

public class ThanhThanhProDetailDto implements Serializable {
    private Long id;
    private Long size;
    private int quantity;
    private String name;
    private long price;
    private String description;
    private CategoryDto category;
	private String image;
    private List<String> images;  // Thay đổi ở đây
    private List<ProductDetailDto> detailDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<ProductDetailDto> getDetailDtos() {
        return detailDtos;
    }

    public void setDetailDtos(List<ProductDetailDto> detailDtos) {
        this.detailDtos = detailDtos;
    }

    public ThanhThanhProDetailDto() {
        super();
    }

    public ThanhThanhProDetailDto(Product p) {
        this.id = p.getId();
        this.description = p.getDescription();
        this.name = p.getName();
        this.price = p.getPrice();
        this.image = Helper.checkImg(p.getImages().get(0).getUrl()) ? 
				p.getImages().get(0).getUrl():
					"http://localhost:8080/uploads/images/"+p.getImages().get(0).getUrl();
        this.images = new ArrayList<>();
        for (ProductImage image : p.getImages()) {
            String imageUrl = Helper.checkImg(image.getUrl()) ? image.getUrl() :
                    "http://localhost:8080/uploads/images/" + image.getUrl();
            this.images.add(imageUrl);
        }
        this.category = new CategoryDto(p.getCategory());
        this.detailDtos = new ArrayList<>();
        for (ProductDetail pd : p.getProduct_details()) {
            ProductDetailDto tmp = new ProductDetailDto(pd);
            this.detailDtos.add(tmp);
        }
    }
}
