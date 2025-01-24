package com.namgoo.desktop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesktopTypeAllDTO {
	
	private String department;
	private Long countDesktopOffice;
	private Long countDesktopCad;
	private Long countDesktopDesign;
	private Long countDesktopOther;
	private Long countDesktopLack;
	
	public DesktopTypeAllDTO(String ICT사업부, Long 사무용, Long 설계용, Long 디자인용, Long 기타, Long 미달) {
		this.department = ICT사업부;
		this.countDesktopOffice = 사무용;
		this.countDesktopCad = 설계용;
		this.countDesktopDesign = 디자인용;
		this.countDesktopOther = 기타;
		this.countDesktopLack = 미달;
	}
	
}
