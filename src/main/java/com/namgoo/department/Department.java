package com.namgoo.department;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.employee.Employee;
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
public class Department {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false, unique = true)
	private String department;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
	private List<ProductInfo> productInfoList;
	@OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
	private List<ProductPcInfo> productPcInfoList;
	@OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
	private List<Employee> employeeList;

}
