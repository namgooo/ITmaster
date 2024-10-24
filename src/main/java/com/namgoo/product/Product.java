package com.namgoo.product;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.category.Category;
import com.namgoo.maker.Maker;
import com.namgoo.product_info.ProductInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false, unique = true)
	private String product;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductInfo> productInfoList;
	
	// ManyToOne
	@ManyToOne
	private Category category;
	@ManyToOne
	private Maker maker;


}
