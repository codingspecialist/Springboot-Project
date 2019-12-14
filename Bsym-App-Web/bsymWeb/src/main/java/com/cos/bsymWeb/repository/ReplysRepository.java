package com.cos.bsymWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.bsymWeb.model.Replys;

public interface ReplysRepository extends JpaRepository<Replys, Integer> {


	//public List<Replys> findByBsymBoard(int bsymboard);
	//public List<Replys> findByBsymBoardId(int bsymBoardId);
}
