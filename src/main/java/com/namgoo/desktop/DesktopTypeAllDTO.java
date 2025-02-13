package com.namgoo.desktop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesktopTypeAllDTO {
	
	private String type;
	private Long total;
	
	public DesktopTypeAllDTO(String type, Long total) {
		this.type = type;
		this.total = total;
	}

}
