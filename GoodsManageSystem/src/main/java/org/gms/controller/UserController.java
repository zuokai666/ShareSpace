package org.gms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@SuppressWarnings("unused")
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/login")
	public ModelAndView login(){
		return new ModelAndView("user/login");
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		return new ModelAndView("user/index");
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about(){
		return new ModelAndView("user/about");
	}
}