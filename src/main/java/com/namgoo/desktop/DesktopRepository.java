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
	
	// 부서원으로 단일 조회
	public Optional<Desktop> findByEmployee(Employee employee);
	
	// 데스크탑 목록 조회
	public List<Desktop> findAll();
		
	// 타입별 데스크탑 총합 조회
	@Query("SELECT new com.namgoo.desktop.DesktopTypeAllDTO(" +
		   "dt.type AS 타입, COUNT(dt.type)) AS 총합 " +
		   "FROM Desktop AS d " +
		   "JOIN DesktopType AS dt ON d.desktopType.id = dt.id " +
		   "WHERE dt.type IN ('사무용', '설계용', '디자인용', '기타', '미달') " +
		   "GROUP BY dt.type")
	public List<DesktopTypeAllDTO> countDesktopTypeAllList();
	
	
	// 부서별 데스크탑 타입 총합 조회
	@Query("SELECT new com.namgoo.desktop.DesktopDepartmentAndTypeAllDTO(" +
		   "dm.department AS 부서, " +
		   "SUM(CASE WHEN dt.type = '사무용' THEN 1 ELSE 0 END), " +
		   "SUM(CASE WHEN dt.type = '설계용' THEN 1 ELSE 0 END), " +
		   "SUM(CASE WHEN dt.type = '디자인용' THEN 1 ELSE 0 END), " +
		   "SUM(CASE WHEN dt.type = '기타' THEN 1 ELSE 0 END), " +
		   "SUM(CASE WHEN dt.type = '미달' THEN 1 ELSE 0 END)) " +
		   "FROM Desktop d " +
		   "JOIN Department dm ON d.department.id = dm.id " +
		   "JOIN DesktopType dt ON d.desktopType.id = dt.id " +
		   "WHERE dm.department IN ('ICT사업부', '건축설계', '토목설계', '전기설계', 'PM', 'O&M', 'R&D', '업무지원', '정부사업', '자재관리', '회계', '경기지사') " +
		   "AND (dt.type IN ('사무용', '설계용', '디자인용', '기타', '미달'))" +
		   "GROUP BY dm.department")
	public List<DesktopDepartmentAndTypeAllDTO> countDesktopDepartmentAndTypeList();
			
}
