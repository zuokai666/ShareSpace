package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.FanSqlHandler;
import xin.ss.dao.UserSqlHandler;
import xin.ss.model.Fan;
import xin.ss.model.FanDto;
import xin.ss.service.FanService;

public class FanServiceImpl implements FanService {

	private FanSqlHandler handler;
	private UserSqlHandler userHandler;
	
	@Override
	public boolean follow(Fan fan) {
		handler=new FanSqlHandler();
		userHandler=new UserSqlHandler();
		boolean isOk=true;
		try{
			handler.setWholeConn();
			userHandler.setWholeConn();
			handler.begin();
			
			boolean isExist=handler.selectByHG(fan);
			if(isExist){
				handler.delete(fan);
				//host fannumber-1;而我  follownumber-1
				userHandler.updateFan_Minus(fan.getHostid());
				userHandler.updateFollow_Minus(fan.getGuestid());
			}else {
				handler.insert(fan);
				//host fannumber+1;而我  follownumber+1
				userHandler.updateFan_Plus(fan.getHostid());
				userHandler.updateFollow_Plus(fan.getGuestid());
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
	public List<FanDto> queryFollow(int guestid) {
		handler=new FanSqlHandler();
		return handler.selectByGuestid(guestid);
	}

	@Override
	public List<FanDto> queryFan(int hostid) {
		handler=new FanSqlHandler();
		return handler.selectByHostid(hostid);
	}

	@Override
	public boolean isExistRelation(Fan fan) {
		handler=new FanSqlHandler();
		return handler.selectByHG(fan);
	}

}
