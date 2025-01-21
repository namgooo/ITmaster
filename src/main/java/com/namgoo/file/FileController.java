package com.namgoo.file;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// 파일 업로드
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
		this.fileService.saveFile(file);
		
		for(MultipartFile multipartFile : files) {
			this.fileService.saveFile(multipartFile);
		}
		
		return "redirect:/root";
	}
	
	// 파일 다운로드
	@GetMapping("/view")
	public String view(Model model) {
		List<File> fileList = this.fileService.findFileList();
		model.addAttribute("fileList", fileList);
		return "redirect:/root";
	}
	
	// 이미지 출력
	@GetMapping("/images/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") Integer id, Model model) throws IOException{
		Optional<File> file = this.fileService.file(id);	
		return new UrlResource("file : " + file.get().getSavePath());
	}
	
	
	
}
