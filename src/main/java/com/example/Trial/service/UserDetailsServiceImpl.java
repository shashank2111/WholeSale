package com.example.Trial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Trial.model.User;
import com.example.Trial.repository.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDaoImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userDaoImpl.findByEmail(username);
		
		List<GrantedAuthority> grantList = new ArrayList<>();
        String role = user.getRoletitle();
//        for(int i=0;i<roles.length;i++){
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
//        }
           System.out.println(user.toString());
           System.out.println(grantList.toString());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),grantList
        );
		
//		return UserDetailsImpl(user);
	}
	
	public void saveUser(User user) {
		userDaoImpl.save(user);
	}

}
