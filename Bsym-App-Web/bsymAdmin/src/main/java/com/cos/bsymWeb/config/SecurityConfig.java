package com.cos.bsymWeb.config;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		String text = URLEncoder.encode("스페인하숙", "UTF-8");
		String successUrl = "/admin/page";
		http.csrf().disable();
		http.cors().disable();
		http.authorizeRequests()
			.antMatchers("/")
			.authenticated()
			.anyRequest()
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/admin/auth/login")
			.permitAll()
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl(successUrl)
			.and()
			.logout()
			.logoutSuccessUrl("/admin/auth/login");
				//.antMatchers("/user/**", "/follow/**", "/images/**")
//				.antMatchers("/follow/**")
//				.authenticated()
//				.anyRequest().permitAll()
//				.and()
//				.formLogin()
//				.loginPage("/auth/login")
//				.loginProcessingUrl("/auth/loginProc")
//				.defaultSuccessUrl("/images");
	}
	
	
}
