package com.namgoo.category;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.desktop.Desktop;
import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_pc_info.ProductPcInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false, unique = true)
	private String category;
	private LocalDateTime createDate;
	
	// OneToMany
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<ProductInfo> productInfoList;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<ProductPcInfo> productPcInfoList;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Product> ProductList;
	
}
