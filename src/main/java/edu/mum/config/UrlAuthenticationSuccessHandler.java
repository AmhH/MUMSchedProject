package edu.mum.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httrequest, HttpServletResponse httpResponse, Authentication authentication)
			throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority -> {
			if(authority.getAuthority().equals("ROLE_Faculty")) {
				try {
					redirectStrategy.sendRedirect(httrequest, httpResponse, "/Faculty/home");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(authority.getAuthority().equals("ROLE_Admin")) {
				try {
					redirectStrategy.sendRedirect(httrequest, httpResponse, "/Faculty/all");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
	            throw new IllegalStateException();
	        }
		});
		
	}
 
	
}