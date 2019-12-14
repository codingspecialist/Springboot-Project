package com.cos.bsymWeb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.bsymWeb.Service.CustomUserDetails;
import com.cos.bsymWeb.Service.LikeService;
import com.cos.bsymWeb.model.BlogLogin;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Likes;
import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.BsymBoardRepository;
import com.cos.bsymWeb.repository.LikesRepository;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.UsersRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping("/bsym")
public class LikeController {
	@Autowired
	private RockerRepository rockerRepository;
	@Autowired
	private LikesRepository likesRepository;
	@Autowired
	private BsymBoardRepository bsymBoardRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	//ajax용
	@PostMapping("/like/{id}")
	public @ResponseBody BlogLogin like(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails userDetail) {
		BlogLogin BL = new BlogLogin();
		BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(id)).get();
		if(userDetail!=null) {
			Users user = userDetail.getUser();
			System.out.println("유저"+user);
			System.out.println("게시물아이디"+id);
			Rocker rocker = rockerRepository.findById(user.getId()).get();
			System.out.println("해당게시물"+bsymBoard);
			Likes like = Likes.builder()
					.bsymBoard(bsymBoard)
					.rocker(rocker)
					.build();	
			likesRepository.save(like);
			bsymBoard.setLikeCount(likesRepository.findByBsymBoardId(Integer.parseInt(id)));
			bsymBoardRepository.save(bsymBoard);
			BL.setLoginCheck("ok");
			BL.setBsymBoard(bsymBoard);
			return BL;
		}else {
			BL.setLoginCheck("error");
			BL.setBsymBoard(bsymBoard);
			return BL;
		}
	}
	
	@PostMapping("/unlike/{id}")
	public @ResponseBody BlogLogin unlike(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails userDetail) {
		BlogLogin BL = new BlogLogin();
		BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(id)).get();
		if(userDetail!=null) {
			Users user = userDetail.getUser();
			System.out.println("유저"+user);
			Rocker rocker = rockerRepository.findById(user.getId()).get();
			System.out.println("아이디"+id);
			System.out.println("해당게시물"+bsymBoard);
			likesRepository.deleteLike(rocker.getId(),bsymBoard.getId());
			bsymBoard.setLikeCount(likesRepository.findByBsymBoardId(Integer.parseInt(id)));
			bsymBoardRepository.save(bsymBoard);
			BL.setLoginCheck("ok");
			BL.setBsymBoard(bsymBoard);
			return BL;
		}else {
			BL.setLoginCheck("error");
			BL.setBsymBoard(bsymBoard);
			return BL;
		}
	}
	
	@PostMapping("/app/like")
	public void appLike(String username, String boardid) {
		System.out.println("===================like App버튼호출 완료=====================");
		System.out.println("호출한 아이디 : "+username+"   "+"좋아요한글 : "+boardid);
		BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(boardid)).get();
		Users user = usersRepository.findByUsername(username);
		System.out.println("유저"+user);
		System.out.println("게시물아이디"+boardid);
		Rocker rocker = rockerRepository.findById(user.getId()).get();
		System.out.println("해당게시물"+bsymBoard);
		Likes like = Likes.builder()
				.bsymBoard(bsymBoard)
				.rocker(rocker)
				.build();	
		likesRepository.save(like);
		bsymBoard.setLikeCount(likesRepository.findByBsymBoardId(Integer.parseInt(boardid)));
		bsymBoardRepository.save(bsymBoard);
	}
	@PostMapping("/app/unlike")
	public void appUnLike(String username, String boardid) {
		System.out.println("===================unlike App버튼호출 완료=====================");
		System.out.println("호출한 아이디 : "+username+"   "+"좋아요한글 : "+boardid);
		BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(boardid)).get();
		Users user = usersRepository.findByUsername(username);
		System.out.println("유저"+user);
		Rocker rocker = rockerRepository.findById(user.getId()).get();
		System.out.println("아이디"+boardid);
		System.out.println("해당게시물"+bsymBoard);
		likesRepository.deleteLike(rocker.getId(),bsymBoard.getId());
		bsymBoard.setLikeCount(likesRepository.findByBsymBoardId(Integer.parseInt(boardid)));
		bsymBoardRepository.save(bsymBoard);
	}

}
