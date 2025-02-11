package com.namgoo.product_info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfoCategoryAllDTO {
	
	private String category;
	private Long total;
	
	public ProductInfoCategoryAllDTO(String category, Long total) {
		this.category = category;
		this.total = total;
	}

}
