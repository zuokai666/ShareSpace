package org.gms.controller;

import java.util.List;

import org.gms.dto.GoodsTypeDto;
import org.gms.service.GoodsTypeService;
import org.gms.util.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 物品类型管理接口控制器
 * 
 * @author King
 * 
 */
@RestController
@RequestMapping(value="/goodsType",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
public class GoodsTypeController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GoodsTypeController.class);
	
	@Autowired
	private GoodsTypeService goodsTypeService;
	
	@RequestMapping(value="/manage")
	public ModelAndView manage(){
		return new ModelAndView("goodsType/manage");
	}
	
	@RequestMapping(value="/listAll")
	public JsonNode listAll(){
		try {
			List<GoodsTypeDto> dtos = goodsTypeService.selectAll();
			return new HttpBody(true, "操作成功", dtos).toResult();
		} catch (Exception e) {
			log.error("", e);
			return new HttpBody(false, "操作失败:"+e.getMessage()).toResult();
		}
	}
	
	@RequestMapping(value="/add")
	public String add(GoodsTypeDto goodsType){
		return null;
	}
	
	@RequestMapping(value="/update")
	public JsonNode update(GoodsTypeDto goodsType){
		try {
			int num = goodsTypeService.updateGoodsType(goodsType);
			if(num == 1){
				return new HttpBody(true, "操作成功", "").toResult();
			}else {
				return new HttpBody(false, "操作失败:当前数据已被其它用户删除或修改,请重试").toResult();
			}
		} catch (Exception e) {
			log.error("", e);
			return new HttpBody(false, "操作失败:"+e.getMessage()).toResult();
		}
	}
	
	@RequestMapping(value="/delete")
	public JsonNode delete(GoodsTypeDto goodsType){
		try {
			int num = goodsTypeService.updateGoodsType(goodsType);
			if(num == 1){
				return new HttpBody(true, "操作成功", "").toResult();
			}else {
				return new HttpBody(false, "操作失败:当前数据已被其它用户删除或修改,请重试").toResult();
			}
		} catch (Exception e) {
			log.error("", e);
			return new HttpBody(false, "操作失败:"+e.getMessage()).toResult();
		}
	}
}