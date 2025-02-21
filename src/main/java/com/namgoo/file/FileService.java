package com.namgoo.file;

import java.io.FileNotFoundException;
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
	
	// 파일 저장 경로
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
	public Resource downloadFile(String fileName) throws IOException {
		// Path 객체로 변환
		// resolve : 파일 이름을 경로에 추가(예시 - /c/files/fileName)
		// normalize : ../(상위 폴더)와 ./(현재 폴더) 같은 불필요한 경로를 제거해서 간결하게 만듬
		Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
		// URL 형식으로 변환, 이를 기반으로 파일을 로드하거나 다운로드가 가능함
		Resource resource = new UrlResource(filePath.toUri());

		return resource;
	}

}
