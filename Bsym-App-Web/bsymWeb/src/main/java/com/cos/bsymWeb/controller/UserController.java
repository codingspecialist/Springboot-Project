package com.cos.bsymWeb.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.cos.bsymWeb.MyUtil.UtillCommon;
import com.cos.bsymWeb.Service.CustomUserDetails;
import com.cos.bsymWeb.Service.UserService;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Likes;
import com.cos.bsymWeb.model.LoginState;
import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.UsersRepository;

@Controller
@RequestMapping("/bsym")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/auth/login")
	public String authLogin() {
		return "/auth/login";
	}
	
	
	@GetMapping("/auth/join")
	public String authJoin() {
		return "/auth/join";
	}
	
	
	@PostMapping("/auth/joinProcess")
	public String joinProc(Users user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userService.userCreate(user);
		return "/auth/login";
	}
	
	@GetMapping("api/loginProcess")
	public @ResponseBody LoginState apiLoginProc(String username, String password) {
		Users findUser = userService.apiLoginFind(username);
		LoginState ls = new LoginState();
		if(findUser!=null) {
			System.out.println("로그인 유저 정보:"+findUser);
			System.out.println("로그인한 유저 원시패스워드:"+password);
			System.out.println("DB 엔코드 패스워드 :"+findUser.getPassword());
			Boolean passCheck = passwordEncoder.matches(password, findUser.getPassword());
			System.out.println("매칭 확인:"+passCheck);
			if(passCheck) {
				ls.setResult("user login success");
			}else{
				ls.setResult("user login false");
			}
		}else {
			ls.setResult("user not found");
		}
		return ls;
	}
	
	
	@PostMapping("/api/joinProcess")
	public void apiJoinProc(Users user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userService.userCreate(user);
	}
	
	@GetMapping("/web/myPage")
	public String webMyPage(@AuthenticationPrincipal CustomUserDetails userDetail, Model model){
		if(userDetail!=null) {
			model.addAttribute("mypage", userService.findIdMypage(userDetail.getUser().getId()).get());
			model.addAttribute("loginUser",userDetail.getUser());
			return "mypage";
		} else{
			return "/auth/login";
		}
		
	}
	
	@GetMapping("/app/myPage/like")
	public @ResponseBody List<BsymBoard> AppMyPage(String username) {
		System.out.println("좋아요 보관함 호출한 아이디 : "+username);
		Users user = userService.apiLoginFind(username);
		List<Likes> list = userService.findIdMypage(user.getId()).get().getLike();
		List<BsymBoard> result = new ArrayList<BsymBoard>();
		for(int i=0;i<list.size();i++) {
			result.add(list.get(i).getBsymBoard());
		}
		return result;
	}

}
