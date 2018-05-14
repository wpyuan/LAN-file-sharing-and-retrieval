package com.gxu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxu.entity.Account;

/**
 * ��֤�Ƿ��¼������
 * @ʱ�� 2018/2/2 09��57�� 
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
		System.out.println("---����---");
		//��ȡip
		String visitorIp= (String) request.getSession().getAttribute("visitorIp");
		System.out.println("visitorIp��"+visitorIp);
		System.out.println("------------");
		//�ж��ʻ��Ƿ�Ϊ��
		if(visitorIp!=null){
			return true;
		}
		//��ת����¼ҳ��
		//request.getRequestDispatcher("/index.jsp").forward(request,response);
		response.sendRedirect("toLogin.do");
		return false;
	}

}
