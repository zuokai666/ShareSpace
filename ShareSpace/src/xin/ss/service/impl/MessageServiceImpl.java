package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.MessageSqlHandler;
import xin.ss.model.Message;
import xin.ss.model.MessageDto;
import xin.ss.service.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private MessageSqlHandler handler;

	@Override
	public boolean sendMsg(Message msg) {
		handler=new MessageSqlHandler();
		return handler.insert(msg);
	}

	@Override
	public boolean deleteOutBoxMsg(List<Message> list) {
		handler=new MessageSqlHandler();
		return handler.updateIsguestdel(list);
	}

	@Override
	public boolean deleteInBoxMsg(List<Message> list) {
		handler=new MessageSqlHandler();
		return handler.updateIshostdel(list);
	}

	@Override
	public List<MessageDto> queryOutBox(int guestid) {
		handler=new MessageSqlHandler();
		return handler.selectByGuestid(guestid);
	}

	@Override
	public List<MessageDto> queryInBox(int hostid) {
		handler=new MessageSqlHandler();
		return handler.selectByHostid(hostid);
	}

	@Override
	public boolean setRead(List<Message> list) {
		handler=new MessageSqlHandler();
		return handler.updateIsread(list);
	}

	@Override
	public int getRead(int hostid) {
		handler=new MessageSqlHandler();
		return handler.selectRead(hostid);
	}
}










