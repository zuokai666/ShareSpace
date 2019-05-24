package xin.ss.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 管理常用字符串的工具类，好处：程序与数据分离，增加扩展性，便于实现国际化
 * @author Administrator
 *
 */
public class StringMangaer {
	public static final String CONTENTTYPE_JSON="application/json;charset=UTF-8";
	public static final String CONTENTTYPE_HTML="text/html;charset=UTF-8";
	public static final String RESULT_LOGIN_SUCCESS="{\"result\":\"1\",\"tip\":\"登录成功\"}";
	public static final String RESULT_LOGIN_FAIL="{\"result\":\"0\",\"tip\":\"账号或密码错误\"}";
	public static final String RESULT_NOTLOGIN="{\"result\":\"0\",\"tip\":\"未登录不允许发表评论\"}";
	public static final String RESULT_UPDATE_SUCCESS="{\"result\":\"1\",\"tip\":\"修改成功\"}";
	public static final String RESULT_UPDATE_FAIL="{\"result\":\"0\",\"tip\":\"修改失败\"}";
	public static final String RESULT_INSERT_SUCCESS="{\"result\":\"1\",\"tip\":\"添加成功\"}";
	public static final String RESULT_INSERT_FAIL="{\"result\":\"0\",\"tip\":\"添加失败\"}";
	public static final String RESULT_DELETE_SUCCESS="{\"result\":\"1\",\"tip\":\"删除成功\"}";
	public static final String RESULT_DELETE_FAIL="{\"result\":\"0\",\"tip\":\"删除失败\"}";
	public static final String RESULT_NOT_LOGIN="{\"result\":\"0\",\"tip\":\"未登录\"}";
	
	public static String getEscapeString(String str){
		if(str!=null){
			str=str.replaceAll("\t"," ");
			str=str.replaceAll("\r\n|\r|\n","<br />");
			String b=StringEscapeUtils.escapeJava(str);
			String c=StringEscapeUtils.escapeHtml(b);
			return StringEscapeUtils.unescapeJava(c);
		}else {
			return "null content";
		}
		
	}
	
	public static boolean isNull(String str) {
		if(str!=null){
			if(str.replaceAll(" ","").length()==0){
				return true;
			}
		}else {
			return true;
		}
		return false;
	}
	public static boolean pwdIsWrong(String str) {
		if(str!=null){
			if(str.length()>=6&&str.replaceAll(" ","").length()==str.length()){
				return false;
			}
		}
		return true;
	}
	public static boolean isNumber(String str) {
		boolean isNumber=true;
		if(str!=null){
			try {
				Integer.parseInt(str);
			} catch (NumberFormatException e) {
				isNumber=false;
			}
		}else {
			isNumber=false;
		}
		return isNumber;
	}
}




















