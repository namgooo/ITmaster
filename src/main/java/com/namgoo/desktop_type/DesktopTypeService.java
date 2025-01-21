package com.namgoo.desktop_type;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesktopTypeService {
	
	@Autowired
	private DesktopTypeRepository desktopTypeRepository;
	
	// 데스크탑타입 목록 조회
	public List<DesktopType> findDesktopTypeList() {
		List<DesktopType> desktopTypeList = this.desktopTypeRepository.findAll();
		return desktopTypeList;
	}
	
	// 데스크탑타입 등록
	public void createDesktopType(DesktopTypeDTO dto) {
		DesktopType desktopType = new DesktopType();
		desktopType.setType(dto.getType());
		desktopType.setCreateDate(LocalDateTime.now());
		this.desktopTypeRepository.save(desktopType);
	}
	
	
	
}
