package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.NoticeSqlHandler;
import xin.ss.model.Notice;
import xin.ss.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeSqlHandler handler;

	@Override
	public boolean insertNotice(Notice notice) {
		handler=new NoticeSqlHandler();
		return handler.insert(notice);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		handler=new NoticeSqlHandler();
		return handler.update(notice);
	}

	@Override
	public boolean deleteNotice(int id) {
		handler=new NoticeSqlHandler();
		return handler.delete(id);
	}

	@Override
	public List<Notice> queryNotices() {
		handler=new NoticeSqlHandler();
		return handler.select();
	}

}
