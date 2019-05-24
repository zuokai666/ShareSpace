package xin.ss.service;


import xin.ss.model.Page;
import xin.ss.model.Relation;
import xin.ss.model.Share;

public interface ShareService {
	
	/**
	 * 用户删除分享（后续降分各种操作）
	 * @param share 【id，userid】
	 * @return
	 */
	boolean deleteShare(Share share);
	/**
	 * <p>1 发表分享，添加到Share中
	 * <p>2 rank提升，添加到User中，2分
	 */
	boolean publishShare(Share share);
	/**
	 * <p>在主页列出所有的分享,并且列出每个分享的评论列表
	 * 
	 * @return 分享列表
	 */
	Page listAllShares(Page page);
	
	/**
	 * <p>0 判断该推荐人是否已经添加推荐
	 * <p>如果没有：
	 * 		<p>1 推荐分享，添加到Share中goodnumber+1
	 * 		<p>2 提升与该Share主人的关系度,Relation中更新分数+2
	 * 		<p>3 rank提升，添加到User中，rank+2分
	 * @param relation shareid为分享Id，guestid为推荐人Id。
	 */
	boolean commitGood(Relation relation);
	/**
	 * <p>0 判断该推荐人是否已经添加反对
	 * <p>如果没有：
	 * 		<p>1 反对分享，添加到Share中badnumber+1
	 * 		<p>2 降低与该Share主人的关系度,Relation中更新分数-2
	 * 		<p>3 rank降低，添加到User中，rank-2分
	 * 
	 * @param relation shareid为分享Id，guestid为反对人Id。
	 */
	boolean commitBad(Relation relation);
}




















