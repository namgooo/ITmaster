package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.namgoo.category.Category;
import com.namgoo.department.Department;
import com.namgoo.desktop_type.DesktopType;
import com.namgoo.employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private Integer cpuScore;
	private Integer gpuScore;
	private LocalDateTime createDate;
	
	// ManyToOne
	@ManyToOne
	private Department department;
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private DesktopType desktopType;

}
