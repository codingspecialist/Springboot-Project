package com.cos.IoTProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.IoTProject.model.Management;
import com.cos.IoTProject.model.Users;

public interface ManagementRepository extends JpaRepository<Management, Integer>{
	public List<Management> findByUser(Users user);
	
	@Query(value="select * from management  where userid = ? order by id DESC limit 1 ;", nativeQuery=true)
	public Management findByLastmanagement(int userid);

	@Query(value="Select * From management Where createDate In(Select Max(createDate) From management Group By userid)", nativeQuery=true)
	public Page<Management> findByOldmanlist(Pageable pageable);
	
	@Query(value="select * from management where Id in(select manageId from gasdetect where gascheck = 'true') Group By userid", nativeQuery=true)
	public Page<Management> findByGasWarniglist(Pageable pageable);
	
	@Query(value="select * from management where Id in(select manageId from tempandhumi where temp not between 20 and 33) Group By userid", nativeQuery=true)
	public Page<Management> findByTempWarniglist(Pageable pageable);
	
}
