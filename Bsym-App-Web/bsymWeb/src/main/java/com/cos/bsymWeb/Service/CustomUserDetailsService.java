package com.cos.bsymWeb.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("들어온유저네임"+username);
		Users user = userRepository.findByUsername(username);
		
		CustomUserDetails userDetails = null;
		if(user != null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
			System.out.println("로그인유저확인"+userDetails.getUser());
		}else {
			throw new UsernameNotFoundException("유저를 찾을 수 없습니다. "+username);
		}
		return userDetails;
	}
}
