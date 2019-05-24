package xin.ss.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeGenerator {

	public static String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
