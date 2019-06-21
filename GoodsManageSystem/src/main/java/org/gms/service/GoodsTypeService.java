package org.gms.service;

import java.util.List;

import org.gms.dto.GoodsTypeDto;

public interface GoodsTypeService {

	int insertGoodsType(GoodsTypeDto goodsType);
	int updateGoodsType(GoodsTypeDto goodsType);
	int deleteGoodsType(GoodsTypeDto goodsType);
	List<GoodsTypeDto> selectAll();
}