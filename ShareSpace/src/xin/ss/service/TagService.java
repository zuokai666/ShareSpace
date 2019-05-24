package xin.ss.service;

import java.util.List;

import xin.ss.model.Tag;

public interface TagService {
	
	/**
	 * 增加标签
	 * @param userTag 【userid，tagid】
	 * @return
	 */
	boolean insertTag(Tag tag);
	
	/**
	 * 修改已有标签
	 * @param tag
	 * @return
	 */
	boolean updateTag(Tag tag);
	
	/**
	 * 查询所有标签
	 * @return
	 */
	List<Tag> queryTags();
}

































