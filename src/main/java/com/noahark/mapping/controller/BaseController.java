package com.noahark.mapping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	protected Logger logger;
	
	private static String USER_SPLIT = "@";
	
	public BaseController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	
	@ExceptionHandler
    public String exp(HttpServletRequest request, Exception ex) {
        String resultViewName = "error/error";

        // 记录日志
        logger.error(ex.getMessage(), ex);

        // 根据不同错误转向不同页面
        /*if (ex instanceof BusinessException) {
            resultViewName = "errors/biz-error";
        } else {
            // 异常转换
            ex = new Exception("系统太累了，需要休息!");
        }*/
        
        request.setAttribute("ex", ex);
        return resultViewName;
    }
	
	protected void flush(HttpServletResponse response, Object obj){
		PrintWriter out = null;
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("",e);
		}
		
        out.write(obj.toString());
        out.flush();
	}
	
	
	protected String getUserName(String user){
		
		if (user == null){
			return null;
		}
		
		if (user.indexOf(USER_SPLIT) >= 0 ){
			return user.substring(0, user.indexOf(USER_SPLIT)).toLowerCase();
		} else {
			return user.toLowerCase();
		}
	}

}
