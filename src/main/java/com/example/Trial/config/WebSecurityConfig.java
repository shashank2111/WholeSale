package com.example.Trial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.Trial.service.UserDetailsServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Bean
    BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////		auth.inMemoryAuthentication().withUser("shashank").password(this.passwordEncoder().encode("nana")).roles("NORMAL");
////		auth.inMemoryAuthentication().withUser("ashish").password(this.passwordEncoder().encode("mummy")).roles("ADMIN");
//	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception{
	    return authenticationManager();
	}
	@Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleAuthenticationSuccessHandler();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/","/login","/logout").permitAll();
//        http.authorizeRequests().antMatchers("/self","/self/**").access("hasAuthority('client')");
//        http.authorizeRequests().antMatchers("/admin","/admin/**").access("hasAuthority('admin')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//        http.authorizeRequests().and().formLogin().loginPage("/login")
//                .defaultSuccessUrl("/welcome",true).failureUrl("/login?error=true").usernameParameter("email")
//                .passwordParameter("password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login","/logout","/register").permitAll()
			.antMatchers("/").access("hasAuthority('admin')")
			.anyRequest()
			.authenticated()
			.and() 
			.formLogin()
			.loginPage("/login")
			.successHandler(myAuthenticationSuccessHandler())
			.failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/logout").logoutSuccessUrl("/"); 
    }
	
}
