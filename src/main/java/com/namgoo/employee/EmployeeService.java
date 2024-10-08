package com.namgoo.employee;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// 부서원 목록
	public List<Employee> findEmployeeList() {
		List<Employee> employeeList = this.employeeRepository.findAll();
		return employeeList;
	}
	
	// 부서별 부서원 목록
	public List<Employee> findEmployeesByDepartment(Department departmnet) {
		List<Employee> employeeList = this.employeeRepository.findByDepartment(departmnet);
		return employeeList;
	}
	
	// 부서원 등록
	public void createEmployee(EmployeeDTO dto) {
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).get();
		Employee employee = new Employee();
		employee.setEmployee(dto.getEmployee());
		employee.setCreateDate(LocalDateTime.now());
		employee.setDepartment(department);
		this.employeeRepository.save(employee);
	}
	
	// 부서원 삭제
	public void deleteEmployee(Integer id) {
		this.employeeRepository.deleteById(id);
	}
	
	// 부서원 상세
	public Employee getEmployeeDetail(Integer id) {
		Employee employee = this.employeeRepository.findById(id).orElse(null);
		return employee;
	}
	
	// 부서원 수정
	public void updateEmployee(EmployeeDTO dto) {
		Employee employee = this.employeeRepository.findById(dto.getId()).orElse(null);
		employee.setEmployee(dto.getEmployee());
		employee.setCreateDate(LocalDateTime.now());
		this.employeeRepository.save(employee);
	}
	
	// 부서 선택시 해당 부서 부서원 조회
	public List<String> findEmployeesByDepartment(String department) {
		List<String> employeeList = this.employeeRepository.findByDepartment(department);
		return employeeList;
	}
	
}
