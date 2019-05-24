package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.CommentSqlHandler;
import xin.ss.dao.RelationSqlHandler;
import xin.ss.dao.ShareSqlHandler;
import xin.ss.dao.UserSqlHandler;
import xin.ss.model.Comment;
import xin.ss.model.CommentDto;
import xin.ss.model.Relation;
import xin.ss.model.User;
import xin.ss.service.CommentService;
import xin.ss.util.RankMangaer;

public class CommentServiceImpl implements CommentService {

	private CommentSqlHandler handler;
	private UserSqlHandler userHandler;
	private RelationSqlHandler relationHandler;
	private ShareSqlHandler shareHandler;
	
	@Override
	public List<CommentDto> listComments(int shareid) {
		handler=new CommentSqlHandler();
		return handler.selectByShareId(shareid);
	}

	@Override
	public boolean commitComment(Comment comment) {
		handler=new CommentSqlHandler();
		userHandler=new UserSqlHandler();
		relationHandler=new RelationSqlHandler();
		shareHandler=new ShareSqlHandler();
		try{
			handler.setWholeConn();
			userHandler.setWholeConn();
			relationHandler.setWholeConn();
			shareHandler.setWholeConn();
			handler.begin();
			//1 提交评论
			boolean isOk=handler.insert(comment);
			if(!isOk){
				throw new RuntimeException("commitComment事务中：添加评论失败");
			}
			//2 提升关系度
			Relation relation=new Relation();
			relation.setShareid(comment.getShareid());
			relation.setGuestid(comment.getUserid());
			boolean isExist=relationHandler.selectReturnTF(relation);
			if(!isExist){
				relation.setScore(RankMangaer.getComment());
				relationHandler.insert(relation);
			}else{
				int result=relationHandler.selectByCondition(relation);
				relation.setScore(result+RankMangaer.getComment());
				relationHandler.update(relation);
			}
			//3提升rank
			int hostid=shareHandler.selectUserIdByShareId(comment.getShareid());
			User user=userHandler.selectById(hostid);
			if(user!=null){
				int rank=user.getRank();
				isOk=userHandler.updateRank(new User(hostid,rank+RankMangaer.getComment()));
				if(!isOk){
					throw new RuntimeException("commitComment事务中：提升rank失败");
				}
			}else{
				throw new RuntimeException("commitComment事务中：无此用户id="+hostid);
			}
			handler.commit();
		}catch(Exception e){
			e.printStackTrace();
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return true;
	}


}
































