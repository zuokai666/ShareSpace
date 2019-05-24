package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.TagSqlHandler;
import xin.ss.model.Tag;
import xin.ss.service.TagService;

public class TagServiceImpl implements TagService {
	
	private TagSqlHandler handler;

	@Override
	public boolean insertTag(Tag tag) {
		handler=new TagSqlHandler();
		return handler.insert(tag);
	}

	@Override
	public boolean updateTag(Tag tag) {
		handler=new TagSqlHandler();
		return handler.update(tag);
	}

	@Override
	public List<Tag> queryTags() {
		handler=new TagSqlHandler();
		return handler.select();
	}

}
