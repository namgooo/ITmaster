package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.category.CategoryRepository;
import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeRepository;

@Service
public class DesktopService {
	
	@Autowired
	private DesktopRepository desktopRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// 데스크탑 목록
	public List<Desktop> finddesktopList() {
		List<Desktop> desktopList = this.desktopRepository.findAll();
		return desktopList;
	}
	
	// 데스크탑 등록
	public void createDesktop(DesktopDTO dto) {
		Department departemnt = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		
		Desktop desktop = new Desktop();
		desktop.setDesktop(dto.getDesktop());
		desktop.setMainboard(dto.getMainboard());
		desktop.setCpu(dto.getCpu());
		desktop.setGpu(dto.getGpu());
		desktop.setSsd(dto.getSsd());
		desktop.setPower(dto.getPower());
		desktop.setMemory1(dto.getMemory1());
		desktop.setMemory2(dto.getMemory2());
		desktop.setMemory3(dto.getMemory3());
		desktop.setMemory4(dto.getMemory4());
		desktop.setCreateDate(LocalDateTime.now());
		
		desktop.setDepartment(departemnt);
		desktop.setEmployee(employee);
		this.desktopRepository.save(desktop);
	}
	
	// 데스크탑 삭제
	public void deleteDesktop(Integer id) {
		this.desktopRepository.deleteById(id);
	}
	
	// 데스크탑 상세
	public Desktop getDesktopDetail(Integer id) {
		Desktop desktop = this.desktopRepository.findById(id).orElse(null);
		return desktop;
	}
	
	// 데스크탑 수정
	public void updateDesktop(DesktopDTO dto) {
		Desktop desktop = this.desktopRepository.findById(dto.getId()).orElse(null);
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		
		desktop.setDesktop(dto.getDesktop());
		desktop.setMainboard(dto.getMainboard());
		desktop.setCpu(dto.getCpu());
		desktop.setGpu(dto.getGpu());
		desktop.setSsd(dto.getSsd());
		desktop.setPower(dto.getPower());
		desktop.setMemory1(dto.getMemory1());
		desktop.setMemory2(dto.getMemory2());
		desktop.setMemory3(dto.getMemory3());
		desktop.setMemory4(dto.getMemory4());
		desktop.setCreateDate(LocalDateTime.now());
		
		desktop.setDepartment(department);
		desktop.setEmployee(employee);
		this.desktopRepository.save(desktop);
	}
	
}
