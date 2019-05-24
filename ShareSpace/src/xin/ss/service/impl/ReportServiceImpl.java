package xin.ss.service.impl;

import java.util.List;

import xin.ss.dao.ReportSqlHandler;
import xin.ss.model.Report;
import xin.ss.model.ReportDto;
import xin.ss.service.ReportService;

public class ReportServiceImpl implements ReportService {
	
	private ReportSqlHandler handler;

	@Override
	public boolean submitReport(Report report) {
		handler=new ReportSqlHandler();
		return handler.insert(report);
	}

	@Override
	public List<ReportDto> queryReports() {
		handler=new ReportSqlHandler();
		return handler.select();
	}

	@Override
	public boolean deleteResport(int reportid) {
		handler=new ReportSqlHandler();
		return handler.delete(reportid);	
	}

}
