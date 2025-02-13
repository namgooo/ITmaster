package com.namgoo.desktop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.category.CategoryRepository;
import com.namgoo.department.Department;
import com.namgoo.department.DepartmentRepository;
import com.namgoo.desktop_type.DesktopType;
import com.namgoo.desktop_type.DesktopTypeRepository;
import com.namgoo.employee.Employee;
import com.namgoo.employee.EmployeeRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class DesktopService {
	
	@Autowired
	private DesktopRepository desktopRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DesktopTypeRepository desktopTypeRepository;
	
	// 데스크탑 검색
	private Specification<Desktop> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Desktop> dk, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<DesktopType, Desktop> dd = dk.join("desktopType", JoinType.LEFT);
				Join<Department, Desktop> mdd = dk.join("department", JoinType.LEFT);
				Join<Employee, Desktop> ed = dk.join("employee", JoinType.LEFT);
				
				Predicate filterPredicate = cb.or(
						cb.like(dd.get("type"), "%" + keyword + "%"),
						cb.like(mdd.get("department"), "%" + keyword + "%"),
						cb.like(ed.get("employee"), "%" + keyword + "%"),
						cb.like(dk.get("desktop"), "%" + keyword + "%")
				);
				query.distinct(true);
				query.orderBy(cb.desc(dk.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 데스크탑 검색 목록(페이징)
	public Page<Desktop> findDesktopPagingList(String keyword, Pageable pageable) {		
		Specification<Desktop> specification = search(keyword);
		Page<Desktop> desktopPagingList = this.desktopRepository.findAll(specification, pageable);
		return desktopPagingList;
	}
	
	// 부서별 데스크탑 검색 목록(페이징)
	public Page<Desktop> findDesktopsByDepartmentPagingList(Department department, String keyword, Pageable pageable) {
		Page<Desktop> desktopList = this.desktopRepository.findByDepartmentAndDesktop(department, keyword, pageable);
		return desktopList;
	}
	
	// 데스크탑 등록
	public void createDesktop(DesktopDTO dto) {
		Department departemnt = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		DesktopType desktopType = this.desktopTypeRepository.findByType(dto.getDesktopType()).orElse(null);
		
		Desktop desktop = new Desktop();
		desktop.setDesktop(dto.getDesktop());
		desktop.setMainboard(dto.getMainboard());
		desktop.setCpu(dto.getCpu());
		desktop.setGpu(dto.getGpu());
		desktop.setSsd(dto.getSsd());
		desktop.setPower(dto.getPower());
		desktop.setMemory1(dto.getMemory1());
		desktop.setMemory2(dto.getMemory2());
		desktop.setMemory3(dto.getMemory3());
		desktop.setMemory4(dto.getMemory4());
		desktop.setCpuScore(dto.getCpuScore());
		desktop.setGpuScore(dto.getGpuScore());
		desktop.setCreateDate(LocalDateTime.now());
		
		desktop.setDepartment(departemnt);
		desktop.setEmployee(employee);
		desktop.setDesktopType(desktopType);
		this.desktopRepository.save(desktop);
	}
	
	// 데스크탑 삭제
	public void deleteDesktop(Integer id) {
		this.desktopRepository.deleteById(id);
	}
	
	// 데스크탑 상세
	public Desktop getDesktopDetail(Integer id) {
		Desktop desktop = this.desktopRepository.findById(id).orElse(null);
		return desktop;
	}
	
	// 데스크탑 수정
	public void updateDesktop(DesktopDTO dto) {
		Desktop desktop = this.desktopRepository.findById(dto.getId()).orElse(null);
		Department department = this.departmentRepository.findByDepartment(dto.getDepartment()).orElse(null);
		Employee employee = this.employeeRepository.findByEmployee(dto.getEmployee()).orElse(null);
		DesktopType desktopType = this.desktopTypeRepository.findByType(dto.getDesktopType()).orElse(null);
		
		desktop.setDesktop(dto.getDesktop());
		desktop.setMainboard(dto.getMainboard());
		desktop.setCpu(dto.getCpu());
		desktop.setGpu(dto.getGpu());
		desktop.setSsd(dto.getSsd());
		desktop.setPower(dto.getPower());
		desktop.setMemory1(dto.getMemory1());
		desktop.setMemory2(dto.getMemory2());
		desktop.setMemory3(dto.getMemory3());
		desktop.setMemory4(dto.getMemory4());
		desktop.setCpuScore(dto.getCpuScore());
		desktop.setGpuScore(dto.getGpuScore());
		desktop.setCreateDate(LocalDateTime.now());
		
		desktop.setDepartment(department);
		desktop.setEmployee(employee);
		desktop.setDesktopType(desktopType);
		this.desktopRepository.save(desktop);
	}
	
	// 데스크탑 목록 조회
	public List<Desktop> findDesktopList() {
		List<Desktop> desktopList = this.desktopRepository.findAll();
		return desktopList;
	}
		
	// 부서별 데스크탑 타입 총합 조회
	public List<DesktopDepartmentAndTypeAllDTO> countDesktopDepartmentAndTypeList() {
		List<DesktopDepartmentAndTypeAllDTO> countDesktopDepartmentAndTypeList = this.desktopRepository.countDesktopDepartmentAndTypeList();
		return countDesktopDepartmentAndTypeList;
	}
	
	// 타입별 데스크탑 총합 조회
	public List<DesktopTypeAllDTO> countDesktopTypeAllList() {
		List<DesktopTypeAllDTO> countDesktopTypeAllList = this.desktopRepository.countDesktopTypeAllList();
		return countDesktopTypeAllList;
	}
	
}
