package org.gms.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class HttpBody {
	
	private static final Logger log = LoggerFactory.getLogger(HttpBody.class);
	
	private final boolean result;
	private final String message;
	private final Object data;
	
	public HttpBody(boolean result,String message,Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	public HttpBody(boolean result,String message) {
		this.result = result;
		this.message = message;
		this.data = null;
	}
	
	public ObjectNode toResult(){
		ObjectNode httpMessage = Constant.om.createObjectNode();
		ObjectNode header = Constant.om.createObjectNode();
		ObjectNode body = Constant.om.createObjectNode();
		setHeader(header);
		setBody(body);
		httpMessage.set("header", header);
		httpMessage.set("body", body);
		return httpMessage;
	}
	
	private void setHeader(ObjectNode header){
		header.put("result", result);
		header.put("message", message);
	}
	
	private void setBody(ObjectNode body) {
		if(data ==null){
			return;
		}
		try {
			String content = Constant.om.writeValueAsString(data);
			JsonNode jsonNode = Constant.om.readValue(content, JsonNode.class);
			body.set("data", jsonNode);
		} catch (IOException e) {
			log.error("", e);
			body.put("data", "");
			body.put("dataException", e.getMessage());
		}
	}
}