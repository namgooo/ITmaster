package com.namgoo.product_pc_info;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPcInfoDTO {
	
	private Integer id;
	private String simpleName;
	private String useStatus;
	private String itemStatus;
	private String location;
	private String price;
	private String buyYear;
	private String productComment;
	private String uniqueCode;
	private LocalDateTime creataDate;
	
	private String category;
	private String maker;
	private String product;
	private String department;
	private String employee;

}
