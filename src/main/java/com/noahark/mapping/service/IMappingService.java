package com.noahark.mapping.service;


import java.util.List;
import java.util.Map;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.IntegrationAccMapping;
import com.noahark.mapping.bean.OtherDimMapper;
import com.noahark.mapping.util.Page;

public interface IMappingService {

	//account mapping
	public AccountMapper checkAccMapping(String accFromCode, String accToCode, String table);	
	public boolean checkAccMappingId(AccountMapper mapping);	
	public int getTotalAccMappingCount();	
	public Page<AccountMapper> queryAccMapping(int rows, int page, Map<String, Object> parameters);	
	public void updateAccMapping(AccountMapper mapping);	
	public void insertAccMapping(AccountMapper mapping);
	public String updateAccMapping(List<AccountMapper> list);	
	public String insertAccMapping(List<AccountMapper> list);
	public void removeAccMapping();
		
	
	//other mapping
	public boolean checkDimMappingId(OtherDimMapper mapping);
	public OtherDimMapper checkDimMapping(String detailType, String dimType, String code);
	public int getTotalDimMappingCount(String detailType, String dimType);
	public Page<OtherDimMapper> queryDimMapping(int rows, int page, Map<String, Object> parameters);
	public void updateDimMapping(OtherDimMapper mapping);
	public void insertDimMapping(OtherDimMapper mapping);
	public String updateDimMapping(List<OtherDimMapper> list);
	public String insertDimMapping(List<OtherDimMapper> list);
	public void removeDimMapping();
	
	
	//InteProdMapping
	public OtherDimMapper checkInteProdMapping(String productCode);
	public boolean checkInteProdMapping(OtherDimMapper mapping);
	public int queryTotalInteProdMappingCount();
	public Page<OtherDimMapper> queryInteProdMapping(int rows, int page, Map<String, Object> parameters);
	public void updateInteProdMapping(OtherDimMapper mapping);
	public void insertInteProdMapping(OtherDimMapper mapping);
	public String updateInteProdMapping(List<OtherDimMapper> list);
	public String insertInteProdMapping(List<OtherDimMapper> list);
	public void removeInteProdMapping();
	
	//InteAccMapping
	//public IntegrationAccMapping checkInteAccMapping(IntegrationAccMapping mapping);
	public int queryTotalInteAccMappingCount();
	public boolean checkInteAccMappingExists(IntegrationAccMapping mapping);
	public Page<IntegrationAccMapping> queryInteAccMapping(int rows, int page, Map<String, Object> parameters);
	public void insertInteAccMapping(IntegrationAccMapping mapping);
	public void updateInteAccMapping(IntegrationAccMapping mapping);
	public String insertInteAccMapping(List<IntegrationAccMapping> list);
	public String updateInteAccMapping(List<IntegrationAccMapping> list);
	public void removeInteAccMapping();	
	
	//query
	public List<Map<String,Object>> queryAllaccMapping(Map<String, Object> parameters);
	public List<Map<String,Object>> queryAlldimMapping(Map<String, Object> parameters);
	public List<Map<String,Object>> queryAllInteProdMapping(Map<String, Object> parameters);
	public List<Map<String,Object>> queryAllInteAccMapping(Map<String, Object> parameters);
	
	
}
