package com.atiqur_rahman.login_example.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by osama on 10/30/2016.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.preHandle(request, response, handler);

        HttpSession objHttpSession = request.getSession(true);


        Object userObj = objHttpSession.getAttribute("user");


            if (!request.getRequestURI().endsWith("login")&&!request.getRequestURI().endsWith("register")) {
            if (userObj == null ) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

}
