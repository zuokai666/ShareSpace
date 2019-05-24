package xin.ss.service;

import java.util.List;

import xin.ss.model.Comment;
import xin.ss.model.CommentDto;

public interface CommentService {
	/**
	 * 通过分享id显示出所有评论（已转移到了列出分享中）
	 * 
	 * @param shareid
	 * @return
	 */
	List<CommentDto> listComments(int shareid);
	/**
	 *  <p>1 提交评论
	 *  <p>2提升与主人的关系度 +getComment()
	 *  <p>3提升主人rank分数    +getComment()
	 * 
	 * @param comment  【分享id，评论人id，内容，时间】
	 * @return
	 */
	boolean commitComment(Comment comment);
}
