package com.namgoo.maker;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakerDTO {
	
	private Integer id;
	private String maker;
	private LocalDateTime createDate;

}
