package project.hrms.business.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.UserService;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserService userService;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	project.hrms.entities.concretes.User user = userService.getByEmail(username).getData();
    	ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    	if(username.equals("enescakr01@hotmail.com")) {
    		authorities.add(new SimpleGrantedAuthority("ROLE_"+"Manager"));
    	}
    	
    	User authUser = new User(user.getEmail(),user.getPassword(),authorities);
    	return authUser;
    }
}
