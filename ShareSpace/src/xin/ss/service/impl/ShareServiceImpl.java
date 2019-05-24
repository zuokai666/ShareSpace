package xin.ss.service.impl;


import java.util.List;

import xin.ss.dao.CommentSqlHandler;
import xin.ss.dao.GBSqlHandler;
import xin.ss.dao.ImageSqlHandler;
import xin.ss.dao.RelationSqlHandler;
import xin.ss.dao.ShareSqlHandler;
import xin.ss.dao.UserSqlHandler;
import xin.ss.model.CommentDto;
import xin.ss.model.Image;
import xin.ss.model.Page;
import xin.ss.model.Relation;
import xin.ss.model.Share;
import xin.ss.model.ShareDto;
import xin.ss.model.User;
import xin.ss.service.ShareService;
import xin.ss.util.RankMangaer;

public class ShareServiceImpl implements ShareService {

	public static List<Image> urls;
	
	private ShareSqlHandler handler;
	private UserSqlHandler userHandler;
	private RelationSqlHandler relationHandler;
	private GBSqlHandler gbHandler;
	private CommentSqlHandler commentHandler;
	private ImageSqlHandler imageHandler;
	
	
	
	@Override
	public boolean deleteShare(Share share) {
		handler=new ShareSqlHandler();//删除分享
		commentHandler=new CommentSqlHandler();//删除评论
		imageHandler=new ImageSqlHandler();//删除图片，以及数据库
		gbHandler=new GBSqlHandler();//删除推荐反对
		Boolean result=true;
		try{
			gbHandler.setWholeConn();
			imageHandler.setWholeConn();
			commentHandler.setWholeConn();
			handler.setWholeConn();
			handler.begin();
			
			commentHandler.deleteByShareId(share.getId());
			urls=imageHandler.select(share.getId());
			imageHandler.deleteByShareId(share.getId());
			gbHandler.deleteByShareId(share.getId());
			boolean isOk=handler.delete(share);
			if(!isOk){
				throw new RuntimeException("删除分享失败");
			}
			handler.commit();
		}catch(Exception e){
			e.printStackTrace();
			result=false;
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return result;
	}
	
	
	@Override
	public boolean publishShare(Share share) {
		userHandler=new UserSqlHandler();
		handler=new ShareSqlHandler();
		imageHandler=new ImageSqlHandler();
		try{
			userHandler.setWholeConn();
			handler.setWholeConn();
			imageHandler.setWholeConn();
			handler.begin();
			// 1 添加分享
			boolean isOk=handler.insert(share);
			if(!isOk){
				throw new RuntimeException("publishShare事务中：添加分享失败");
			}
			//1.5 得到shareid
			int shareid=handler.selectShareId();
			//2 添加图片sql,此时图片中并没有shareid
			List<Image> images=share.getImageList();
			for(Image i:images){
				i.setShareid(shareid);
			}
			isOk=imageHandler.insert(images);
			if(!isOk){
				throw new RuntimeException("publishShare事务中：添加添加失败，或者数目不对");
			}
			//3 更新分数
			User user=userHandler.selectById(share.getUserid());
			if(user!=null){
				int rank=user.getRank();
				isOk=userHandler.updateRank(new User(share.getUserid(),rank+RankMangaer.getShare()));
				if(!isOk){
					throw new RuntimeException("publishShare事务中：提升rank失败");
				}
			}else{
				throw new RuntimeException("publishShare事务中：该用户不存在id="+share.getUserid());
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

	
	@Override
	public Page listAllShares(Page page) {
		handler=new ShareSqlHandler();
		commentHandler=new CommentSqlHandler();
		imageHandler=new ImageSqlHandler();
		try{
			handler.setWholeConn();
			imageHandler.setWholeConn();
			commentHandler.setWholeConn();
			handler.begin();
			//0为每个分享添加评论
			List<ShareDto> shareList=handler.selectAll(page);
			for(int i=0;i<shareList.size();i++){
				int shareid=shareList.get(i).getId();
				List<CommentDto> comments=commentHandler.selectByShareId(shareid);
				shareList.get(i).setCommentList(comments);
				//0.5 为每个分享添加图片
				List<Image> images=imageHandler.select(shareid);
				shareList.get(i).setImageList(images);
			}
			//1设置列表
			page.setShareList(shareList);
			//2设置总数
			int count=handler.selectCount(page);
			page.setCount(count);
			//3设置总页数
			int totalPage=(int)Math.ceil(count*1.0/page.getPageSize());
			page.setTotalPage(totalPage);
			handler.commit();
		}catch(Exception e){
			e.printStackTrace();
			handler.rollback();
		} finally{
			handler.closeWholeConn();
		}
		return page;
	}

	@Override
	public boolean commitGood(Relation relation) {
		gbHandler=new GBSqlHandler();
		userHandler=new UserSqlHandler();
		handler=new ShareSqlHandler();
		relationHandler=new RelationSqlHandler();
		try{
			gbHandler.setWholeConn();
			userHandler.setWholeConn();
			relationHandler.setWholeConn();
			handler.setWholeConn();
			handler.begin();
			//0 判断是否已经推荐过
			int res=gbHandler.selectGood(relation);
			if(res==-1){
				gbHandler.insertGood(relation);
			}else if(res==1){
				return false;//已经推荐过
			}else{
				gbHandler.updateGood(relation);
			}
			//1添加推荐
			boolean isOk=handler.updateGood(relation.getShareid());
			if(!isOk){
				throw new RuntimeException("commitGood事务中：添加推荐失败");
			}
			//3 提升rank
			//hostid 分享主人的id
			int hostid=handler.selectUserIdByShareId(relation.getShareid());
			User user=userHandler.selectById(hostid);
			if(user!=null){
				int rank=user.getRank();
				isOk=userHandler.updateRank(new User(hostid,rank+RankMangaer.getGood()));
				if(!isOk){
					throw new RuntimeException("commitGood事务中：提升rank失败");
				}
				//2 提升关系度
				boolean isExist=relationHandler.selectReturnTF(relation);
				if(!isExist){
					relation.setScore(RankMangaer.getGood());
					relationHandler.insert(relation);
				}else{
					int result=relationHandler.selectByCondition(relation);
					relation.setScore(result+RankMangaer.getGood());
					relationHandler.update(relation);
				}
			}else{
				throw new RuntimeException("commitGood事务中：无此用户id="+hostid);
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

	@Override
	public boolean commitBad(Relation relation) {
		gbHandler=new GBSqlHandler();
		userHandler=new UserSqlHandler();
		handler=new ShareSqlHandler();
		relationHandler=new RelationSqlHandler();
		try{
			gbHandler.setWholeConn();
			userHandler.setWholeConn();
			relationHandler.setWholeConn();
			handler.setWholeConn();
			handler.begin();
			//1
			int res=gbHandler.selectBad(relation);
			if(res==-1){
				gbHandler.insertBad(relation);
			}else if(res==1){
				return false;
			}else{
				gbHandler.updateBad(relation);
			}
			//1
			boolean isOk=handler.updateBad(relation.getShareid());
			if(!isOk){
				throw new RuntimeException("commitBad事务中：添加反对失败");
			}
			//3 降低rank
			int hostid=handler.selectUserIdByShareId(relation.getShareid());
			User user=userHandler.selectById(hostid);
			if(user!=null){
				int rank=user.getRank();
				isOk=userHandler.updateRank(new User(hostid,rank+RankMangaer.getBad()));
				if(!isOk){
					throw new RuntimeException("commitBad事务中：降低rank失败");
				}
				//2 提升关系度
				boolean isExist=relationHandler.selectReturnTF(relation);
				if(!isExist){
					relation.setScore(RankMangaer.getBad());
					relationHandler.insert(relation);
				}else{
					int result=relationHandler.selectByCondition(relation);
					relation.setScore(result+RankMangaer.getBad());
					relationHandler.update(relation);
				}
			}else{
				throw new RuntimeException("commitBad事务中：无此用户id="+hostid);
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


















