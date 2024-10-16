package com.namgoo.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namgoo.category.Category;
import com.namgoo.category.CategoryService;
import com.namgoo.maker.Maker;
import com.namgoo.maker.MakerService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	// 2024-10-16 퇴근
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MakerService makerService;
	
	// 제품 목록
	@GetMapping("/product-list")
	public String findProductList(Model model) {
		List<Product> productList = this.productService.findProductList();
		model.addAttribute("productList", productList);
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
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
