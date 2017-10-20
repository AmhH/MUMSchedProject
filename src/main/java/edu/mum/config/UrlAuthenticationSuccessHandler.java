package edu.mum.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class UrlAuthenticationSuccessHandler
  implements AuthenticationSuccessHandler {

	
  
protected Log logger = LogFactory.getLog(this.getClass());

@Override
public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
		throws IOException, ServletException {
	// TODO Auto-generated method stub
	
}
}

   // private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

   /* @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
       System.out.println("Success");
    	handle(request, response, authentication);
        clearAuthenticationAttributes(request);
       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
       for (GrantedAuthority grantedAuthority : authorities) {
             if (grantedAuthority.getAuthority().equals("ROLE_Faculty")) {
                 response.sendRedirect("/faculty/home");
                 return;
             } else if (grantedAuthority.getAuthority().equals("ROLE_Admin")) {
                response.sendRedirect("/adminhome");
                return;
             }
         }
    }
}*/

   /* protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        System.out.println("Handler");
        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
        System.out.println("redirect");
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isFaculty = false;
        boolean isAdmin = false;
        boolean isStudent = false;
    
         Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_Faculty")) {
            	isFaculty = true;
            	System.out.println("isfaculty :"+isFaculty);
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_Admin")) {
            	isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_Student")) {
            	isStudent = true;
                break;
            } 
        	Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                  if (grantedAuthority.getAuthority().equals("ROLE_Faculty")) {
                      response.sendRedirect("/faculty/home");
                      return;
                  } else if (grantedAuthority.getAuthority().equals("ROLE_Admin")) {
                     response.sendRedirect("/adminhome");
                     return;
                  }
              }
        }

        if (isFaculty) {
        	System.out.println("this is faculty home page");
            return "facultyhome.html";
        } else if (isAdmin) {
            return "adminhome.html";
        } else if (isStudent) {
            return "";
      
        } else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }*/
