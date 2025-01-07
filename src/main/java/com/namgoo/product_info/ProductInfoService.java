package com.namgoo.product_info;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class ProductInfoService {
	
	@Autowired
	private ProductInfoRepository productInfoRepository;
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
	
	// 제품 정보 검색
	private Specification<ProductInfo> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ProductInfo> pi, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Category, ProductInfo> cp = pi.join("category", JoinType.LEFT);
				Join<Maker, ProductInfo> mp = pi.join("maker", JoinType.LEFT);
				Join<Product, ProductInfo> pp = pi.join("product", JoinType.LEFT);
				Join<Department, ProductInfo> dp = pi.join("department", JoinType.LEFT);
				Join<Employee, ProductInfo> ep = pi.join("employee", JoinType.LEFT);
				
				Predicate filterPredicate = cb.or(
						cb.like(pi.get("simpleName"), "%" + keyword + "%"),
						cb.like(pi.get("useStatus"), "%" + keyword + "%"),
						cb.like(pi.get("itemStatus"), "%" + keyword + "%"),
						cb.like(pi.get("location"), "%" + keyword + "%"),
						cb.like(pi.get("price"), "%" + keyword + "%"),
						cb.like(pi.get("buyYear"), "%" + keyword + "%"),
						cb.like(pi.get("productComment"), "%" + keyword + "%"),
						cb.like(pi.get("uniqueCode"), "%" + keyword + "%"),
						cb.like(cp.get("category"), "%" + keyword + "%"),
						cb.like(mp.get("maker"), "%" + keyword + "%"),
						cb.like(pp.get("product"), "%" + keyword + "%"),
						cb.like(dp.get("department"), "%" + keyword + "%"),
						cb.like(ep.get("employee"), "%" + keyword + "%")	
				);
				query.distinct(true);
				query.orderBy(cb.desc(pi.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 제품 정보 검색 목록(페이징)
	public Page<ProductInfo> findProductInfoPagingList(String keyword, Pageable pageable) {
		Specification<ProductInfo> specification = search(keyword);
		Page<ProductInfo> productInfoList = this.productInfoRepository.findAll(specification, pageable);
		return productInfoList;
	}
			
	// 제품 정보 등록
	public void createProductInfo(ProductInfoDTO dto) {
		Category category = this.categoryRepository.findByCategory(dto.getCategory()).orElse(null);
		Maker maker = this.makerRepository.findByMaker(dto.getMaker()).orElse(null);
		Product product = this.productRepository.findByProduct(dto.getProduct()).orElse(null);
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		
		// 두 자리 문자열로 변환
		int categoryId = category.getId();
		int makerId = maker.getId();
		int productId = product.getId();
		String categoryNum = String.format("%02d", categoryId);
		String makerNum = String.format("%02d", makerId);
		String productNum = String.format("%02d", productId);
		
		ProductInfo productInfo = new ProductInfo();
		productInfo.setSimpleName(dto.getSimpleName());
		productInfo.setUseStatus(dto.getUseStatus());
		productInfo.setItemStatus(dto.getItemStatus());
		productInfo.setLocation(dto.getLocation());
		productInfo.setPrice(dto.getPrice());
		productInfo.setBuyYear(dto.getBuyYear());
		productInfo.setProductComment(dto.getProductComment());
		productInfo.setUniqueCode(categoryNum + makerNum + productNum + "-" + dto.getUniqueCode());
		productInfo.setCreateDate(LocalDateTime.now());
		
		productInfo.setCategory(category);
		productInfo.setMaker(maker);
		productInfo.setProduct(product);
		productInfo.setDepartment(department);
		productInfo.setEmployee(employee);
		this.productInfoRepository.save(productInfo);
	}
	
	// 제품 정보 삭제
	public void deleteProductInfo(Integer id) {
		this.productInfoRepository.deleteById(id);
	}
	
	// 제품 정보 수정(페이지)
	public ProductInfo getProductInfoDetail(Integer id) {
		ProductInfo productInfo = this.productInfoRepository.findById(id).orElse(null);
		return productInfo;
	}
	
	// 제품 정보 수정
	public void updateProductInfo(ProductInfoDTO dto) {
		ProductInfo productInfo = this.productInfoRepository.findById(dto.getId()).orElse(null);
		Category category = this.categoryRepository.findByCategory(dto.getCategory()).get();
		Maker maker = this.makerRepository.findByMaker(dto.getMaker()).get();
		Product product = this.productRepository.findByProduct(dto.getProduct()).get();
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).get();
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).get();
		
		// 두 자리 문자열로 변환
		int categoryId = category.getId();
		int makerId = maker.getId();
		int productId = product.getId();
		String categoryNum = String.format("%02d", categoryId);
		String makerNum = String.format("%02d", makerId);
		String productNum = String.format("%02d", productId);
		
		productInfo.setSimpleName(dto.getSimpleName());
		productInfo.setUseStatus(dto.getUseStatus());
		productInfo.setItemStatus(dto.getItemStatus());
		productInfo.setLocation(dto.getLocation());
		productInfo.setPrice(dto.getPrice());
		productInfo.setBuyYear(dto.getBuyYear());
		productInfo.setProductComment(dto.getProductComment());
		productInfo.setUniqueCode(categoryNum + makerNum + productNum + "-" + dto.getUniqueCode());
		productInfo.setCreateDate(LocalDateTime.now());
		
		productInfo.setCategory(category);
		productInfo.setMaker(maker);
		productInfo.setProduct(product);
		productInfo.setDepartment(department);
		productInfo.setEmployee(employee);
		this.productInfoRepository.save(productInfo);
	}
	
}
