package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.category.Category;
import com.namgoo.department.Department;
import com.namgoo.employee.Employee;

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
	private String mainboard;
	private String cpu;
	private String gpu;
	private String ssd;
	private String power;
	private String memory1;
	private String memory2;
	private String memory3;
	private String memory4;
	private LocalDateTime createDate;
	
	// ManyToOne
	@ManyToOne
	private Department department;
	@ManyToOne
	private Employee employee;
	
	// 2024-10-25 퇴근

}
