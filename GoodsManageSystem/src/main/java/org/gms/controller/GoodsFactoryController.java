package org.gms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value="/goodsFactory")
public class GoodsFactoryController {
	
	@SuppressWarnings("unused")
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GoodsFactoryController.class);
	
	@RequestMapping(value="/manage")
	public ModelAndView manage(){
		return new ModelAndView("goodsFactory/manage");
	}
}