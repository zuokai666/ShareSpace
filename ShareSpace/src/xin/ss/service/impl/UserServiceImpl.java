package xin.ss.service.impl;

import java.util.List;


import xin.ss.dao.CommentSqlHandler;
import xin.ss.dao.ShareSqlHandler;
import xin.ss.dao.UserSqlHandler;
import xin.ss.dao.UserTagSqlHandler;
import xin.ss.model.Page;
import xin.ss.model.Statistics;
import xin.ss.model.User;
import xin.ss.model.UserDto;
import xin.ss.model.UserTag;
import xin.ss.service.UserService;

public class UserServiceImpl implements UserService {

	private UserSqlHandler handler;
	private UserTagSqlHandler userTagHandler;
	
	@Override
	public boolean update(User user) {
		handler=new UserSqlHandler();
		return handler.update(user);
	}

	@Override
	public boolean updateUrl(User user) {
		handler=new UserSqlHandler();
		return handler.updateUrl(user);
	}
	
	@Override
	public User login(User user) {
		handler=new UserSqlHandler();
		return handler.select(user);
	}

	@Override
	public boolean register(User user) {
		handler=new UserSqlHandler();
		boolean res=false;
		try{
			handler.setWholeConn();
			handler.begin();
			//1 验证是否有重账号
			//2 验证是否有重名
			if(handler.selectByAccount(user.getAccount())==false&&handler.selectByName(user.getName())==false){
				//3 添加用户
				boolean isOk=handler.insert(user);
				if(isOk){
					res=true;
				}
			}
			handler.commit();
		}catch(Exception e){
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return res;
	}

	@Override
	public boolean updateRank(User user) {
		handler=new UserSqlHandler();
		return handler.updateRank(user);
	}

	@Override
	public List<UserDto> listUserRanks() {
		handler=new UserSqlHandler();
		return handler.selectRanks();
	}

	@Override
	public User queryUser(int id) {
		handler=new UserSqlHandler();
		userTagHandler=new UserTagSqlHandler();
		User user=null;
		try {
			handler.setWholeConn();
			userTagHandler.setWholeConn();
			handler.begin();
			
			user=handler.selectById(id);
			if(user!=null){
				user.setTaglist(userTagHandler.select(id));
			}
			
			handler.commit();
		}catch(Exception e){
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return user;
	}

	@Override
	public Boolean checkName(String name) {
		handler=new UserSqlHandler();
		return handler.selectByName(name);
	}

	@Override
	public Boolean checkAccount(String account) {
		handler=new UserSqlHandler();
		return handler.selectByAccount(account);
	}

	@Override
	public Statistics GetStatistics() {
		handler=new UserSqlHandler();
		CommentSqlHandler commentSqlHandler=new CommentSqlHandler();
		ShareSqlHandler shareSqlHandler=new ShareSqlHandler();
		Statistics s=new Statistics();
		try {
			handler.setWholeConn();
			commentSqlHandler.setWholeConn();
			shareSqlHandler.setWholeConn();
			handler.begin();
			
			//1
			s.setRegister(handler.selectCount());
			//2
			s.setComment(commentSqlHandler.selectCount());
			//3
			Page page=new Page();
			page.setTypeid(0);
			page.setUserid(-1);
			s.setShare(shareSqlHandler.selectCount(page));
			
			handler.commit();
		}catch(Exception e){
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return s;
	}

	@Override
	public boolean updateName(User user) {
		handler=new UserSqlHandler();
		boolean res=false;
		try{
			handler.setWholeConn();
			handler.begin();
			//验证是否有重名
			//1 设置空名
			User u=new User();
			u.setId(user.getId());
			u.setName("");
			handler.updateName(u);
			//2 查找是否存在此昵称
			boolean isExist=handler.selectByName(user.getName());
			//3 根据2结果，进行操作
			if(!isExist){
				handler.updateName(user);
				res=true;
			}else {
				res=false;
				throw new Exception();
			}
			handler.commit();
		}catch(Exception e){
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return res;
	}

	@Override
	public boolean updatePsw(User user) {
		handler=new UserSqlHandler();
		return handler.updatePsw(user);
	}

	@Override
	public int queryIdByName(String name) {
		handler=new UserSqlHandler();
		return handler.selectIdByName(name);
	}

	@Override
	public boolean updateUserTag(List<UserTag> tags) {
		userTagHandler=new UserTagSqlHandler();
		boolean isOk=false;
		try{
			userTagHandler.setWholeConn();
			userTagHandler.begin();
			
			userTagHandler.delete(tags.get(0).getUserid());
			userTagHandler.insert(tags);
			isOk=true;
			
			userTagHandler.commit();
		}catch(Exception e){
			userTagHandler.rollback();
		} finally{
			userTagHandler.closeWholeConn();
		}
		return isOk;
	}

	@Override
	public boolean delUserTag(int userid) {
		userTagHandler=new UserTagSqlHandler();
		return userTagHandler.delete(userid);
	}

}

























