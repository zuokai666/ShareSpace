package org.gms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class HttpBody {
	
	private final boolean result;
	private final String message;
	private final Object data;
	
	public HttpBody(boolean result,String message,Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public ObjectNode toResult() throws JsonProcessingException{
		ObjectNode httpMessage = Constant.om.createObjectNode();
		ObjectNode header = Constant.om.createObjectNode();
		ObjectNode body = Constant.om.createObjectNode();
		setHeader(header);
		setBody(body);
		httpMessage.set("header", header);
		httpMessage.set("body", body);
		return httpMessage;
	}
	
	public ObjectNode toFailResult(){
		ObjectNode httpMessage = Constant.om.createObjectNode();
		ObjectNode header = Constant.om.createObjectNode();
		ObjectNode body = Constant.om.createObjectNode();
		setHeader(header);
		httpMessage.set("header", header);
		httpMessage.set("body", body);
		return httpMessage;
	}
	
	private void setHeader(ObjectNode header){
		header.put("result", result);
		header.put("message", message);
	}
	
	private void setBody(ObjectNode body) throws JsonProcessingException{
		body.put("data", Constant.om.writeValueAsString(data));
	}
}