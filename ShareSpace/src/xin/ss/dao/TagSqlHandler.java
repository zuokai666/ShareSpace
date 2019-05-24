package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import xin.ss.model.Tag;

public class TagSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 管理员查询所有标签使用
	 * 目的：用来显示系统所有标签列表
	 * 
	 * @return 标签列表
	 */
	public List<Tag> select(){
		Connection conn=getConnection();
		List<Tag> tags=null;
		String sql="select id,name from Tag";
		try {
			tags=runner.query(conn,sql,new BeanListHandler<>(Tag.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return tags;
	}

	/**
	 * 插入新建的标签
	 * 管理员功能
	 * @param tag [需要name]
	 * @return
	 */
	public boolean insert(Tag tag){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Tag(name) values(?)";
		try {
			effectNum=runner.update(conn,sql,tag.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 更新已有标签
	 * @param tag
	 * @return
	 */
	public boolean update(Tag tag){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update Tag set name=? where id=?";
		try {
			effectNum=runner.update(conn,sql,tag.getName(),tag.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}




















