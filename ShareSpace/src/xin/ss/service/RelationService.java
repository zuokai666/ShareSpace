package xin.ss.service;

import java.util.List;

import xin.ss.model.*;

public interface RelationService {
	/**
	 * 用于查看关系图使用，根据用户id查询出所有与他有关系的用户。
	 * @param hostid 用户id
	 * @return 
	 */
	List<RelationDto> queryScore(int hostid);
}
