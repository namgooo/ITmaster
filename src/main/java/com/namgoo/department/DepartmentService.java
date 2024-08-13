package com.namgoo.department;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
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
