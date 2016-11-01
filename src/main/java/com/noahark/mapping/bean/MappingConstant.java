package com.noahark.mapping.bean;

import java.util.HashMap;
import java.util.Map;

public class MappingConstant {

	public static Map<String, Integer> Data_Fetch = new HashMap<String, Integer>();
	public static Map<String, Integer> Data_Type = new HashMap<String, Integer>();
	public static Map<String, Integer> Currency_Type = new HashMap<String, Integer>();
	
	static{
		
		Currency_Type.put("本位币", 0);
		Currency_Type.put("原币", 1);
		
		Data_Type.put("金额", 0);
		Data_Type.put("数量", 1);
		Data_Type.put("单价", 2);
		
		Data_Fetch.put("期初余额", 0);
		Data_Fetch.put("借方发生额", 1);
		Data_Fetch.put("贷方发生额", 2);
		Data_Fetch.put("期末余额", 3);
		Data_Fetch.put("借方-贷方发生额", 4);
		Data_Fetch.put("贷方-借方发生额", 5);
		
	}
	
	public static int getCurrencyTypeId(String name){
		if (!Currency_Type.containsKey(name)){
			throw new RuntimeException("\"" +name + "\" 币种名称错误.");
		}
		
		return Currency_Type.get(name);
	}
	
	public static int getDataTypeId(String name){
		if (!Data_Type.containsKey(name)){
			throw new RuntimeException("\"" +name + "\" 数据类型名称错误.");
		}
		
		return Data_Type.get(name);
	}
	public static int getDataFetchId(String name){
		if (!Data_Fetch.containsKey(name)){
			throw new RuntimeException("\"" +name + "\" 取数方式名称错误.");
		}
		return Data_Fetch.get(name);
	}
}
