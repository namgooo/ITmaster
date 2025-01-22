package com.namgoo.desktop;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
	private Integer cpuScore;
	private Integer gpuScore;
	private LocalDateTime createDate;
	
	private String department;
	private String employee;
	private String desktopType;
	
	private Long countDesktopOffice;
	private Long countDesktopCad;
	private Long countDesktopDesign;
	private Long countDesktopOther;
	private Long countDesktopLack;
	
    public DesktopDTO(Long 사무용, Long 설계용, Long 디자인용, Long 기타, Long 미달) {
        this.countDesktopOffice = 사무용;
        this.countDesktopCad = 설계용;
        this.countDesktopDesign = 디자인용;
        this.countDesktopOther = 기타;
        this.countDesktopLack = 미달;
    }
	
}
