package com.namgoo.employee;

import java.util.List;
import java.util.Optional;

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

import com.namgoo.department.Department;
import com.namgoo.department.DepartmentService;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoRepository;
import com.namgoo.product_info.ProductInfoService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	// 2025-01-07 커밋
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	
	// 부서원 검색 목록(페이징)
	@GetMapping("/employee-list")
	public String findEmployeePagingList(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		Page<Employee> employeeList = this.employeeService.findEmployeePagingList(keyword, pageable);
		model.addAttribute("employeeList", employeeList);
				
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", employeeList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", employeeList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", employeeList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", employeeList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", employeeList.getTotalPages()); // 마지막 페이지
		return "employee/employee_list";
	}

	// 부서별 부서원 검색 목록(페이징)
	@GetMapping("/employee-list/{id}")
	public String findEmployeesByDepartmentPagingList(@PathVariable("id") Integer id, @PageableDefault(size=10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		Department department =  this.departmentService.findDepartmentById(id);
		Page <Employee> employeeList = this.employeeService.findEmployeesByDepartmentPagingList(department, keyword, pageable);
		model.addAttribute("employeeList", employeeList);
		
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", employeeList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", employeeList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", employeeList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", employeeList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", employeeList.getTotalPages()); // 마지막 페이지
		
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
