package com.namgoo.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.namgoo.desktop.Desktop;
import com.namgoo.desktop.DesktopService;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoService;

@Controller
public class MainController {
	
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private DesktopService desktopService;
	
	@GetMapping("/root")
	public String main(Model model) {
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
		return "main/admin";
	}
	

	

}
