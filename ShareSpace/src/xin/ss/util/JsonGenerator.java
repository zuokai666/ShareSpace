package xin.ss.util;

import java.util.List;

import xin.ss.model.*;


public class JsonGenerator {

	public static String getJson(List<? extends AbstarctEntity> entityList){
		if(entityList==null){
			return "[]";
		}
		StringBuilder result=new StringBuilder();
		result.append("[");
		for(int i=0;i<entityList.size();i++){
			result.append(entityList.get(i).toJson());
			if(i!=(entityList.size()-1)){
				result.append(",");
			}
		}
		result.append("]");
		return result.toString();
	}
}
