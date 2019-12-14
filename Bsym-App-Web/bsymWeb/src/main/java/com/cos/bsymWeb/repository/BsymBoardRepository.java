package com.cos.bsymWeb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;

import com.cos.bsymWeb.model.BsymBoard;

public interface BsymBoardRepository extends JpaRepository<BsymBoard, Integer > {
	public Page<BsymBoard> findByBroadcastName(String broadcastName, Pageable pageable);
	public List<BsymBoard> findByBroadcastName(String broadcastName);
}
