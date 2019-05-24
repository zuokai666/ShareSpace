package xin.ss.service;

import java.util.List;

import xin.ss.model.Message;
import xin.ss.model.MessageDto;

/**
 * 消息人为guest，接收方为host
 * 
 * @author King
 *
 */
public interface MessageService {
	
	/**
	 * 发送消息
	 * @param msg 【hostid，guestid，content，time】     isread=0(数据库默认)
	 * @return
	 */
	boolean sendMsg(Message msg);
	
	/**
	 * 删除发件箱消息[批处理]
	 * @param msg 【id，guestid】
	 * @return
	 */
	boolean deleteOutBoxMsg(List<Message> list);
	/**
	 * 删除收件箱消息[批处理]
	 * @param msg 【id，hostid】
	 * @return
	 */
	boolean deleteInBoxMsg(List<Message> list);
	
	/**
	 * 查询发件箱所有信息（排除已删除，数据访问层自动加上isguestdel=0）
	 * @param guestid
	 * @return
	 */
	List<MessageDto> queryOutBox(int guestid);
	
	/**
	 * 查询收件箱所有信息（排除已删除，数据访问层自动加上ishostdel=0）
	 * @param hostid
	 * @return
	 */
	List<MessageDto> queryInBox(int hostid);
	
	/**
	 * 设置收件箱中的信息状态未已读[批处理]
	 * @param msg 【id，hostid】
	 * @return
	 */
	boolean setRead(List<Message> list);
	
	/**
	 * 得到未读数目，在页面显示
	 * @param hostid
	 * @return
	 */
	int getRead(int hostid);
}

































