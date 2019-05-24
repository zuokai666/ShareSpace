package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Fan;
import xin.ss.model.FanDto;

public class FanSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 检查是否存在关系
	 * @param userid 
	 * @return true 已存在关系
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean selectByHG(Fan fan){
		long count=0;
		Connection conn=getConnection();
		try {
			String sql="select count(*) from Fan where guestid=? and hostid=?";
			count=(Long)runner.query(conn,sql,new ScalarHandler(),fan.getGuestid(),fan.getHostid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if((int)count>0){
			return true;
		}
		return false;
	}
	/**
	 * 返回用户的关注列表
	 * @param guestid
	 * @return
	 */
	public List<FanDto> selectByGuestid(int guestid){
		Connection conn=getConnection();
		List<FanDto> fans=null;
		String sql="select hostid,(select url from User where id=hostid)'hosturl',(select name from User where id=hostid)'hostname' from Fan where guestid=?";
		try {
			fans=runner.query(conn,sql,new BeanListHandler<>(FanDto.class),guestid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return fans;
	}
	/**
	 * 返回用户的粉丝列表
	 * @param guestid
	 * @return
	 */
	public List<FanDto> selectByHostid(int hostid){
		Connection conn=getConnection();
		List<FanDto> fans=null;
		String sql="select guestid,(select url from User where id=guestid)'guesturl',(select name from User where id=guestid)'guestname' from Fan where hostid=?";
		try {
			fans=runner.query(conn,sql,new BeanListHandler<>(FanDto.class),hostid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return fans;
	}
	
	/**
	 * 添加关系
	 */
	public boolean insert(Fan fan){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Fan(guestid,hostid) values(?,?)";
		try {
			effectNum=runner.update(conn,sql,fan.getGuestid(),fan.getHostid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 删除关系
	 * @param fan
	 * @return
	 */
	public boolean delete(Fan fan){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Fan where guestid=? and hostid=?";
		try {
			effectNum=runner.update(conn,sql,fan.getGuestid(),fan.getHostid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}




















