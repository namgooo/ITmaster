package com.namgoo.desktop_type;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.desktop.Desktop;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DesktopType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String type;
	private LocalDateTime createDate;
	
	// OneToMany
	@OneToMany(mappedBy = "desktopType", cascade = CascadeType.REMOVE)
	private List<Desktop> desktopList;
}
