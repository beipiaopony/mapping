package com.noahark.mapping.service;


import java.util.List;
import java.util.Map;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.OtherDimMapper;
import com.noahark.mapping.util.Page;

public interface IMappingService {

	public AccountMapper checkAccMapping(String accFromCode, String accToCode);
	
	public boolean checkAccMappingId(AccountMapper mapping);
	
	public int getTotalAccMappingCount();
	
	public Page<AccountMapper> queryAccMapping(int rows, int page, Map<String, Object> parameters);
	
	public void updateAccMapping(AccountMapper mapping);
	
	public void insertAccMapping(AccountMapper mapping);
	
	
	public boolean checkDimMappingId(OtherDimMapper mapping);
	
	public OtherDimMapper checkDimMapping(String detailType, String dimType, String code);
	
	public int getTotalDimMappingCount(String detailType, String dimType);
	
	public Page<OtherDimMapper> queryDimMapping(int rows, int page, Map<String, Object> parameters);
	
	public void updateDimMapping(OtherDimMapper mapping);
	
	public void insertDimMapping(OtherDimMapper mapping);
	
	public String updateAccMapping(List<AccountMapper> list);
	
	public String updateDimMapping(List<OtherDimMapper> list);
	
	public List<Map<String,Object>>  queryAllaccMapping(Map<String, Object> parameters);
	public List<Map<String,Object>> queryAlldimMapping(Map<String, Object> parameters);
	
}
