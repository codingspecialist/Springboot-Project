package com.cos.bsymWeb.model;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
public class Rocker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@OneToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({"address","createDate","updataDate","email","gender","name","password","phone"}) 
	private Users user;
	
	@OneToMany(mappedBy = "rocker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"rocker"})
	@Fetch(FetchMode.SELECT)
	private List<Likes> like;
	
	@OneToMany(mappedBy = "rocker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"rocker"})
	@Fetch(FetchMode.SELECT)
	private List<Shares> share;
	
	@OneToMany(mappedBy = "rocker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"rocker"})
	//@LazyCollection(LazyCollectionOption.FALSE)
	private List<Replys> reply;

}
