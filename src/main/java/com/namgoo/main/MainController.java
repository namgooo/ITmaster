package com.namgoo.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.namgoo.desktop.Desktop;
import com.namgoo.desktop.DesktopDTO;
import com.namgoo.desktop.DesktopService;
import com.namgoo.desktop.DesktopTypeAllDTO;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoCategoryAllDTO;
import com.namgoo.product_info.ProductInfoCategoryAndItemStatusAllDTO;
import com.namgoo.product_info.ProductInfoService;

@Controller
public class MainController {
	
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private DesktopService desktopService;
	
	@GetMapping("/root")
	public String main(Model model) {		
		return "main/main";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		// 제품 정보 총합 조회
		List<ProductInfo> productInfoList = this.productInfoService.findProductInfoList();
		Integer countProductInfo = productInfoList.size();
		model.addAttribute("countProductInfo", countProductInfo);
		// 제품 정보 '키보드' 총합 조회
		Integer countProductInfoKeyboard = this.productInfoService.countProductInfoKeyboard();
		model.addAttribute("countProductInfoKeyboard", countProductInfoKeyboard);
		// 제품 정보 '마우스' 총합 조회
		Integer countProductInfoMouse = this.productInfoService.countProductInfoMouse();
		model.addAttribute("countProductInfoMouse", countProductInfoMouse);
		// 제품 정보 '모니터' 총합 조회
		Integer countProductInfoMonitor = this.productInfoService.countProductInfoMonitor();		
		model.addAttribute("countProductInfoMonitor", countProductInfoMonitor);
		// 데스크탑 총합 조회
		List<Desktop> desktopList = this.desktopService.findDesktopList();
		Integer countDesktop = desktopList.size();
		model.addAttribute("countDesktop", countDesktop);
		// 제품 정보 총합 + 데스크탑 총합 조회
		Integer sum = countProductInfo + countDesktop;
		model.addAttribute("sum", sum);
		
		// 제품 정보(키보드) 상태 - '정상' 총합 조회
		Integer countProductInfoKeyboardNormal = this.productInfoService.countProductInfoKeyboardNormal();
		model.addAttribute("countProductInfoKeyboardNormal", countProductInfoKeyboardNormal);
		// 제품 정보(키보드) 상태 - '결함' 총합 조회
		Integer countProductInfoKeyboardDefective = this.productInfoService.countProductInfoKeyboardDefective();
		model.addAttribute("countProductInfoKeyboardDefective", countProductInfoKeyboardDefective);
		// 제품 정보(키보드) 상태 - '고장' 총합 조회
		Integer countProductInfoKeyboardFaulty = this.productInfoService.countProductInfoKeyboardFaulty();
		model.addAttribute("countProductInfoKeyboardFaulty", countProductInfoKeyboardFaulty);
		
		// 제품 정보(마우스) 상태 - '정상' 총합 조회
		Integer countProductInfoMouseNormal = this.productInfoService.countProductInfoMouseNormal();
		model.addAttribute("countProductInfoMouseNormal", countProductInfoMouseNormal);
		// 제품 정보(마우스) 상태 - '결함' 총합 조회
		Integer countProductInfoMouseDefective = this.productInfoService.countProductInfoMouseDefective();
		model.addAttribute("countProductInfoMouseDefective", countProductInfoMouseDefective);
		// 제품 정보(마우스) 상태 - '고장' 총합 조회
		Integer countProductInfoMouseFaulty = this.productInfoService.countProductInfoMouseFaulty();
		model.addAttribute("countProductInfoMouseFaulty", countProductInfoMouseFaulty);
		
		// 제품 정보(모니터) 상태 - '정상' 총합 조회
		Integer countProductInfoMonitorNormal = this.productInfoService.countProductInfoMonitorNormal();
		model.addAttribute("countProductInfoMonitorNormal", countProductInfoMonitorNormal);
		// 제품 정보(모니터) 상태 - '결함' 총합 조회
		Integer countProductInfoMonitorDefective = this.productInfoService.countProductInfoMonitorDefective();
		model.addAttribute("countProductInfoMonitorDefective", countProductInfoMonitorDefective);
		// 제품 정보(모니터) 상태 - '고장' 총합 조회
		Integer countProductInfoMonitorFaulty = this.productInfoService.countProductInfoMonitorFaulty();
		model.addAttribute("countProductInfoMonitorFaulty", countProductInfoMonitorFaulty);
		
		
		
		// 데스크탑 '사무용' 총합 조회
		Integer countDesktopOffice = this.desktopService.countDesktopOffice();
		model.addAttribute("countDesktopOffice", countDesktopOffice);
		// 데스크탑 '설계용' 총합 조회
		Integer countDesktopCad = this.desktopService.countDesktopCad();
		model.addAttribute("countDesktopCad", countDesktopCad);
		// 데스크탑 '디자인용' 총합 조회
		Integer countDesktopDesign = this.desktopService.countDesktopDesign();
		model.addAttribute("countDesktopDesign", countDesktopDesign);
		// 데스크탑 '기타' 총합 조회
		Integer countDesktopOther = this.desktopService.countDesktopOther();
		model.addAttribute("countDesktopOther", countDesktopOther);
		// 데스크탑 '미달' 총합 조회
		Integer countDesktopLack = this.desktopService.countDesktopLack();
		model.addAttribute("countDesktopLack", countDesktopLack);

		// ---------------------------------------------------------------------------------------------------------
		
		// 부서별 데스크탑 타입 총합 조회
		List<DesktopTypeAllDTO> countDesktopTypeList = this.desktopService.countDesktopTypeList();
		model.addAttribute("countDesktopTypeList", countDesktopTypeList);
//		System.out.println("부서 : " + countDesktopTypeList.get(0).getDepartment());
//		System.out.println("사무용 : " + countDesktopTypeList.get(0).getCountDesktopOffice());
//		System.out.println("설계용 : " + countDesktopTypeList.get(0).getCountDesktopCad());
//		System.out.println("디자인용 : " + countDesktopTypeList.get(0).getCountDesktopDesign());
//		System.out.println("기타 : " + countDesktopTypeList.get(0).getCountDesktopOther());
//		System.out.println("미달 : " + countDesktopTypeList.get(0).getCountDesktopLack());
		
		// 카테고리 별 제품 정보 총합
		List<ProductInfoCategoryAllDTO> countCategoryProductInfoList = this.productInfoService.countCategoryProductInfoList();
		model.addAttribute("countCategoryProductInfoList", countCategoryProductInfoList);
//		System.out.println("모니터 : " + countCategoryProductInfoList.get(0).getTotal());
//		System.out.println("키보드 : " + countCategoryProductInfoList.get(1).getTotal());
//		System.out.println("마우스 : " + countCategoryProductInfoList.get(2).getTotal());
//		System.out.println(countCategoryProductInfoList.get(0).getCategory());
		
		// 데스크탑 총합
		List<Desktop> desktop = this.desktopService.findDesktopList();
		Integer desktopTotal = desktop.size();
		model.addAttribute("destkopTotal", desktopTotal);
		
		// 카테고리, 상태 별 전산 물품 총합 조회
		List<ProductInfoCategoryAndItemStatusAllDTO> countCategoryAndItemStatusProductInfoList = this.productInfoService.countCategoryAndItemStatusProductInfoList();
		model.addAttribute("countCategoryAndItemStatusProductInfoList", countCategoryAndItemStatusProductInfoList);
		System.out.println("카테고리 : " + countCategoryAndItemStatusProductInfoList.get(0).getCategory());
		System.out.println("정상 : " + countCategoryAndItemStatusProductInfoList.get(0).getNormal());
		System.out.println("결함 : " + countCategoryAndItemStatusProductInfoList.get(0).getDefective());
		System.out.println("고장 : " + countCategoryAndItemStatusProductInfoList.get(0).getBroken());
		
		return "main/admin";
		
	}

	

}
