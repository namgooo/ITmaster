package com.namgoo.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.category.Category;
import com.namgoo.category.CategoryRepository;
import com.namgoo.department.Department;
import com.namgoo.employee.Employee;
import com.namgoo.maker.Maker;
import com.namgoo.maker.MakerRepository;
import com.namgoo.product_info.ProductInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MakerRepository makerRepository;
	
	// 제품 검색
	private Specification<Product> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Product> p, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate filterPredicate = cb.or(
						cb.like(p.get("product"), "%" + keyword + "%")	
				);
				query.distinct(true);
				query.orderBy(cb.desc(p.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 제품 검색 목록(페이징)
	public Page<Product> findProductPagingList(String keyword, Pageable pageable) {
		Specification<Product> specification = search(keyword);
		Page<Product> productList = this.productRepository.findAll(specification, pageable);
		return productList;
	}
	
	// 제품 목록
	public List<Product> findProductList() {
		List<Product> productList = this.productRepository.findAll();
		return productList;
	}
	
	// 제품 등록
	public void createProduct(ProductDTO dto) {
		Optional<Category> category = this.categoryRepository.findByCategory(dto.getCategory());
		Optional<Maker> maker = this.makerRepository.findByMaker(dto.getMaker());
		Product product = new Product();
		product.setProduct(dto.getProduct());
		product.setCreateDate(LocalDateTime.now());
		product.setCategory(category.get());
		product.setMaker(maker.get());
		this.productRepository.save(product);
	}
	
	// 제품 삭제
	public void deleteProduct(Integer id) {
		this.productRepository.deleteById(id);
	}
	
	// 제품 상세
	public Product getProductDetail(Integer id) {
		Product product = this.productRepository.findById(id).orElse(null);
		return product;
	}
	
	// 제품 수정
	public Product updateProduct(ProductDTO dto) {
		Category category = this.categoryRepository.findByCategory(dto.getCategory()).get();
		Maker maker = this.makerRepository.findByMaker(dto.getMaker()).get();
		Product product = this.productRepository.findById(dto.getId()).orElse(null);
		product.setProduct(dto.getProduct());
		product.setCreateDate(LocalDateTime.now());
		product.setCategory(category);
		product.setMaker(maker);
		this.productRepository.save(product);
		return product;
	}
	
	// 카테고리 선택 시 해당 제품 조회
	public List<String> findProductsByCategory(String category) {
		List<String> categoryList = this.productRepository.findProductsByCategory(category);
		return categoryList;
	}
	
	// 제조사 선택 시 해당 제품 조회
	public List<String> findProductsByMaker(String Maker) {
		List<String> makerList = this.productRepository.findProductsByMaker(Maker);
		return makerList;
	}
	
}
