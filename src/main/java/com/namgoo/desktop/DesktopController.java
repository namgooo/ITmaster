package com.namgoo.desktop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.namgoo.category.Category;
import com.namgoo.category.CategoryService;
import com.namgoo.department.Department;
import com.namgoo.department.DepartmentService;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeService;
import com.namgoo.maker.Maker;
import com.namgoo.maker.MakerService;
import com.namgoo.product.Product;
import com.namgoo.product.ProductService;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoService;

@Controller
@RequestMapping("/desktop")
public class DesktopController {
	
	@Autowired
	private DesktopService desktopService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	
	// 데스크탑 목록
	@GetMapping("/desktop-list")
	public String findDesktopList(Model model) {
		List<Desktop> desktopList = this.desktopService.finddesktopList();
		model.addAttribute("desktopList", desktopList);
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		List<Employee> employeeList = this.employeeService.findEmployeeList();
		model.addAttribute("employeeList", employeeList);
		return "desktop/desktop";
	}
	
	// 데스크탑 등록
	@PostMapping("/desktop-create")
	public String createDesktop(DesktopDTO dto) {
		this.desktopService.createDesktop(dto);
		return "redirect:/desktop/desktop-list";
	}
	
	// 데스크탑 삭제
	@GetMapping("/desktop-delete/{id}")
	public String deleteDesktop(@PathVariable("id") Integer id) {
		this.desktopService.deleteDesktop(id);
		return "redirect:/desktop/desktop-list";
	}
	
	// 데스크탑 상세
	@GetMapping("/desktop-detail/{id}")
	public String getDesktopDetail(@PathVariable("id") Integer id, Model model) {
		Desktop desktop = this.desktopService.getDesktopDetail(id);
		model.addAttribute("desktop", desktop);
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		List<Employee> employeeList = this.employeeService.findEmployeeList();
		model.addAttribute("employeeList", employeeList);
		return "desktop/desktop_detail";
	}
	
	// 데스크탑 수정
	@PostMapping("/desktop-update")
	public String updateDesktop(DesktopDTO dto) {
		this.desktopService.updateDesktop(dto);
		return "redirect:/desktop/desktop-list";
	}
	
	// 부서 선택 시 해당 부서원 조회
	@GetMapping("/getEmployeesByDepartment")
	@ResponseBody
	public List<String> getEmployeesByDepartment(@RequestParam("department") String department) {
		return this.employeeService.findEmployeesByDepartment(department);
	}
	
	
}
