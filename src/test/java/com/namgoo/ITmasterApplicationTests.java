package com.namgoo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.groovy.parser.antlr4.GroovyParser.ThisFormalParameterContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_info.ProductInfoRepository;

@SpringBootTest
class ITmasterApplicationTests {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MakerRepository makerRepository;
	@Autowired
	private ProductRepository productRepository;
	
//	@Test
//	void testEmployeeSave() {
//		
//		for(int i = 0; i < 30; i++) {
//			Employee e = new Employee();
//			Optional<Department> d = this.departmentRepository.findById(2);
//			e.setEmployee("테스트" + i);
//			e.setCreateDate(LocalDateTime.now());
//			e.setDepartment(d.orElseGet(null));
//			this.employeeRepository.save(e);
//			
//		}
//		
//	}
	
}
