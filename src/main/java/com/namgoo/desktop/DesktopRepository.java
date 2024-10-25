package com.namgoo.desktop;

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
import com.namgoo.employee.Employee;

@Repository
public interface DesktopRepository extends JpaRepository<Desktop, Integer>{

	// 데스크탑 검색 목록(페이징)
	public Page<Desktop> findAll(Specification<Desktop> specification, Pageable pageable);
	
	// 부서별 데스크탑 검색 목록(페이징)
	@Query("SELECT d FROM Desktop d WHERE d.department = :department AND d.desktop LIKE %:keyword%")
	public Page<Desktop> findByDepartmentAndDesktop(@Param("department") Department department, @Param("keyword") String keyword, Pageable pageable);
	
	// 데스크탑 단일 조회
	public Optional<Desktop> findById(Integer id);
	
	// 데스크탑명으로 단일 조회
	public Optional<Desktop> findByDesktop(String desktop);
}
