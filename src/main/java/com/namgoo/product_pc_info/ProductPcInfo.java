package com.namgoo.product_pc_info;

import java.time.LocalDateTime;

import com.namgoo.category.Category;
import com.namgoo.department.Department;
import com.namgoo.desktop.Desktop;
import com.namgoo.disposal.Disposal;
import com.namgoo.employee.Employee;
import com.namgoo.maker.Maker;
import com.namgoo.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductPcInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String simpleName;
	private String useStatus;
	private String itemStatus;
	private String location;
	private String price;
	private String buyYear;
	private String productComment;
	@Column(nullable = false, unique = true)
	private String uniqueCode;
	private LocalDateTime createDate;
	
	// ManyToOne
	@ManyToOne
	private Category category;
	@ManyToOne
	private Department department;
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private Maker maker;
	@ManyToOne
	private Product product;
	
	// OneToOne
	@OneToOne(mappedBy = "productPcInfo")
	private Disposal disposal;
	
}
