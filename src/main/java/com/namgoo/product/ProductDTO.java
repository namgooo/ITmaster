package com.namgoo.product;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private Integer id;
	private String category;
	private String maker;
	private String product;
	private LocalDateTime createDate;

}
