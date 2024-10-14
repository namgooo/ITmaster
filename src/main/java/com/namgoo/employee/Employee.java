package com.namgoo.employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.namgoo.department.Department;
import com.namgoo.desktop.Desktop;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_pc_info.ProductPcInfo;

import jakarta.persistence.CascadeType;
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
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String employee;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToMany(mappedBy = "desktop", cascade = CascadeType.REMOVE)
	private List<Desktop> desktopList;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<ProductInfo> productInfoList;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<ProductPcInfo> productPcInfoList;
	
	// ManyToOne
	@ManyToOne
	private Department department;

}
