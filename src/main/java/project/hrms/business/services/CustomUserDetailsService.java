package project.hrms.business.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
    	User authUser = new User(user.getEmail(),user.getPassword(),new ArrayList<GrantedAuthority>());
    	return authUser;
    }
}
