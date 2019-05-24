package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Page;
import xin.ss.model.Share;
import xin.ss.model.ShareDto;

public class ShareSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * <p>根据当前页数，和分享类型Id，返回所有的分享列表
	 * 
	 * <p>ShareDto为Share的数据传输对象，改变了userid与typeid.
	 * <p>userid=-1，表明显示全部用户分享。
	 * @return
	 */
	public List<ShareDto> selectAll(Page page){
		Connection conn=getConnection();
		List<ShareDto> shareList=null;
		int a=page.getCurrentPage();
		int b=page.getPageSize();
		int c=page.getTypeid();
		int u=page.getUserid();
		try {
			if(c==0){
				if(u==-1){
					String sql="select id,userid,(select name from User where id=userid)'username',"
							+ "(select url from User where id=userid)'url',"
							+ "content,(select name from Type where id=typeid)'typename',"
							+ "publishtime,goodnumber,badnumber from Share order by publishtime desc limit ?,?";
					shareList=runner.query(conn,sql,new BeanListHandler<>(ShareDto.class),(a-1)*b,b);
				}else{
					String sql="select id,userid,(select name from User where id=userid)'username',"
							+ "(select url from User where id=userid)'url',"
							+ "content,(select name from Type where id=typeid)'typename',"
							+ "publishtime,goodnumber,badnumber from Share where userid=? order by publishtime desc limit ?,?";
					shareList=runner.query(conn,sql,new BeanListHandler<>(ShareDto.class),u,(a-1)*b,b);
				}
			}else{
				if(u==-1){
					String sql="select id,userid,(select name from User where id=userid)'username',"
							+ "(select url from User where id=userid)'url',"
							+ "content,(select name from Type where id=typeid)'typename',"
							+ "publishtime,goodnumber,badnumber from Share where typeid=? order by publishtime desc limit ?,?";
					shareList=runner.query(conn,sql,new BeanListHandler<>(ShareDto.class),c,(a-1)*b,b);
				}else{
					String sql="select id,userid,(select name from User where id=userid)'username',"
							+ "(select url from User where id=userid)'url',"
							+ "content,(select name from Type where id=typeid)'typename',"
							+ "publishtime,goodnumber,badnumber from Share where typeid=? and userid=? order by publishtime desc limit ?,?";
					shareList=runner.query(conn,sql,new BeanListHandler<>(ShareDto.class),c,u,(a-1)*b,b);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return shareList;
	}
	/**
	 * 通过分享内容id获得总数
	 * @param page 【typeid】
	 * @return 数量
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectCount(Page page){
		Connection conn=getConnection();
		long count=0;
		int typeid=page.getTypeid();
		int u=page.getUserid();
		try {
			if(typeid==0){
				if(u==-1){
					String sql="select count(*) from Share";
					count=(Long)runner.query(conn,sql,new ScalarHandler());
				}else{
					String sql="select count(*) from Share where userid=?";
					count=(Long)runner.query(conn,sql,new ScalarHandler(),u);
				}
				
			}else{
				if(u==-1){
					String sql="select count(*) from Share where typeid=?";
					count=(Long)runner.query(conn,sql,new ScalarHandler(),typeid);
				}else{
					String sql="select count(*) from Share where typeid=? and userid=?";
					count=(Long)runner.query(conn,sql,new ScalarHandler(),typeid,u);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return (int)count;
	}
	/**
	 * 根据分享Id，查找出该分享的主人UserId号。
	 * 
	 * @param shareid 分享的Id
	 * @return 主人的Id，如果为-1，表示没有此分享
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectUserIdByShareId(int shareid){
		Connection conn=getConnection();
		Object userid=null;
		String sql="select userid from Share where id=?";
		try {
			userid=runner.query(conn,sql,new ScalarHandler(),shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(userid!=null){
			return (Integer)userid;
		}
		return -1;
	}
	
	/**
	 * 添加分享
	 * 
	 * @param share 需要【userid,content,typeid,publishtime】
	 * @return
	 */
	public boolean insert(Share share){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Share(userid,content,typeid,publishtime) values(?,?,?,?)";
		Object[] obj=new Object[4];
		obj[0]=share.getUserid();
		obj[1]=share.getContent();
		obj[2]=share.getTypeid();
		obj[3]=share.getPublishtime();
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
	 * 得到分享之后的主键
	 * @return 主键
	 */
	public int selectShareId(){
		Connection conn=getConnection();
		Share id=null;
		String sql="SELECT @@IDENTITY as id";
		try {
			id=runner.query(conn,sql,new BeanHandler<>(Share.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return id.getId();
	}
	
	/**
	 * 根据Shareid增加一个推荐
	 * 
	 * @param shareid
	 */
	public boolean updateGood(int shareid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update Share set goodnumber=goodnumber+1 where id=?";
		try {
			effectNum=runner.update(conn,sql,shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 根据Shareid增加一个反对
	 * @param shareid
	 */
	public boolean updateBad(int shareid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update Share set badnumber=badnumber+1 where id=?";
		try {
			effectNum=runner.update(conn,sql,shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	
	/**
	 * <p>删除该条记录，删除分享使用
	 * <p>自己删除，管理员删除
	 * @param shareidid
	 * @return
	 */
	public boolean delete(Share share){
		int effectNum=0;
		Connection conn=getConnection();
		try {
			int userid=share.getUserid();
			if(userid==-1){
				String sql="delete from Share where id=?";
				effectNum=runner.update(conn,sql,share.getId());
			}else {
				String sql="delete from Share where id=? and userid=?";
				effectNum=runner.update(conn,sql,share.getId(),share.getUserid());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
}

















