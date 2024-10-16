package com.namgoo.maker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.category.Category;
import com.namgoo.category.CategoryRepository;
import com.namgoo.department.Department;
import com.namgoo.employee.Employee;
import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class MakerService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MakerRepository makerRepository;
	
	// 제조사 검색
	private Specification<Maker> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Maker> m, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate filterPredicate = cb.or(
						cb.like(m.get("maker"), "%" + keyword + "%")	
				);
				query.distinct(true);
				query.orderBy(cb.desc(m.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 제조사 검색 목록(페이징)
	public Page<Maker> findMakerPagingList(String keyword, Pageable pageable) {
		Specification<Maker> specification = search(keyword);
		Page<Maker> makerList = this.makerRepository.findAll(specification, pageable);
		return makerList;
	}
	
	// 제조사 목록
	public List<Maker> findMakerList() {
		List<Maker> makerList = this.makerRepository.findAll();
		return makerList;
	}
	
	// 제조사 등록
	public void createMaker(MakerDTO dto) {
		Maker maker = new Maker();
		maker.setMaker(dto.getMaker());
		maker.setCreateDate(LocalDateTime.now());
		this.makerRepository.save(maker);
	}
	
	// 제조사 삭제
	public void deleteMaker(Integer id) {
		this.makerRepository.deleteById(id);
	}
	
	// 제조사 상세
	public Maker getMakerDetail(Integer id) {
		Maker maker = this.makerRepository.findById(id).orElse(null);
		return maker;
	}
	
	// 제조사 수정
	public void updateMaker(MakerDTO dto) {
		Maker maker = this.makerRepository.findById(dto.getId()).orElse(null);
		maker.setMaker(dto.getMaker());
		maker.setCreateDate(LocalDateTime.now());
		this.makerRepository.save(maker);
	}
	

}
