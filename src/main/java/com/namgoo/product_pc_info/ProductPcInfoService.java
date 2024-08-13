package com.namgoo.product_pc_info;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.category.Category;
import com.namgoo.category.CategoryRepository;
import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeRepository;
import com.namgoo.maker.Maker;
import com.namgoo.maker.MakerRepository;
import com.namgoo.product.Product;
import com.namgoo.product.ProductRepository;

@Service
public class ProductPcInfoService {
	
	@Autowired
	private ProductPcInfoRepository productPcInfoRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MakerRepository makerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// 제품 정보(PC) 목록
	public List<ProductPcInfo> findProductPcInfoList() {
		List<ProductPcInfo> productPcInfoList = this.productPcInfoRepository.findAll();
		return productPcInfoList;
	}
	
	// 제품 정보(PC) 등록
	public void createProductPcInfo(ProductPcInfoDTO dto) {
		Category category = this.categoryRepository.findByCategory(dto.getCategory()).orElse(null);
		Maker maker = this.makerRepository.findByMaker(dto.getMaker()).orElse(null);
		Product product = this.productRepository.findByProduct(dto.getProduct()).orElse(null);
		Department deppartment = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		
		// 두 자리 문자열로 변환
		int categoryId = category.getId();
		int makerId = maker.getId();
		int productId = product.getId();
		String categoryNum = String.format("%02d", categoryId);
		String makerNum = String.format("%02d", makerId);
		String productNum = String.format("%02d", productId);
		
		ProductPcInfo productPcInfo = new ProductPcInfo();
		productPcInfo.setSimpleName(dto.getSimpleName());
		productPcInfo.setUseStatus(dto.getUseStatus());
		productPcInfo.setItemStatus(dto.getItemStatus());
		productPcInfo.setLocation(dto.getLocation());
		productPcInfo.setPrice(dto.getPrice());
		productPcInfo.setBuyYear(dto.getBuyYear());
		productPcInfo.setProductComment(dto.getProductComment());
		productPcInfo.setUniqueCode(categoryNum + makerNum + productNum + "-" + dto.getUniqueCode());
		
		productPcInfo.setCategory(category);
		productPcInfo.setMaker(maker);
		productPcInfo.setProduct(product);
		productPcInfo.setDepartment(deppartment);
		productPcInfo.setEmployee(employee);
		this.productPcInfoRepository.save(productPcInfo);
	}
	
	// 제품 정보(PC) 삭제
	public void deleteProductPcInfo(Integer id) {
		this.productPcInfoRepository.deleteById(id);
	}
	
	// 제품 정보(PC) 상세
	public ProductPcInfo getProductPcInfoDetail(Integer id) {
		ProductPcInfo productPcInfo = this.productPcInfoRepository.findById(id).orElse(null);
		return productPcInfo;
	}
	
	// 제품 정보 수정
	public void updateProductPcInfo(ProductPcInfoDTO dto) {
		ProductPcInfo productPcInfo = this.productPcInfoRepository.findById(dto.getId()).orElse(null);
		Category category = this.categoryRepository.findById(dto.getId()).orElse(null);
		Maker maker = this.makerRepository.findById(dto.getId()).orElse(null);
		Product product = this.productRepository.findById(dto.getId()).orElse(null);
		Department department = this.departmentRepository.findById(dto.getId()).orElse(null);
		Employee employee = this.employeeRepository.findById(dto.getId()).orElse(null);
		
		// 두 자리 문자열로 변환
		int categoryId = category.getId();
		int makerId = maker.getId();
		int productId = product.getId();
		String categoryNum = String.format("%02d", categoryId);
		String makerNum = String.format("%02d", makerId);
		String productNum = String.format("%02d", productId);
		
		productPcInfo.setSimpleName(dto.getSimpleName());
		productPcInfo.setUseStatus(dto.getUseStatus());
		productPcInfo.setItemStatus(dto.getItemStatus());
		productPcInfo.setLocation(dto.getLocation());
		productPcInfo.setPrice(dto.getPrice());
		productPcInfo.setBuyYear(dto.getBuyYear());
		productPcInfo.setProductComment(dto.getProductComment());
		productPcInfo.setUniqueCode(categoryNum + makerNum + productNum + "-" + dto.getUniqueCode());
		productPcInfo.setCreateDate(LocalDateTime.now());
		
		productPcInfo.setCategory(category);
		productPcInfo.setMaker(maker);
		productPcInfo.setProduct(product);
		productPcInfo.setDepartment(department);
		productPcInfo.setEmployee(employee);
		this.productPcInfoRepository.save(productPcInfo);
	}

}
