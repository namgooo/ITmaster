package com.namgoo.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExcelController {
	
	@Autowired
	private ExcelService excelService; 
	
	@PostMapping("/excel")
	public String excelData(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		// System.out.println("진입확인");
        try {
            excelService.saveExcelData(file);
            redirectAttributes.addFlashAttribute("message", "File uploaded and data saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An error occurred: " + e.getMessage());
        }
        		
		return "redirect:/root";
	}

}
