package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.AuthenticationSqlHandler;
import xin.ss.dao.UserSqlHandler;
import xin.ss.model.Authentication;
import xin.ss.model.User;
import xin.ss.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

	private AuthenticationSqlHandler handler;
	private UserSqlHandler userHandler;
	
	@Override
	public boolean submitIdentity(Authentication authentication) {
		handler=new AuthenticationSqlHandler();
		boolean isOk=true;
		try{
			handler.setWholeConn();
			handler.begin();
		    boolean ok=handler.selectBy12(authentication.getUserid());
		    if(!ok){
		    	handler.insert(authentication);
		    }else {
				isOk=false;
			}
			handler.commit();
		}catch(Exception e){
			isOk=false;
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return isOk;
	}

	@Override
	public boolean cancelIdentity(Authentication authentication) {
		handler=new AuthenticationSqlHandler();
		boolean isOk=true;
		try{
			handler.setWholeConn();
			handler.begin();
		    boolean ok=handler.selectBy245(authentication);
		    if(!ok){
		    	handler.update(authentication);
		    }else {
				isOk=false;
			}
			handler.commit();
		}catch(Exception e){
			isOk=false;
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return isOk;
	}

	@Override
	public List<Authentication> queryIdentity(int userid) {
		handler=new AuthenticationSqlHandler();
		return handler.selectByUserId(userid);
	}

	@Override
	public boolean isExist_h2_Identity(int userid) {
		handler=new AuthenticationSqlHandler();
		return handler.selectBy12(userid);
	}

	@Override
	public boolean updateIdentity(Authentication authentication) {
		handler=new AuthenticationSqlHandler();
		userHandler=new UserSqlHandler();
		boolean isOk=true;
		try{
			handler.setWholeConn();
			userHandler.setWholeConn();
			handler.begin();
			
		    boolean ok=handler.update(authentication);
		    if(ok){
		    	//验证是否审核通过
		    	if(authentication.getStatus()==4){
		    		Authentication a=handler.selectById(authentication.getId());
		    		User user=new User();
		    		user.setId(a.getUserid());
		    		user.setTitle(a.getTitle());
		    		userHandler.updateAuth(user);
		    	}
		    }else {
				isOk=false;
			}
		    
			handler.commit();
		}catch(Exception e){
			isOk=false;
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return isOk;
	}

	@Override
	public List<Authentication> queryAuthenticationsByStatus(int status) {
		handler=new AuthenticationSqlHandler();
		return handler.selectByStatus(status);
	}

}




















