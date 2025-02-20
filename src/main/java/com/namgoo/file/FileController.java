package com.namgoo.file;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	private static final String UPLOAD_DIR = "C:/files/";
	
	// 파일 목록 조회
	@GetMapping("/upload")
	public String findFileList(Model model) {
		List<File> fileList = this.fileService.findFileList();
		model.addAttribute("fileList", fileList);
		return "main/file";
	}
	
	// 파일 업로드
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		this.fileService.uploadFile(file);
		return "redirect:/file/upload";
	}
	
	// 파일 다운로드
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {	
		try {
			// 파일 이름으로 DB에서 파일명을 조회
			String file = this.fileService.downloadFile(fileName);
			if(file == null || file.trim().isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			Path filePath = Paths.get(UPLOAD_DIR).resolve(file).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			// 파일이 존재하고 읽을 수 있는지 확인
			if(!resource.exists() || !resource.isReadable()) {
				return ResponseEntity.notFound().build();
			}
			
			String contentType = Files.probeContentType(filePath);
			if(contentType == null) {
				contentType = "";
			}
			
			
			
		} catch(Exception e) {
			
		}	
			
		return null;
	}
	
	// 2025-02-20 파일 다운로드
	
	
}
