package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.category.Category;
import com.namgoo.employee.Employee;
import com.namgoo.product_pc_info.ProductPcInfo;

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
public class Desktop {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false, unique = true)
	private String desktop;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToMany(mappedBy = "desktop", cascade = CascadeType.REMOVE)
	private List<ProductPcInfo> productPcInfoList;
	
	// ManyToOne
	@ManyToOne
	private Category cateogry;
	@ManyToOne
	private Employee employee;

}
