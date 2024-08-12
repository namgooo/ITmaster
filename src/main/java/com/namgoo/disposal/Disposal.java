package com.namgoo.disposal;

import java.time.LocalDateTime;

import com.namgoo.product_info.ProductInfo;
import com.namgoo.product_pc_info.ProductPcInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Disposal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String breakdownCause;
	private String breakdownStatus;
	private String repairStatus;
	private String repairPrice;
	private String todayPrice;
	private String disposalStatus;
	private LocalDateTime createDate;
	
	// OneToOne
	@OneToOne
	@JoinColumn(name = "product_info_id", referencedColumnName = "id")
	private ProductInfo productInfo;
	@OneToOne
	@JoinColumn(name = "product_pc_info_id", referencedColumnName = "id")
	private ProductPcInfo productPcInfo;

}
