package com.namgoo.answer;

import java.time.LocalDateTime;

import com.namgoo.question.Question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String reply;
	private LocalDateTime createDate;
	
	// ManyToOne
	@ManyToOne
	private Question question;
	

}
