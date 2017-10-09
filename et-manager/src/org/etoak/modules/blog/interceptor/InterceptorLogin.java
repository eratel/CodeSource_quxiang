package org.etoak.modules.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.etoak.modules.blog.pojo.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorLogin implements HandlerInterceptor
{

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
        throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
        throws Exception
    {
        String  token = (String)request.getSession().getAttribute("token");  
        String requestURI = request.getRequestURI();  
        String loginUrl = "/login.html";  
        String uri = requestURI.substring(requestURI.lastIndexOf("/"));  
        if (token == null) {  
            if (uri.startsWith("/login")) {  
                return true;  
            } else {  
                // 非法请求 重定向到登录页面  
                response.sendRedirect(request.getContextPath() + loginUrl);  
                return false;  
            }  
        } else { 
            response.sendRedirect(request.getContextPath() + loginUrl);  
            return false;  
        } 
    }
}

















