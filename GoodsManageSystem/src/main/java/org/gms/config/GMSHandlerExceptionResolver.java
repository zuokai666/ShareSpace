package org.gms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理未捕获异常，统一返回
 * 
 * @author King
 *
 */
@Component
public class GMSHandlerExceptionResolver implements HandlerExceptionResolver{
	
	private static final Logger log = LoggerFactory.getLogger(GMSHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		log.info("GMS处理未捕获异常:[{}]，统一返回", ex.getMessage());
		return null;
	}
}