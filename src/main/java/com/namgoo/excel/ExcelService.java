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
		
	public void saveExcelData(MultipartFile file) throws IOException {
		
		// 엑셀 파일을 읽음
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		// 첫번째 시트를 가져옴
		Sheet sheet = workbook.getSheetAt(0);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
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
			// 번호
			desktop.setId(null);
			
			System.out.println("____________________________________");
			System.out.println("ID 확인 : " + desktop.getId());
			
			System.out.println("부서 확인 : " + desktop.getDepartment().getDepartment());
			System.out.println("부서원 확인 : " + desktop.getEmployee().getEmployee());
			
			System.out.println(desktop.getDesktop());
			System.out.println(desktop.getMainboard());
			System.out.println(desktop.getCpu());
			System.out.println(desktop.getGpu());
			System.out.println(desktop.getSsd());
			System.out.println(desktop.getPower());
			System.out.println(desktop.getMemory1());
			System.out.println(desktop.getMemory2());
			System.out.println(desktop.getMemory3());
			System.out.println(desktop.getMemory4());
			System.out.println(desktop.getCreateDate());
			
			try {
			    this.desktopRepository.save(desktop);
			    this.desktopRepository.flush();
			    // 
			    System.out.println("저장성공 확인 : " + (i + 1));
			} catch (Exception e) {
			    System.err.println("저장실패 확인: " + (i + 1));
			    e.printStackTrace();
			}
			
			System.out.println("ID 확인 : " + desktop.getId());
			
		}

		workbook.close();
		
	}

}
