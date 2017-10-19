package edu.mum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.mum.repository.UserRepository;
import edu.mum.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@EnableWebSecurity
@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
  
	@Autowired
    CustomUserDetailsService userdetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	           	http
	        	.csrf().disable()
			           	     .authorizeRequests()
			                // .antMatchers("/**").authenticated()
			           	     .anyRequest().permitAll()
			           	     .and().formLogin().successHandler(new UrlAuthenticationSuccessHandler()).permitAll();
	          
	 }
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth   
		    .userDetailsService(userdetailsService)
		    .passwordEncoder(getPasswordEncoder());
		    
	}
	
	
	private PasswordEncoder getPasswordEncoder()
	{
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
			
			@Override
			public String encode(CharSequence charSequence) {
				System.out.println("Security");
			      return charSequence.toString();
			}
		};
   }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
}