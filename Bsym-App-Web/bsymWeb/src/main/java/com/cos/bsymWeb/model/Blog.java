package com.cos.bsymWeb.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Blog {
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<BsymBoard> items;
}
