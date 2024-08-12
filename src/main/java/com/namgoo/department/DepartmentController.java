package com.namgoo.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	// 부서 목록
	@GetMapping("/department-list")
	public String department(Model model) {
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		return "department/department";
	}
	
	// 부서 등록
	@PostMapping("/department-create")
	public String createDepartment(DepartmentDTO dto) {
		this.departmentService.createDeaprtment(dto);
		return "redirect:/department/department-list";
	}
	
	// 부서 삭제
	@GetMapping("/department-delete/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		this.departmentService.deleteDepartment(id);
		return "redirect:/department/department-list";
	}
	
	// 부서 상세
	@GetMapping("/department-detail/{id}")
	public String getDepartmentDetail(@PathVariable("id") Integer id, Model model) {
		Department department = this.departmentService.getDepartmentDetail(id).orElse(null);
		model.addAttribute("department", department);
		return "department/department_detail";
	}
	
	// 부서 수정
	@PostMapping("/department-update")
	public String updateDepartment(DepartmentDTO dto) {
		this.departmentService.updateDepartmentById(dto);
		return "redirect:/department/department-list";
	}
	
}
