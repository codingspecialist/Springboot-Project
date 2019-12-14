package com.cos.IoTProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IoTProject.model.GasDetect;
import com.cos.IoTProject.model.Management;

public interface GasDetectRepository extends JpaRepository<GasDetect, Integer>{
	public GasDetect findByManagement(Management management);
}
