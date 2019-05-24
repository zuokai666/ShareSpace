package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import xin.ss.model.Notice;

public class NoticeSqlHandler extends AbstractTransactionHandler  {
	
	
	/**
	 * 返回公告列表
	 * @return
	 */
	public List<Notice> select(){
		Connection conn=getConnection();
		List<Notice> notices=null;
		String sql="select id,title,content,time from Notice order by time desc";
		try {
			notices=runner.query(conn,sql,new BeanListHandler<>(Notice.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return notices;
	}
	
	
	/**
	 * 添加公告
	 */
	public boolean insert(Notice notice){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Notice(title,content,time) values(?,?,?)";
		try {
			effectNum=runner.update(conn,sql,notice.getTitle(),notice.getContent(),notice.getTime());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 修改公告
	 */
	public boolean update(Notice notice){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update Notice set title=?,content=? where id=?";
		try {
			effectNum=runner.update(conn,sql,notice.getTitle(),notice.getContent(),notice.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 删除公告
	 * @param fan
	 * @return
	 */
	public boolean delete(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Notice where id=?";
		try {
			effectNum=runner.update(conn,sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}




















