package com.namgoo.department;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.category.Category;
import com.namgoo.employee.Employee;
import com.namgoo.maker.Maker;
import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// 부서 검색
	private Specification<Department> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Department> dm, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate filterPredicate = cb.or(						
						cb.like(dm.get("department"), "%" + keyword + "%")
				);
				query.distinct(true);
				query.orderBy(cb.desc(dm.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 부서 검색 목록(페이징)
	public Page<Department> findDepartmentPagingList(String keyword, Pageable pageable) {
		Specification<Department> specification = search(keyword);
		Page<Department> departmentPagingList = this.departmentRepository.findAll(specification, pageable);
		return departmentPagingList;
	}
	
	// 부서 목록
	public List<Department> findDepartmentList() {
		List<Department> departmentList = this.departmentRepository.findAll();
		return departmentList;
	}

	// 부서 등록
	public void createDeaprtment (DepartmentDTO dto) {
		Department department = new Department();
		department.setDepartment(dto.getDepartment());
		department.setCreateDate(LocalDateTime.now());
		this.departmentRepository.save(department);
	}
	
	// 부서 삭제
	public void deleteDepartment(Integer id) {
		this.departmentRepository.deleteById(id);
	}
	
	// 부서 상세
	public Optional<Department> getDepartmentDetail(Integer id) {
		Optional<Department> department = this.departmentRepository.findById(id);
		return department;
	}
	
	// 부서 수정
	public void updateDepartmentById(DepartmentDTO dto) {
		Integer departmentId = dto.getId();
		Department department = this.departmentRepository.findById(departmentId).orElse(null);
		department.setDepartment(dto.getDepartment());
		department.setCreateDate(LocalDateTime.now());
		this.departmentRepository.save(department);
	}
	
	// 부서 단일 조회
	public Department findDepartmentById(Integer id) {
		Department department = this.departmentRepository.findById(id).orElse(null);
		return department;
	}
		
}
