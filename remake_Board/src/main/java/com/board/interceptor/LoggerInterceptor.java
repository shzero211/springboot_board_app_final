package com.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	//URI가 들어가기전에  잡아주는 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("==========================");
		logger.debug("=============BEGIN==================");
		logger.debug("Request URI======>"+request.getRequestURL());
		return super.preHandle(request, response, handler);
	}
	//URI 가 들어온후 잡아주는 인터셉터
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("==================== END ======================");
		logger.debug("===============================================");
	}

}
