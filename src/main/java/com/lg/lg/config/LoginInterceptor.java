package com.lg.lg.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther:
 * @Date:
 * @Description: 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (handler instanceof HandlerMethod && session.getAttribute("user") == null) {
            request.setAttribute("message", "请先登录");
            //request.getRequestDispatcher("/main").forward(request, response);
            request.getRequestDispatcher("/login/toLogin").forward(request,response);
          //  response.sendRedirect("/login/toLogin");
            return false;
        }
        return true;
    }


}
