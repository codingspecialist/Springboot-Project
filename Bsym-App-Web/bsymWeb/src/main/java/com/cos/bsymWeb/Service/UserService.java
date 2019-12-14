package com.cos.bsymWeb.Service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.UsersRepository;

@Service
public class UserService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private RockerRepository rockerRepository;
	
	public String userCreate(Users user) {
		usersRepository.save(user);
		Rocker rocker = Rocker.builder()
				.user(user)
				.build();
		rockerRepository.save(rocker);
		return "ok";
	}

	public Users userLoginFind(Users user) {
		return usersRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
	
	public Optional<Rocker> findIdMypage(int Id) {
		return rockerRepository.findById(Id);
	}
	
	public Users apiLoginFind(String username) {
		return usersRepository.findByUsername(username);
	}
	
}
