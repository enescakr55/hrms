package project.hrms.business.services.config;

import java.util.List;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.hrms.business.services.CustomUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JwtFilter jwtFilter;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String[] antMatcherList = {
    	"/api/auth/login",
    	"/api/jobadverts/getall",
    	"/api/jobseekers/getall",
    	"/api/jobadverts/getisactive",
    	"/api/cities/getall",
    	"/api/employers/getall",
    	"/api/jobseekers/registerjobseeker",
    	"/swagger-ui.html",
    	"/api/mail/send",
    	"/api/mail/verify"

    			
    	};
    	//http.authorizeRequests().antMatchers("").access("hasRole('Manager')");
    	http.cors();
    	//antMatchers(antMatcherList)
        http.csrf().disable()
                .authorizeRequests().antMatchers("**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	// return NoOpPasswordEncoder.getInstance();
        DelegatingPasswordEncoder delPasswordEncoder=  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bcryptPasswordEncoder =new BCryptPasswordEncoder();
    delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
    return delPasswordEncoder;   
    }
}