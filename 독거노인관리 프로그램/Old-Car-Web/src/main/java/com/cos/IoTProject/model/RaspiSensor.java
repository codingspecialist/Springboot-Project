package com.cos.IoTProject.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
public class RaspiSensor {
	private String ip;
	private Management management;
	private int temp;
	private int humi;
	private int gasdetect;
	private int humandetect;
}
