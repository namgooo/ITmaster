package com.namgoo.department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	// 부서 목록
	public List<Department> findAll();
	
	// 부서 검색 목록(페이징)
	public Page<Department> findAll(Specification<Department> specification, Pageable pageable);
	
	// 부서 단일 조회
	public Optional<Department> findById(Integer id);
	
	// 부서명으로 단일 조회
	public Optional<Department> findByDepartment(String department);
	
}
