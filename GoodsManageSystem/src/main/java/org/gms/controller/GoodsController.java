package org.gms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/goods")
public class GoodsController {
	
	@SuppressWarnings("unused")
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GoodsController.class);
	
	@RequestMapping(value="/manage")
	public ModelAndView manage(){
		return new ModelAndView("goods/manage");
	}
}