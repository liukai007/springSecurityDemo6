package com.lk.demo5.other;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Mobile1AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private String forwardUrl;

    public Mobile1AuthenticationSuccessHandler(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        httpServletRequest.getRequestDispatcher(forwardUrl).forward(httpServletRequest, httpServletResponse);
        httpServletResponse.sendRedirect(forwardUrl);
//        httpServletRequest.getRequestDispatcher(forwardUrl).forward(httpServletRequest, httpServletResponse);
    }
}
