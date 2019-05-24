package xin.ss.service;

import java.util.List;

import xin.ss.model.Authentication;

public interface AuthenticationService {
	
	public static final int I_SUBMIT  =1;//用户提交
	public static final int I_CHECK   =2;//审核中
	public static final int I_CANCEL  =3;//用户取消
	public static final int I_SUCCESS =4;//审核通过
	public static final int I_FAIL    =5;//审核未通过
	
	//以下是：用户模块功能
	
	/**
	 * 用户提交身份验证
	 * 
	 * @return authentication【userid，url，title，content，time，status=I_SUBMIT，result】
	 */
	boolean submitIdentity(Authentication authentication);
	
	/**
	 * 取消认证，设置status=I_CANCEL与result="用户已取消"
	 * @param authentication 【id 认证主键，userid ，设置status=I_CANCEL，result】
	 * @return
	 */
	boolean cancelIdentity(Authentication authentication);
	
	/**
	 * 根据用户主键查询他提交的验证
	 * @param userid
	 * @return
	 */
	List<Authentication> queryIdentity(int userid);
	
	/**
	 * 判断当前是否存在前二种状态的认证，若存在，不允许再次提交认证，否则可以提交认证
	 * @param userid
	 * @return true 存在前二种状态的认证【自己提交成功后，管理员认证中】
	 */
	boolean isExist_h2_Identity(int userid);
	
	
	
	//以下是：管理员模块功能
	
	
	/**
	 * 管理员用来修改认证的状态与理由（此次管理User表，用来修改是否经过认证，注意%%%）
	 * @param authentication 【id,status,result】
	 * @return
	 */
	boolean updateIdentity(Authentication authentication);
	
	/**
	 * 根据认证状态来查询所有认证信息，status=0，返回所有。
	 * @param status
	 * @return
	 */
	List<Authentication> queryAuthenticationsByStatus(int status);
}



















