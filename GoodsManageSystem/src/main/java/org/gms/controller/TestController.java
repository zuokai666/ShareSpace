package org.gms.controller;

import org.gms.dto.GoodsTypeDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
@SuppressWarnings("unused")
public class TestController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value="/a")
	public String a(){
		log.info("a1");
		int i = 1/0;
		log.info("a2");
		return "a";
	}
	
	@RequestMapping(value="/b")
	public String b(){
		log.info("b");
		return "b";
	}
}