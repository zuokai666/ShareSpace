package xin.ss.service;

import java.util.List;

import xin.ss.model.Notice;

public interface NoticeService {
	
	/**
	 * 添加公告
	 * @param notice
	 * @return
	 */
	boolean insertNotice(Notice notice);
	
	/**
	 * 更新公告
	 * @param notice
	 * @return
	 */
	boolean updateNotice(Notice notice);
	/**
	 * 删除公告
	 * @param notice
	 * @return
	 */
	boolean deleteNotice(int id);
	
	/**
	 * 列出所有公告
	 * @return
	 */
	List<Notice> queryNotices();
}































