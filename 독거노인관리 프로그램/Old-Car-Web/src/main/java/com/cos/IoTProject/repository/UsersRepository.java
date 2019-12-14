package com.cos.IoTProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.IoTProject.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Users findByIpaddress(String ipaddress);
	@Query(value="select MAX(id) from users", nativeQuery=true)
	public int findMaxid();
}
