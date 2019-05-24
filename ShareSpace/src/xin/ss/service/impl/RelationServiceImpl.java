package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.RelationSqlHandler;
import xin.ss.model.RelationDto;
import xin.ss.service.RelationService;

public class RelationServiceImpl implements RelationService {

	private RelationSqlHandler handler;
	@Override
	public List<RelationDto> queryScore(int hostid) {
		handler=new RelationSqlHandler();
		return handler.selectByHostId(hostid);
	}


}
