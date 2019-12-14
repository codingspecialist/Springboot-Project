package com.cos.bsymWeb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Likes;
import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.BsymBoardRepository;
import com.cos.bsymWeb.repository.LikesRepository;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.UsersRepository;
import com.google.gson.Gson;

@Service
public class BlogService {
	@Autowired
	private BsymBoardRepository bsymBoardRepository;
	@Autowired
	private RockerRepository rockerRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private LikesRepository likesRepository;
	@Autowired
	private Gson gson;
	
	
	public Page<BsymBoard> bloglistPage(String div, String broadcastName, Pageable pageable){
		System.out.println("검색구분"+div);
		System.out.println("검색방송네임"+broadcastName);
		if(div.equals("cook")&&broadcastName==null){
			broadcastName="스페인하숙";
		}
		else if(div.equals("res")&&broadcastName==null) {
			broadcastName="미식클럽";
		}
		Page<BsymBoard> list = bsymBoardRepository.findByBroadcastName( broadcastName, pageable);
		return list;
	}
	
	public Page<BsymBoard> bloglistPage(CustomUserDetails userDetail, String div, String broadcastName, Pageable pageable){
		System.out.println("검색구분"+div);
		System.out.println("검색방송네임"+broadcastName);
		Page<BsymBoard> page = bsymBoardRepository.findByBroadcastName(broadcastName, pageable);
		List<BsymBoard> list = page.getContent();
		if(div.equals("cook")&&broadcastName==null){
			broadcastName="스페인하숙";
		}
		else if(div.equals("res")&&broadcastName==null) {
			broadcastName="미식클럽";
		}
		if(userDetail!=null) {
			int userId = userDetail.getUser().getId();
			System.out.println("로그인유저 ID : "+userId);
			Optional<Rocker> rocker = rockerRepository.findById(userId);
			List<Likes> like = rocker.get().getLike();
			//System.out.println(like.get(1));
			for(int i=0;i<list.size();i++) {
				BsymBoard bsymboard = list.get(i);
				for(int j=0; j<like.size(); j++) {
					if(bsymboard.getId()==like.get(j).getBsymBoard().getId()) {
						bsymboard.setLikeCheck("true");
						break;
					}else {
						bsymboard.setLikeCheck("false");
					}
				}
				System.out.println("로그인 했을때 likeCheck확인 : "+list.get(i).getLikeCheck());
				//bsymboard.setLikeCount(likesRepository.findByBsymBoardId(bsymboard.getId()));
			}
			return page;
		}else {
			for(int i=0;i<list.size();i++) {
				//BsymBoard bsymboard = list.get(i);
				list.get(i).setLikeCheck("false");
				System.out.println("likeCheck확인 : "+list.get(i).getLikeCheck());
				//bsymboard.setLikeCount(likesRepository.findByBsymBoardId(bsymboard.getId()));
			}
			return page;
		}
	}
	
	public List<BsymBoard> Appbloglist(String div, String broadcastName, String username){
		System.out.println("검색구분"+div);
		System.out.println("검색방송네임"+broadcastName);
		List<BsymBoard> list = bsymBoardRepository.findByBroadcastName(broadcastName);
		if(div.equals("cook")&&broadcastName==null){
			broadcastName="스페인하숙";
		}
		else if(div.equals("res")&&broadcastName==null) {
			broadcastName="미식클럽";
		}
		if(!username.equals("로그인 해주세요")) {
			System.out.println("로그인유저 ID : "+username);
			Users user = usersRepository.findByUsername(username);
			Optional<Rocker> rocker = rockerRepository.findById(user.getId());
			List<Likes> like = rocker.get().getLike();
			for(int i=0;i<list.size();i++) {
				for(int j=0; j<like.size(); j++) {
					if(list.get(i).getId()==like.get(j).getBsymBoard().getId()) {
						list.get(i).setLikeCheck("true");
						break;
					}else {
						list.get(i).setLikeCheck("false");
					}
				}
				System.out.println("로그인 했을때 likeCheck확인 : "+list.get(i).getLikeCheck());
			}
			return list;
		}else {
			for(int i=0;i<list.size();i++) {
				list.get(i).setLikeCheck("false");
				System.out.println("likeCheck확인 : "+list.get(i).getLikeCheck());
			}
			return list;
		}
	}
	public List<BsymBoard> blogfindAll(String broadcastName) {
		System.out.println(broadcastName);
		return bsymBoardRepository.findByBroadcastName(broadcastName);
	}



	public Optional<BsymBoard> findId(int id) {
		return bsymBoardRepository.findById(id);
	}
	
	public BsymBoard updata(BsymBoard bsymBoard) {
		bsymBoard.setReadCount(bsymBoard.getReadCount()+1);
		return bsymBoardRepository.save(bsymBoard);
	}



//	public Page<BsymBoard> allPage(@PageableDefault(size = 8) Pageable pageable) {
//		
//		return bsymBoardRepository.findAll(pageable);
//	}
}
