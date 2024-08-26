package com.namgoo.product_info;

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

import net.bytebuddy.matcher.MethodSortMatcher.Sort;

@Controller
@RequestMapping("/product-info")
public class ProductInfoController {
	
	@Autowired
	private ProductInfoService productInfoService;
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
	
	// 제품 정보 목록
	@GetMapping("/product-info-list")
	public String findProductInfoList(@PageableDefault(size = 5) Pageable pageable, Model model) {
	
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
		// 페이징
		Page<ProductInfo> productInfoList = this.productInfoService.findProductInfoList(pageable);
		model.addAttribute("productInfoList", productInfoList);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", productInfoList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", productInfoList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", productInfoList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", productInfoList.getTotalPages()); // 총 페이지 수
		return "product_info/product_info_list";
	}
	
	// 제품 정보 등록
	@GetMapping("/product-info-create")
	public String createProductInfo(Model model) {
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
		return "product_info/product_info_create";
	}
	
	// 제품 정보 등록
	@PostMapping("/product-info-create")
	public String createProductInfo(ProductInfoDTO dto) {
		this.productInfoService.createProductInfo(dto);
		return "redirect:/product-info/product-info-list";
	}
	
	// 제품 정보 삭제
	@GetMapping("/product-info-delete/{id}")
	public String deleteProductInfo(@PathVariable("id") Integer id) {
		this.productInfoService.deleteProductInfo(id);
		return "redirect:/product-info/product-info-list";
	}
	
	// 제품 정보 수정
	@GetMapping("/product-info-detail/{id}")
	public String getProductInfoDetail(@PathVariable("id") Integer id, Model model) {
		ProductInfo productInfo = this.productInfoService.getProductInfoDetail(id);
		model.addAttribute("productInfo", productInfo);
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
		return "product_info/product_info_update";
	}
	
	// 제품 정보 수정
	@PostMapping("/product-info-update")
	public String updateProductInfo(ProductInfoDTO dto) {
		this.productInfoService.updateProductInfo(dto);
		return "redirect:/product-info/product-info-list";
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
	@GetMapping("/getProductsByMaker")
	@ResponseBody
	public List<String> getProductsByMaker(@RequestParam("maker") String maker) {
		return this.productService.findProductsByMaker(maker);
	}
	
}
