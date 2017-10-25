package edu.mum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.mum.repository.UserRepository;
import edu.mum.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableWebSecurity
@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService userdetailsService;

	@Autowired
	UrlAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		           .csrf().disable()
		           .authorizeRequests()
		           .antMatchers("/faculty/**").hasAuthority("ROLE_Faculty")
		           .antMatchers("/admin/**").hasAuthority("ROLE_Admin")
		           .antMatchers("/student/**").hasAuthority("ROLE_Student")
		           .antMatchers("/home").authenticated()
	               .anyRequest().authenticated()
		           .and().formLogin().successHandler(successHandler).and().exceptionHandling().accessDeniedPage("/403");
		   	     	
	
		

	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsService).passwordEncoder(getPasswordEncoder());

	}

	private PasswordEncoder getPasswordEncoder() {
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

}