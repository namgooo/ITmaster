package com.namgoo.maker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.category.CategoryRepository;

@Service
public class MakerService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MakerRepository makerRepository;
	
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
