package xin.ss.service;

import java.util.List;

import xin.ss.model.Report;
import xin.ss.model.ReportDto;

public interface ReportService {
	
	//以下是：用户模块功能
	
	/**
	 * 用户提交非法分享的举报
	 * @param report 【举报信息】
	 * @return
	 */
	boolean submitReport(Report report);
	
	
	//以下是：管理员模块功能
	
	
	/**
	 * 查询用户提交的的举报
	 * 
	 * @return
	 */
	List<ReportDto> queryReports();
	
	/**
	 * 删除已处理的举报
	 * 
	 * @param reportid
	 * @return
	 */
	boolean deleteResport(int reportid);
	
	
}

































