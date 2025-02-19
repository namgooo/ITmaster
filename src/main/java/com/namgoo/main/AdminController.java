package com.namgoo.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namgoo.desktop.Desktop;
import com.namgoo.desktop.DesktopDepartmentAndTypeAllDTO;
import com.namgoo.desktop.DesktopService;
import com.namgoo.desktop.DesktopTypeAllDTO;
import com.namgoo.product_info.ProductInfoCategoryAllDTO;
import com.namgoo.product_info.ProductInfoCategoryAndItemStatusAllDTO;
import com.namgoo.product_info.ProductInfoService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private DesktopService desktopService;
	
	@GetMapping("/chart")
	public String admin(Model model) {
		
		// 데스크탑 총합
		List<Desktop> desktop = this.desktopService.findDesktopList();
		Integer desktopTotal = desktop.size();
		model.addAttribute("destkopTotal", desktopTotal);
		
		// 부서별 데스크탑 타입 총합 조회
		List<DesktopDepartmentAndTypeAllDTO> countDesktopDepartmentAndTypeList = this.desktopService.countDesktopDepartmentAndTypeList();
		model.addAttribute("countDesktopDepartmentAndTypeList", countDesktopDepartmentAndTypeList);
		
		// 타입별 데스크탑 총합 조회
		List<DesktopTypeAllDTO> countDesktopTypeAllList = this.desktopService.countDesktopTypeAllList();
		model.addAttribute("countDesktopTypeAllList", countDesktopTypeAllList);
		
		// 카테고리 별 제품 정보 총합
		List<ProductInfoCategoryAllDTO> countCategoryProductInfoList = this.productInfoService.countCategoryProductInfoList();
		model.addAttribute("countCategoryProductInfoList", countCategoryProductInfoList);
				
		// 카테고리, 상태 별 전산 물품 총합 조회
		List<ProductInfoCategoryAndItemStatusAllDTO> countCategoryAndItemStatusProductInfoList = this.productInfoService.countCategoryAndItemStatusProductInfoList();
		model.addAttribute("countCategoryAndItemStatusProductInfoList", countCategoryAndItemStatusProductInfoList);
		
		// 2025-02-19 전산관리 파일 업로드 및 파일 다운로드
		
		return "main/admin";
		
	}
	

}
