package com.namgoo;

import java.time.LocalDate;
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
import com.namgoo.question.Question;
import com.namgoo.question.QuestionRepository;

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
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testQuestionSave() {
		
		for(int i = 1; i < 30; i++) {
			Question question = new Question();
			question.setSubject("질문 테스트" + i);
			question.setContent("내용 테스트" + i);
			question.setCreateDate(LocalDateTime.now());
			this.questionRepository.save(question);
		}
		
	}
	
}
