package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import xin.ss.model.Tag;
import xin.ss.model.UserTag;

public class UserTagSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 根据Id查询用户信息时使用
	 * 目的：用来显示用户的标签列表
	 * 
	 * @param userid 用户Id
	 * @return 标签列表
	 */
	public List<Tag> select(int userid){
		Connection conn=getConnection();
		List<Tag> tags=null;
		String sql="select (tagid)'id',(select name from Tag where id=tagid)'name' from UserTag where userid=?";
		try {
			tags=runner.query(conn,sql,new BeanListHandler<>(Tag.class),userid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return tags;
	}
	/**
	 * 增加标签
	 * @param userTag
	 * @return
	 */
	public boolean insert(List<UserTag> list){
		Connection conn=getConnection();
		String sql="insert into UserTag(userid,tagid) values(?,?)";
		Object params[][] = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
             params[i] = new Object[] { list.get(i).getUserid(),list.get(i).getTagid()};
        }
		try {
			runner.batch(conn,sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return true;
	}
	/**
	 * 删除标签(特制)
	 * @param userid
	 * @return
	 */
	public boolean delete(int userid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from UserTag where userid=?";
		try {
			effectNum=runner.update(conn,sql,userid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}




















