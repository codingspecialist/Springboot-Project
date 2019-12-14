package com.cos.bsymWeb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Builder
public class imgtest {
	private String imglink;
}
