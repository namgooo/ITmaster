package com.namgoo.desktop;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesktopDTO {
	
	private Integer id;
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
	
	private String department;
	private String employee;
	
}
