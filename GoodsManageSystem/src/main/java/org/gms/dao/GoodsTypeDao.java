package org.gms.dao;

import java.util.List;

import org.gms.dto.GoodsTypeDto;
import org.springframework.stereotype.Repository;

public interface GoodsTypeDao {
	
	int insertGoodsType(GoodsTypeDto goodsType);
	int updateGoodsType(GoodsTypeDto goodsType);
	int deleteGoodsType(GoodsTypeDto goodsType);
	List<GoodsTypeDto> selectAll();
}