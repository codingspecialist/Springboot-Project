package com.cos.bsymWeb.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Builder
public class BsymBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String broadcastName;
	private String title;
	@Column(length = 1000)
	private String imglink;
	@Column(length = 1000)
	private String imgPath;
	@Column(length = 1000)
	private String link;
	private String description;
	private int readCount;
	private int likeCount;
	private int shareCount;
	
	@CreationTimestamp
	private LocalDate createDate;
	private String postdate;
	@Transient
	@Enumerated(value = EnumType.STRING)
	private BoardDivision boardDivision;
	private String division;
	
	@Transient
	private String likeCheck = "false";
	@Transient
	private int page;
}
