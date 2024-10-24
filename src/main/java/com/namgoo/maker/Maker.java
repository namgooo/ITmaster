package com.namgoo.maker;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;

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
public class Maker {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false, unique = true)
	private String maker;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToMany(mappedBy = "maker", cascade = CascadeType.REMOVE)
	private List<Product> productList;
	@OneToMany(mappedBy = "maker", cascade = CascadeType.REMOVE)
	private List<ProductInfo> productInfoList;
	
}
