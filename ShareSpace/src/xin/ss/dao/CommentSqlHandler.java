package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Comment;
import xin.ss.model.CommentDto;

public class CommentSqlHandler extends AbstractTransactionHandler {
	
	/**
	 * 通过分享id查询出所有评论
	 * 
	 * @param shareid
	 * @return
	 */
	public List<CommentDto> selectByShareId(int shareid){
		Connection conn=getConnection();
		List<CommentDto> comments=null;
		String sql="select id,userid,(select name from User where id=userid)'username',"
				+ "(select url from User where id=userid)'url',"
				+ "content,commenttime from Comment where shareid=?";
		try {
			comments=runner.query(conn,sql,new BeanListHandler<>(CommentDto.class),shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return comments;
	}
	
	/**
	 * 得到评论总数，网站统计使用
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectCount(){
		Connection conn=getConnection();
		long count=0;
		try {
			String sql="select count(*) from Comment";
			count=(Long)runner.query(conn,sql,new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return (int)count;
	}
	
	/**
	 * 添加评论
	 * 
	 * @param comment 【分享id，评论人id，内容，时间】
	 * @return
	 */
	public boolean insert(Comment comment){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Comment(shareid,userid,content,commenttime) values(?,?,?,?)";
		Object[] obj=new Object[4];
		obj[0]=comment.getShareid();
		obj[1]=comment.getUserid();
		obj[2]=comment.getContent();
		obj[3]=comment.getCommenttime();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	public boolean deleteByShareId(int shareid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Comment where shareid=?";
		try {
			effectNum=runner.update(conn,sql,shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}

























