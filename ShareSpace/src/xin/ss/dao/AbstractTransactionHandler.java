package xin.ss.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import xin.ss.dao.util.C3P0Util;
import xin.ss.dao.util.ThreadLocalManager;

public abstract class AbstractTransactionHandler {
	
	private Connection wholeConn=null;
	private boolean isWhole=false;
	
	public QueryRunner runner=new QueryRunner();
	
	public void setWholeConn() {
		this.wholeConn = ThreadLocalManager.getConnection();
		isWhole=true;
	}
	
	public Connection getConnection(){
		if(wholeConn!=null){
			return wholeConn;
		}else{
			return C3P0Util.getConnection();
		}
	}
	
	public void begin(){
		ThreadLocalManager.startTransaction();
	}
	
	public void commit(){
		ThreadLocalManager.commitTransaction();
	}
	
	public void rollback(){
		ThreadLocalManager.rollbackTransaction();
	}
	
	public void closeLocalConn(Connection conn){
		if(!isWhole){
			C3P0Util.close(conn);
		}
	}
	
	public void closeWholeConn(){
		ThreadLocalManager.closeConnection();
	}
	
	public boolean getResult(int effectNum){
		if(effectNum>0){
			return true;
		}else{
			return false;
		}
		
	}
}
















