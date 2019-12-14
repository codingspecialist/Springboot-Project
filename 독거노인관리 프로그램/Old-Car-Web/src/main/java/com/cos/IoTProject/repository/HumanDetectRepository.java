package com.cos.IoTProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IoTProject.model.HumanDetect;
import com.cos.IoTProject.model.Management;

public interface HumanDetectRepository extends JpaRepository<HumanDetect, Integer>{
	public List<HumanDetect> findByManagement(Management management);
}
