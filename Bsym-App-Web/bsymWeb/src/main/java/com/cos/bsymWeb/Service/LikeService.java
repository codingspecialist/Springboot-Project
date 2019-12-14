package com.cos.bsymWeb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.bsymWeb.repository.LikesRepository;

@Service
public class LikeService {
	@Autowired
	private LikesRepository likesRepository;
	

}
