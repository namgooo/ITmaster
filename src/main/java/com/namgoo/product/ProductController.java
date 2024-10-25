package com.namgoo.product;

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

import com.namgoo.category.Category;
import com.namgoo.category.CategoryService;
import com.namgoo.maker.Maker;
import com.namgoo.maker.MakerService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MakerService makerService;
	
	// 제품 목록
	@GetMapping("/product-list")
	public String findProductList(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
		
		Page<Product> productList = this.productService.findProductPagingList(keyword, pageable);
		model.addAttribute("productList", productList);
		
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", productList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", productList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", productList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", productList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", productList.getTotalPages()); // 마지막 페이지
		
		return "product/product_list";
	}
	
	// 제품 등록
	@PostMapping("/product-create")
	public String createProduct(ProductDTO dto) {
		this.productService.createProduct(dto);
		return "redirect:/product/product-list";
	}
	
	// 제품 삭제
	@GetMapping("/product-delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		this.productService.deleteProduct(id);
		return "redirect:/product/product-list";
	}
	
	// 제품 상세
	@GetMapping("/product-detail/{id}")
	public String getProductDetail(@PathVariable("id") Integer id, Model model) {
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
		Product product = this.productService.getProductDetail(id);
		model.addAttribute("product", product);
		return "product/product_detail";
	}
	
	// 제품 수정
	@PostMapping("/product-update")
	public String updateProduct(ProductDTO dto) {
		this.productService.updateProduct(dto);
		return "redirect:/product/product-list";
	}
	
}
