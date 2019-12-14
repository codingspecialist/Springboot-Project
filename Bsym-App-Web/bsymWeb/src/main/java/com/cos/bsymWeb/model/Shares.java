package com.cos.bsymWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.cos.bsymWeb.model.Likes.LikesBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Builder
public class Shares {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@OneToOne
	@JoinColumn(name = "bsymBoardId")
	private BsymBoard bsymBoard;
	
	@ManyToOne
	@JoinColumn(name = "rockerId")
	@JsonIgnore
	private Rocker rocker;

}
