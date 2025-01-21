package com.namgoo.desktop_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/desktop-type")
public class DesktopTypeController {
	
	@Autowired
	private DesktopTypeService desktopTypeService;
	
	// 데스크탑타입 목록 조회
	@GetMapping("/list")
	public String findDesktopTypeList() {
		List<DesktopType> desktopTypeList = this.desktopTypeService.findDesktopTypeList();
		return "desktop/desktop_type";
	}
	
	// 데스크탑타입 등록
	@PostMapping("/create")
	public String createDesktopType(DesktopTypeDTO dto) {
		this.desktopTypeService.createDesktopType(dto);
		return "redirect:/desktop-type/list";
	}
	
}
