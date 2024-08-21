package com.namgoo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namgoo.department.Department;
import com.namgoo.department.DepartmentService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	
	// 부서 목록
	@GetMapping("/employee-list")
	public String findEmployeeList(Model model) {
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		return "employee/employee_list";
	}
	
	// 부서별 부서원 목록
	@GetMapping("/employee-list/{id}")
	public String findEmployeesByDepartment(@PathVariable("id") Integer id, Model model) {
		Department department = this.departmentService.findDepartmentById(id);
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		List<Employee> employeeList = this.employeeService.findEmployeeList();
		model.addAttribute("employeeList", employeeList);
		return "employee/employee_list";
	}
	
	// 부서원 등록
	@PostMapping("/employee-create")
	public String createEmployee(EmployeeDTO dto) {
		this.employeeService.createEmployee(dto);
		return "redirect:/employee/employee-list";
	}
	
	// 부서원 삭제
	@GetMapping("/employee-delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		this.employeeService.deleteEmployee(id);
		return "redirect:/employee/employee-list";
	}
	
	// 부서원 상세
	@GetMapping("/employee-detail/{id}")
	public String getEmployeeDetail(@PathVariable("id") Integer id, Model model) {
		Employee employee = this.employeeService.getEmployeeDetail(id);
		model.addAttribute("employee", employee);
		return "employee/employee_detail";
	}
	
	// 부서원 수정
	@PostMapping("/employee-update")
	public String updateEmployee(EmployeeDTO dto) {
		this.employeeService.updateEmployee(dto);
		return "redirect:/employee/employee-list";
	}

}
