package org.gms.controller;

import org.gms.dto.GoodsTypeDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 物品类型管理接口控制器
 * 
 * http://localhost:9999/gms/goodsType/manage
 * http://localhost:9999/gms/html/goodsType/manage.html
 * http://localhost:9999/gms/js/jquery.min.js
 * 
 * @author King
 * 
 */
@RestController
@RequestMapping(value="/goodsType",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
public class GoodsTypeController {
	
	@RequestMapping(value="/manage")
	public ModelAndView manage(){
		return new ModelAndView("goodsType/manage");
	}
	
	@RequestMapping(value="/add")
	public String add(GoodsTypeDto goodsType){
		return null;
	}
}