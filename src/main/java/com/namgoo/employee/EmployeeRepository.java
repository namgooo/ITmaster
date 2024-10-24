package com.namgoo.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.namgoo.department.Department;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	// 부서원 검색 목록(페이징)
	public Page<Employee> findAll(Specification<Employee> specification, Pageable pageable);
	
	// 부서원 목록
	public List<Employee> findAll();
		
	// 부서별 부서원 검색 목록(페이징)
	@Query("SELECT e FROM Employee e WHERE e.department = :department AND e.employee LIKE %:keyword%")
	Page<Employee> findByDepartmentAndEmployee(@Param("department") Department department, @Param("keyword") String keyword, Pageable pageable);
	
	// 부서원 단일 조회
	public Optional<Employee> findById(Integer id);
	
	// 부서원명으로 단일 조회
	public Optional<Employee> findByEmployee(String employee);
		
	// 부서 선택 시 해당 부서원 조회
	@Query("SELECT e.employee FROM Employee e JOIN e.department d WHERE d.department = ?1")
	public List<String> findByDepartment(String department);
	
}
