package com.cos.bsymWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.bsymWeb.Service.CustomUserDetails;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Shares;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.BsymBoardRepository;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.SharesRepository;

@Controller
@RequestMapping("/bsym")
public class ShareController {
	@Autowired
	private RockerRepository rockerRepository;
	@Autowired
	private SharesRepository sharesRepository;
	@Autowired
	private BsymBoardRepository bsymBoardRepository;
	
	@PostMapping("/share/{id}")
	public @ResponseBody String share(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails userDetail) {
		if(userDetail!=null) {
			Users user = userDetail.getUser();
			System.out.println("유저"+user);
			Rocker rocker = rockerRepository.findById(user.getId()).get();
			List<Shares> shares = rocker.getShare();
			BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(id)).get();
			for(int i=0; i<shares.size(); i++) {
				if(bsymBoard.getId()==shares.get(i).getBsymBoard().getId()) {
					return "overlap";
				}
			}
			bsymBoard.setShareCount(bsymBoard.getShareCount()+1);
			bsymBoardRepository.save(bsymBoard);
			System.out.println("아이디"+id);
			System.out.println("해당게시물"+bsymBoard);
			Shares share = Shares.builder()
					.bsymBoard(bsymBoard)
					.rocker(rocker)
					.build();	
			sharesRepository.save(share);
			return "ok";
		}else {
			return "error";
		}
	}
	@PostMapping("/shareDel/{id}")
	public @ResponseBody String shareDel(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails userDetail) {
		if(userDetail!=null) {
			Users user = userDetail.getUser();
			System.out.println("유저"+user);
			Rocker rocker = rockerRepository.findById(user.getId()).get();
			BsymBoard bsymBoard = bsymBoardRepository.findById(Integer.parseInt(id)).get();
			bsymBoard.setShareCount(bsymBoard.getShareCount()+1);
			bsymBoardRepository.save(bsymBoard);
			System.out.println("아이디"+id);
			System.out.println("해당게시물"+bsymBoard);
			sharesRepository.deleteShare(rocker.getId(),bsymBoard.getId());
			return "ok";
		}else {
			return "error";
		}
	}
}
