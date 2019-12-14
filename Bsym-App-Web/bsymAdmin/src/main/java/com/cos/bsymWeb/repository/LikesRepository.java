package com.cos.bsymWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.bsymWeb.model.Likes;
import com.cos.bsymWeb.model.Users;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
	@Modifying
	@Transactional
	@Query(value="delete from likes where rockerId = ?1 and bsymBoardId = ?2", nativeQuery=true)
	public void deleteLike(int RockerId, int BsymBoardId);
	@Query(value="select * from likes where rockerId = ?", nativeQuery=true)
	public List<Likes> findByRockerId(int RockerId);
}
