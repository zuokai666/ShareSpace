package org.gms.dao;

import java.util.List;

import org.gms.dto.GoodsTypeDto;

public interface GoodsTypeDao {
	
	int insertGoodsType(GoodsTypeDto goodsType);
	int updateGoodsType(GoodsTypeDto goodsType);
	int deleteGoodsType(GoodsTypeDto goodsType);
	List<GoodsTypeDto> selectAll();
}