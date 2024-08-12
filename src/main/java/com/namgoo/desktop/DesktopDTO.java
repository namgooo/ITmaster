package com.namgoo.desktop;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesktopDTO {
	
	private Integer id;
	private String desktop;
	private String employee;
	private LocalDateTime createDate;

}
