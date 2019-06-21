package org.gms.service.impl;

import java.util.List;

import org.gms.dao.GoodsTypeDao;
import org.gms.dto.GoodsTypeDto;
import org.gms.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService{
	
	@Autowired
	private GoodsTypeDao goodsTypeDao;
	
	@Override
	public int insertGoodsType(GoodsTypeDto goodsType) {
		return goodsTypeDao.insertGoodsType(goodsType);
	}

	@Override
	public int updateGoodsType(GoodsTypeDto goodsType) {
		return goodsTypeDao.updateGoodsType(goodsType);
	}

	@Override
	public int deleteGoodsType(GoodsTypeDto goodsType) {
		return goodsTypeDao.deleteGoodsType(goodsType);
	}

	@Override
	public List<GoodsTypeDto> selectAll() {
		return goodsTypeDao.selectAll();
	}
}