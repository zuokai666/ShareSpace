package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Message;
import xin.ss.model.MessageDto;

public class MessageSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 返回用户的发件箱信息
	 * @param guestid
	 * @return
	 */
	public List<MessageDto> selectByGuestid(int guestid){
		Connection conn=getConnection();
		List<MessageDto> msgs=null;
		String sql="select id,hostid,(select url from User where id=hostid)'hosturl',"
				+ "(select name from User where id=hostid)'hostname',content,time,isread "
				+ "from Message where guestid=? and isguestdel=0 order by time desc";
		try {
			msgs=runner.query(conn,sql,new BeanListHandler<>(MessageDto.class),guestid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return msgs;
	}
	/**
	 * 返回用户的收件箱信息
	 * @param hostid
	 * @return
	 */
	public List<MessageDto> selectByHostid(int hostid){
		Connection conn=getConnection();
		List<MessageDto> msgs=null;
		String sql="select id,guestid,(select url from User where id=guestid)'guesturl',"
				+ "(select name from User where id=guestid)'guestname',content,time,isread "
				+ "from Message where hostid=? and ishostdel=0 order by time desc";
		try {
			msgs=runner.query(conn,sql,new BeanListHandler<>(MessageDto.class),hostid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return msgs;
	}
	
	/**
	 * 添加消息
	 */
	public boolean insert(Message msg){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Message(hostid,guestid,content,time) values(?,?,?,?)";
		Object[] obj=new Object[4];
		obj[0]=msg.getHostid();
		obj[1]=msg.getGuestid();
		obj[2]=msg.getContent();
		obj[3]=msg.getTime();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 设置发件箱的状态为已删除【id，guestid，isguestdel=1】
	 * @param fan
	 * @return
	 */
	public boolean updateIsguestdel(List<Message> list){
		if(list.size()==0){
			return true;
		}
		Connection conn=getConnection();
		String sql="update Message set isguestdel=1 where id=? and guestid=?";
		Object params[][] = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
             params[i] = new Object[] { list.get(i).getId(),list.get(i).getGuestid()};
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
	 * 设置收件箱的状态为已删除【id，hostid，ishostdel=1】
	 * @param fan
	 * @return
	 */
	public boolean updateIshostdel(List<Message> list){
		if(list.size()==0){
			return true;
		}
		Connection conn=getConnection();
		String sql="update Message set ishostdel=1 where id=? and hostid=?";
		Object params[][] = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
             params[i] = new Object[] { list.get(i).getId(),list.get(i).getHostid()};
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
	 * 设置收件箱的信息为已读【id，hostid，isread=1】
	 * @param fan
	 * @return
	 */
	public boolean updateIsread(List<Message> list){
		if(list.size()==0){
			return true;
		}
		Connection conn=getConnection();
		String sql="update Message set isread=1 where id=? and hostid=?";
		Object params[][] = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
             params[i] = new Object[] { list.get(i).getId(),list.get(i).getHostid()};
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
	 * 真正的删除该删的信息
	 * @param fan
	 * @return
	 */
	public boolean delete(){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Message where ishostdel=1 and isguestdel=1";
		try {
			effectNum=runner.update(conn,sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int selectRead(int hostid){
		long count=0;
		Connection conn=getConnection();
		String sql="select count(*) from Message where hostid=? and ishostdel=0 and isread=0";
		try {
			count=(Long)runner.query(conn,sql,new ScalarHandler(),hostid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return (int)count;
	}
	
}




















