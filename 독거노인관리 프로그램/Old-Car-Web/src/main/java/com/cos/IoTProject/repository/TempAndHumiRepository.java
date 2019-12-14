package com.cos.IoTProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IoTProject.model.TempAndHumi;

public interface TempAndHumiRepository extends JpaRepository<TempAndHumi, Integer>{

}
