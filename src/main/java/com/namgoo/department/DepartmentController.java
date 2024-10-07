package com.namgoo.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	// 부서 목록
	@GetMapping("/department-list")
	public String department(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		Page<Department> departmentList = this.departmentService.findDepartmentPagingList(keyword, pageable);
		model.addAttribute("departmentList", departmentList);
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", departmentList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", departmentList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", departmentList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", departmentList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", departmentList.getTotalPages()); // 마지막 페이지
		return "department/department_list";
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
