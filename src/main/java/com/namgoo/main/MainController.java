package com.namgoo.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/root")
	public String main() {
		return "main/main";
	}
	
	@GetMapping("/test")
	public String test() {
		return "product_info/product_info_test";
	}

}
