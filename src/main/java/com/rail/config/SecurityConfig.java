package com.rail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("railway")).roles("ADMIN").and().withUser("user").password(passwordEncoder().encode("railway")).roles("USER");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	
	
		
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/**").hasAnyRole("USER","ADMIN").antMatchers("/**").hasRole("ADMIN")
		.anyRequest().authenticated().and().httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().csrf().disable();
	
	
	
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
