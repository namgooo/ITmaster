package com.namgoo.department;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
	
	private Integer id;
	private String department;
	private LocalDateTime createDate;

}
