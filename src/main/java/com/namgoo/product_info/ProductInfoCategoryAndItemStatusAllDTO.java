package com.namgoo.product_info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfoCategoryAndItemStatusAllDTO {
	
	private String category;
	private Long normal;
	private Long defective;
	private Long broken;
	
	public ProductInfoCategoryAndItemStatusAllDTO(String 카테고리, Long 정상, Long 결함, Long 고장) {
		this.category = 카테고리;
		this.normal = 정상;
		this.defective = 결함;
		this.broken = 고장;
	}
}
