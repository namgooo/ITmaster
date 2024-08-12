package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.category.CategoryRepository;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeRepository;

@Service
public class DesktopService {
	
	@Autowired
	private DesktopRepository desktopRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// 데스크탑 목록
	public List<Desktop> finddesktopList() {
		List<Desktop> desktopList = this.desktopRepository.findAll();
		return desktopList;
	}
	
	// 데스크탑 등록
	public void createDesktop(DesktopDTO dto) {
		Desktop desktop = new Desktop();
		desktop.setDesktop(dto.getDesktop());
		desktop.setCreateDate(LocalDateTime.now());
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
		Optional<Employee> employee  = this.employeeRepository.findByEmployee(dto.getEmployee());
		Desktop desktop = this.desktopRepository.findById(dto.getId()).get();
		desktop.setDesktop(dto.getDesktop());
		desktop.setCreateDate(LocalDateTime.now());
		if(employee.isEmpty()) {
			desktop.setEmployee(employee.get());
		} else {
			desktop.setEmployee(employee.get());
		}
		this.desktopRepository.save(desktop);
	}
	

}
