package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import xin.ss.model.Image;

public class ImageSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 根据shareid列出分享的图片
	 * @param shareid
	 * @return
	 */
	public List<Image> select(int shareid){
		Connection conn=getConnection();
		List<Image> list=null;
		String sql="select url from Image where shareid=?";
		try {
			list=runner.query(conn,sql,new BeanListHandler<>(Image.class),shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return list;
	}
	
	/**
	 * 添加一系列图片
	 * @param 
	 * @return
	 */
	public boolean insert(List<Image> images){
		if(images.size()==0){
			return true;
		}
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Image(shareid,url) values(?,?)";
		Object[][] obj=new Object[images.size()][2];
		for(int i=0;i<images.size();i++){
			obj[i]=new Object[]{images.get(i).getShareid(),images.get(i).getUrl()};
		}
		try {
			effectNum=runner.batch(conn, sql, obj).length;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(effectNum==images.size()){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 删除该条记录，删除分享使用
	 * @param shareidid
	 * @return
	 */
	public boolean deleteByShareId(int shareid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Image where shareid=?";
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

















