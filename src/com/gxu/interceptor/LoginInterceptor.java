package com.gxu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxu.entity.Account;

/**
 * 验证是否登录拦截器
 * @时间 2018/2/2 09点57分 
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		System.out.println("---拦截---");
		//获取ip
		String visitorIp= (String) request.getSession().getAttribute("visitorIp");
		System.out.println("visitorIp："+visitorIp);
		System.out.println("------------");
		//判断帐户是否为空
		if(visitorIp!=null){
			return true;
		}
		//跳转到登录页面
		//request.getRequestDispatcher("/index.jsp").forward(request,response);
		response.sendRedirect("toLogin.do");
		return false;
	}

}
