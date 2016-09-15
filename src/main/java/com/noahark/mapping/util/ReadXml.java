package com.noahark.mapping.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ReadXml {

	
	public static void readXml(String xml, Map<String,String> map){
		//Map<String,String> map = new HashMap<String,String>();
		
		try {
			Document document = DocumentHelper.parseText(xml);
			
			Element root = document.getRootElement();
			
			Element token=root.element("token");
			if (token != null){
				map.put("token", StringUtils.remove(token.getText(),"\n"));				
			}
			
			Element user=root.element("user");
			if (user != null){
				map.put("user", user.getTextTrim());				
			}
			
			Element assertertoken=root.element("assertertoken");
			if (assertertoken != null){
				map.put("assertertoken", assertertoken.getTextTrim());				
			}
			
			
			Element desc=root.element("desc");
			if (desc != null){
				map.put("desc", desc.getTextTrim());				
			}
			
			Element code=root.element("code");
			if (code != null){
				map.put("code", code.getTextTrim());				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//return map;
		
	}
}
