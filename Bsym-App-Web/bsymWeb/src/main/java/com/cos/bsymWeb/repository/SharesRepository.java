package com.cos.bsymWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.bsymWeb.model.Shares;

public interface SharesRepository extends JpaRepository<Shares, Integer>{
	@Modifying
	@Transactional
	@Query(value="delete from shares where rockerId = ?1 and bsymBoardId = ?2", nativeQuery=true)
	public void deleteShare(int RockerId, int BsymBoardId);
}
