package com.namgoo.employee;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// 부서원 검색
	private Specification<Employee> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Employee> em, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate filterPredicate = cb.or(						
						cb.like(em.get("employee"), "%" + keyword + "%")
				);
				query.distinct(true);
				query.orderBy(cb.desc(em.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 부서원 목록
	public List<Employee> findEmployeeList() {
		List<Employee> employeeList = this.employeeRepository.findAll();
		return employeeList;
	}
	
	// 부서원 검색 목록(페이징)
	public Page<Employee> findEmployeePagingList(String keyword, Pageable pageable) {
		Specification<Employee> specification = search(keyword);
		Page<Employee> employeePagingList = this.employeeRepository.findAll(specification, pageable);
 		return employeePagingList;
	}
	
	// 부서별 부서원 목록(페이징)
	public Page<Employee> findEmployeesByDepartmentPagingList(Department departmnet, String keyword, Pageable pageable) {
		Page<Employee> employeeList = this.employeeRepository.findByDepartmentAndEmployee(departmnet, keyword, pageable);
		return employeeList;
	}
	
	// 부서원 등록
	public void createEmployee(EmployeeDTO dto) {
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).get();
		Employee employee = new Employee();
		employee.setEmployee(dto.getEmployee());
		employee.setCreateDate(LocalDateTime.now());
		employee.setDepartment(department);
		this.employeeRepository.save(employee);
	}
	
	// 부서원 삭제
	public void deleteEmployee(Integer id) {
		this.employeeRepository.deleteById(id);
	}
	
	// 부서원 상세
	public Employee getEmployeeDetail(Integer id) {
		Employee employee = this.employeeRepository.findById(id).orElse(null);
		return employee;
	}
	
	// 부서원 수정
	public void updateEmployee(EmployeeDTO dto) {
		Employee employee = this.employeeRepository.findById(dto.getId()).orElse(null);
		employee.setEmployee(dto.getEmployee());
		employee.setCreateDate(LocalDateTime.now());
		this.employeeRepository.save(employee);
	}
	
	// 부서 선택시 해당 부서 부서원 조회
	public List<String> findEmployeesByDepartment(String department) {
		List<String> employeeList = this.employeeRepository.findByDepartment(department);
		return employeeList;
	}
	
}
