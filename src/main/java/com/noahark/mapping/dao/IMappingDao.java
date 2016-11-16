package com.noahark.mapping.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.IntegrationAccMapping;
import com.noahark.mapping.bean.OtherDimMapper;

@Repository("mappingDao")
public interface IMappingDao {

	public AccountMapper querySingleAccMapping(Map map);
	public int countAccountMapping();
	public List<AccountMapper> queryAccountMapping(Map map);
	public List<Map<String,Object>> queryAccountMappingAll(Map map);
	public void updateAccountMapping(AccountMapper mapping);
	public void insertAccountMapping(AccountMapper mapping);
	
	public void removeAccMapping();
	
	public OtherDimMapper querySingleDimMapping(Map map);
	public int countOtherDimMapping(Map map);
	public List<OtherDimMapper> queryOtherDimMapping(Map map);
	public List<Map<String,Object>> queryOtherDimMappingAll(Map map);
	public void updateOtherDimMapping(OtherDimMapper mapping);
	public void insertOtherDimMapping(OtherDimMapper mapping);
	public void removeDimMapping();
	
		
	public void removeInteProdMapping();
	public OtherDimMapper querySingleInteProdMapping(Map map);
	public int countInteProdMapping(Map map);
	public List<OtherDimMapper> queryInteProdMapping(Map map);
	public List<Map<String,Object>> queryAllInteProdMapping(Map map);	
	public void insertInteProdMapping(OtherDimMapper mapping);
	public void updateInteProdMapping(OtherDimMapper mapping);
	
	public void removeInteAccMapping();
	public IntegrationAccMapping querySingleInteAccMapping(IntegrationAccMapping inteAcc);
	public void updateInteAccMapping(IntegrationAccMapping inteAcc);
	public void insertInteAccMapping(IntegrationAccMapping inteAcc);
	public int countInteAccMapping(Map map);
	public List<IntegrationAccMapping> queryInteAccMapping(Map map);
	public List<Map<String,Object>> queryAllInteAccMapping(Map map);
	
	
	
}
