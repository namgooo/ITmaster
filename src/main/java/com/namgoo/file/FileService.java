package com.namgoo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	private static final String UPLOAD_DIR = "C:/files/";
	
	// 파일 목록 조회
	public List<File> findFileList() {
		List<File> fileList = this.fileRepository.findAll();
		return fileList;
	}
	
	// 파일 업로드
	public String uploadFile(MultipartFile multipartFile) {
		// 파일이 선택되었는지 확인(예외처리)
		if(multipartFile.isEmpty()) {
			return "파일을 선택하세요!";
		}
		try {
			// 저장할 파일 경로 설정
			String fileName = multipartFile.getOriginalFilename();
			String filePath = UPLOAD_DIR + fileName;
			
			// 파일 저장
			Path path = Paths.get(filePath);
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); // 사용자가 같은 이름의 파일을 업로드하면 덮어쓰기
			
			// 파일 정보를 DB에 저장
			File file = new File();
			file.setFileName(fileName);
			file.setFilePath(filePath);
			file.setFileType(fileName.substring(fileName.lastIndexOf("."))); // 파일 확장자
			file.setFileSize(multipartFile.getSize());
			file.setCreateDate(LocalDateTime.now());
			
			this.fileRepository.save(file);
			
			return "파일 업로드 성공 : " + fileName;
			
		} catch (IOException e) {
			e.printStackTrace();
			return "파일 업로드 실패!";
		}
		
	}
	
	// 파일 다운로드
	public Resource downloadFile(Long fileId) throws Exception{
		
		// DB에서 id를 이용해서 파일명 조회
		String fileName = this.fileRepository.findById(fileId).orElseThrow(() -> new Exception("파일을 찾을 수 없습니다.")).getFileName();
		
		// 업로드 경로 + 파일명으로 경로 설정
		Path filePath = Paths.get(UPLOAD_DIR + fileName);
		
		// 파일이 존재하는지 확인 후 Resource 객체 반환
		Resource resource = new UrlResource(filePath.toUri());
		if(!resource.exists() || !resource.isReadable()) {
			throw new Exception("파일을 찾을 수 없거나 읽을 수 없습니다.");
		}
		return resource;	
	}
	

	

}
