package com.namgoo;

import java.time.LocalDateTime;

import org.apache.groovy.parser.antlr4.GroovyParser.ThisFormalParameterContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.namgoo.category.Category;
import com.namgoo.department.Department;
import com.namgoo.employee.Employee;
import com.namgoo.maker.Maker;
import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoRepository;

@SpringBootTest
class ITmasterApplicationTests {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Test
	void contextLoads() {
		
		for(int i = 0; i < 100; i++) {
			Category c = new Category();
			Maker m = new Maker();
			Product p = new Product();
			Department d = new Department();
			Employee e = new Employee();
			

			ProductInfo p1 = new ProductInfo();
		
			p1.setMaker(m);
			p1.setProduct(p);
			p1.setDepartment(d);
			p1.setEmployee(e);
			p1.setSimpleName("테스트");
			p1.setUseStatus("중고");
			p1.setItemStatus("결함");
			p1.setLocation("보급");
			p1.setPrice("모름");
			p1.setBuyYear("모름");
			p1.setProductComment("없음");
			p1.setUniqueCode("1111");
			p1.setCreateDate(LocalDateTime.now());
			this.productInfoRepository.save(p1);
			
		}
		
	}
	
	

}
