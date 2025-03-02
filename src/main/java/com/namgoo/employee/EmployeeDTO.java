package com.namgoo.employee;

import java.time.LocalDateTime;

import com.namgoo.department.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
	
	private Integer id;
	private String employee;
	private LocalDateTime createDate;
	
	private String department;

}
