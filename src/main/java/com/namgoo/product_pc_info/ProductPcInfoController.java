package com.namgoo.product_pc_info;

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

@Controller
@RequestMapping("/product-pc-info")
public class ProductPcInfoController {
	
	@Autowired
	private ProductPcInfoService productPcInfoService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MakerService makerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	
	// 제품 정보(PC) 목록
	@GetMapping("/product-pc-info-list")
	public String findProductPcInfoList(Model model) {
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
		List<Product> productList = this.productService.findProductList();
		model.addAttribute("productList", productList);
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		List<Employee> employeeList = this.employeeService.findEmployeeList();
		model.addAttribute("employeeList", employeeList);
		List<ProductPcInfo> productPcInfoList = this.productPcInfoService.findProductPcInfoList();
		model.addAttribute("productPcInfoList", productPcInfoList);
		return "product_pc_info/product_pc_info";
	}
	
	// 제품 정보(PC) 등록
	@PostMapping("/product-pc-info-create")
	public String createProductPcInfo(ProductPcInfoDTO dto) {
		this.productPcInfoService.createProductPcInfo(dto);
		return "redirect:/product-pc-info/product-pc-info-list";
	}
	
	// 제품 정보(PC) 삭제
	@GetMapping("/product-pc-info-delete/{id}")
	public String deleteProductPcInfo(@PathVariable("id") Integer id) {
		this.productPcInfoService.deleteProductPcInfo(id);
		return "redirect:/product-pc-info/product-pc-info-list";
	}

	// 제품 정보 상세
	@GetMapping("/product-pc-info-detail/{id}")
	public String getProductPcInfoDetail(@PathVariable("id") Integer id, Model model) {
		ProductPcInfo productPcInfo = this.productPcInfoService.getProductPcInfoDetail(id);
		model.addAttribute("productPcInfo", productPcInfo);
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
		List<Product> productList = this.productService.findProductList();
		model.addAttribute("productList", productList);
		List<Department> departmentList = this.departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);
		List<Employee> employeeList = this.employeeService.findEmployeeList();
		model.addAttribute("employeeList", employeeList);
		return "product_pc_info/product_pc_info_detail";
	}
	
	// 부서 선택 시 해당 부서원 조회
	@GetMapping("/getEmployeesByDepartment")
	@ResponseBody
	public List<String> getEmployeesByDepartment(@RequestParam("department") String department) {
		return this.employeeService.findEmployeesByDepartment(department);
	}
	
	// 카테고리 선택 시 해당 제품 조회
	@GetMapping("/getProductsByCategory")
	@ResponseBody
	public List<String> getProductsByCategory(@RequestParam("category") String category) {
		return this.productService.findProductsByCategory(category);
	}
	
	// 제조사 선택 시 해당 제품 조회
	@GetMapping("getProductsByMaker")
	@ResponseBody
	public List<String> getProductsByMaker(@RequestParam("maker") String maker) {
		return this.productService.findProductsByMaker(maker);
	}
	
	
	
	
	
}
