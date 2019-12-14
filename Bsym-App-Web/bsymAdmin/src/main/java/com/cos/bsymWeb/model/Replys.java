package com.cos.bsymWeb.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Replys {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@CreationTimestamp
	private LocalDate createDate;
	private String context;
	
	@ManyToOne
	@JoinColumn(name = "rockerId")
	@JsonIgnoreProperties({"like","share","reply"})
	private Rocker rocker;
	
	@ManyToOne
	@JoinColumn(name = "brcId")
	@JsonBackReference
	private BoardReplyCon brc;
}
