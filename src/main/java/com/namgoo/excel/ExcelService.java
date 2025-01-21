package com.namgoo.excel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;
import com.namgoo.desktop.Desktop;
import com.namgoo.desktop.DesktopRepository;
import com.namgoo.desktop_type.DesktopType;
import com.namgoo.desktop_type.DesktopTypeRepository;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeRepository;

import groovy.util.logging.Slf4j;

@Service
public class ExcelService {
	
	@Autowired
	private DesktopRepository desktopRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DesktopTypeRepository desktopTypeRepository;
	
	// 등록
	public void saveExcelData(MultipartFile excelFile) throws IOException {
		
		// 엑셀 파일을 읽음
		Workbook workbook = WorkbookFactory.create(excelFile.getInputStream());
		// 첫번째 시트를 가져옴
		Sheet sheet = workbook.getSheetAt(0);
		// 첫번째 시트의 마지막 열의 번호까지 반복함
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			// 엑셀 열 생성
			Row row = sheet.getRow(i);
			// 데스크탑 객체 생성
			Desktop desktop = new Desktop();
			
			// 부서
			String departmentName = row.getCell(0).getStringCellValue();
			Department department = this.departmentRepository.findByDepartment(departmentName).orElse(null);
			desktop.setDepartment(department);
			// 부서원
			String employeeName = row.getCell(1).getStringCellValue();
			Employee employee = this.employeeRepository.findByEmployee(employeeName).orElse(null);
			desktop.setEmployee(employee);
			// 데스크탑명
			desktop.setDesktop(row.getCell(2).getStringCellValue());
			// 메인보드
			desktop.setMainboard(row.getCell(3).getStringCellValue());
			// CPU
			desktop.setCpu(row.getCell(4).getStringCellValue());
			// GPU
			desktop.setGpu(row.getCell(5).getStringCellValue());
			// SSD
			desktop.setSsd(row.getCell(6).getStringCellValue());
			// 파워
			desktop.setPower(row.getCell(7).getStringCellValue());
			// 메모리1
			desktop.setMemory1(row.getCell(8).getStringCellValue());
			// 메모리2
			desktop.setMemory2(row.getCell(9).getStringCellValue());
			// 메모리3
			desktop.setMemory3(row.getCell(10).getStringCellValue());
			// 메모리4
			desktop.setMemory4(row.getCell(11).getStringCellValue());
			// 등록일시
			desktop.setCreateDate(LocalDateTime.now());
			// 번호(null 처리를 해줘야 저장이 됨)
			desktop.setId(null);
			
			try {
			    this.desktopRepository.save(desktop);
			    this.desktopRepository.flush(); // 강제로 데이터베이스에 저장
			    // 
			    System.out.println("저장성공 확인 : " + (i + 1));
			} catch (Exception e) {
			    System.err.println("저장실패 확인: " + (i + 1));
			    e.printStackTrace();
			}
			System.out.println("ID 확인 : " + desktop.getId());	
		}
		workbook.close(); // 종료
	}
	
	// 업데이트
	public void updateExcelData(MultipartFile excelFile) throws IOException {
		
		// 엑셀 파일을 읽음
		Workbook workbook = WorkbookFactory.create(excelFile.getInputStream());
		// 첫번째 시트를 가져옴
		Sheet sheet = workbook.getSheetAt(11);
		// 첫번째 시트의 마지막 열의 번호까지 반복함
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			// 엑셀 열 생성
			Row row = sheet.getRow(i);
			
			// 부서원
			String employeeName = row.getCell(0).getStringCellValue(); // 엑셀 데이터 읽음
			Employee employee = this.employeeRepository.findByEmployee(employeeName).orElse(null); // 읽은 엑셀 데이터로 부서원 찾음
			if(employee != null ) {
				Desktop employeeByDesktop = this.desktopRepository.findByEmployee(employee).orElse(null); // 찾은 부서원에 해당하는 데스크탑 찾음
				if(employeeByDesktop != null) {
					// 부서
					String departmentName = row.getCell(1).getStringCellValue();
					Department department = this.departmentRepository.findByDepartment(departmentName).orElse(null);
					employeeByDesktop.setDepartment(department);
					// 데스크탑명
					employeeByDesktop.setDesktop(row.getCell(2).getStringCellValue());
					// 메인보드
					employeeByDesktop.setMainboard(row.getCell(3).getStringCellValue());
					// CPU
					employeeByDesktop.setCpu(row.getCell(4).getStringCellValue());
					// GPU
					employeeByDesktop.setGpu(row.getCell(5).getStringCellValue());
					// SSD
					employeeByDesktop.setSsd(row.getCell(6).getStringCellValue());
					// 파워
					employeeByDesktop.setPower(row.getCell(7).getStringCellValue());
					// 메모리1
					employeeByDesktop.setMemory1(row.getCell(8).getStringCellValue());
					// 메모리2
					employeeByDesktop.setMemory2(row.getCell(9).getStringCellValue());
					// 메모리3
					employeeByDesktop.setMemory3(row.getCell(10).getStringCellValue());
					// 메모리4
					employeeByDesktop.setMemory4(row.getCell(11).getStringCellValue());
					// 구분
					String desktopTypeName = row.getCell(12).getStringCellValue();
					DesktopType desktopType = this.desktopTypeRepository.findByType(desktopTypeName).orElse(null);
					employeeByDesktop.setDesktopType(desktopType);
					// CPU점수
					employeeByDesktop.setCpuScore((int) row.getCell(13).getNumericCellValue());
					// GPU점수
					employeeByDesktop.setGpuScore((int) row.getCell(14).getNumericCellValue());
					// 등록일시
					employeeByDesktop.setCreateDate(LocalDateTime.now());
					
					try {
					    this.desktopRepository.save(employeeByDesktop);
					    this.desktopRepository.flush();
					    System.out.println("저장성공 확인 : " + (i + 1));
					    System.out.println("저장성공 확인 : " + row.getCell(2).getStringCellValue());
					} catch (Exception e) {
					    System.err.println("저장실패 확인: " + (i + 1));
					    e.printStackTrace();
					}
				} else {
					System.out.println("해당 데스크탑 없음");
				}
			} else {
				System.out.println("해당 부서원 없음");
			}
		}
		workbook.close();
	}
}
