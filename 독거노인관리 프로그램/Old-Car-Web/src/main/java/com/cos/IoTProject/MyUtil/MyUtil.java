package com.cos.IoTProject.MyUtil;

import org.springframework.beans.factory.annotation.Autowired;

import com.cos.IoTProject.model.RaspiSensor;
import com.google.gson.Gson;

public class MyUtil {
	@Autowired
	private static Gson gson;
	@Autowired
	private static RaspiSensor raspisensor;
	
	
	public static void RaspiSensorIn(String sensorData) {
		raspisensor = gson.fromJson(sensorData, RaspiSensor.class);
	}
	
	public static String getResourcePath() {
		return "E://spring_instagrem//IoTProject//src//main//resources//static//images//photoId//";
	}

}
