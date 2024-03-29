package com.mvc.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class CommonInterceptor extends HandlerInterceptorAdapter{
	 @Override    
	    public boolean preHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler) throws Exception {
//		 String user=(String) request.getSession().getAttribute("user");
//		 if(user!=""&&user!=null){
//			 return true;
//		 }
//		 response.sendRedirect("/HelloSpringMVC/login");
		 return false;
	    }    
	 
	 /** 
	     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
	     * 可在modelAndView中加入数据，比如当前时间 
	     */  
	    @Override    
	    public void postHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler,    
	            ModelAndView modelAndView) throws Exception {     
	    }    
	    
	    /**  
	     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
	     *   
	     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
	     */    
	    @Override    
	    public void afterCompletion(HttpServletRequest request,    
	            HttpServletResponse response, Object handler, Exception ex)    
	            throws Exception {    
	    }    
}
