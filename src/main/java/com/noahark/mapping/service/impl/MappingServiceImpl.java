package com.noahark.mapping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.OtherDimMapper;
import com.noahark.mapping.dao.IMappingDao;
import com.noahark.mapping.service.IMappingService;
import com.noahark.mapping.util.Page;


@Service("mappingService")
public class MappingServiceImpl implements IMappingService {
	
	@Resource
	private IMappingDao mappingDao;
	

	@Override
	public AccountMapper checkAccMapping(String accFromCode, String accToCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("FROM_ACCOUNT", accFromCode);
		map.put("TO_ACCOUNT", accToCode);
		return mappingDao.querySingleAccMapping(map);
	}

	@Override
	public boolean checkAccMappingId(AccountMapper mapping) {
		AccountMapper tmp = checkAccMapping(mapping.getFromAccountCode(), mapping.getToAccountCode());
		if (tmp != null){
			mapping.setMapId(tmp.getMapId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getTotalAccMappingCount() {
		return mappingDao.countAccountMapping();
	}

	@Override
	public Page<AccountMapper> queryAccMapping(int rows, int page, Map<String, Object> parameters) {
		int totalSize = 0;
		
		int start = (page - 1) * rows;
		int end = page * rows;
		
		parameters.put("START", start);
		parameters.put("END", end);
		
		totalSize = mappingDao.countAccountMapping();
		List<AccountMapper> list = mappingDao.queryAccountMapping(parameters);
		
		Page<AccountMapper> result = new Page<AccountMapper>(totalSize,list);
		
		return result;
	}

	@Override
	public void updateAccMapping(AccountMapper mapping) {
		this.mappingDao.updateAccountMapping(mapping);
		
	}

	@Override
	public void insertAccMapping(AccountMapper mapping) {
		this.mappingDao.insertAccountMapping(mapping);
		
	}

	@Override
	public boolean checkDimMappingId(OtherDimMapper mapping) {
		OtherDimMapper tmp = checkDimMapping(mapping.getDetailType(), mapping.getDimType(), mapping.getDetailCode());
		if (tmp != null){
			mapping.setMapId(tmp.getMapId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public OtherDimMapper checkDimMapping(String detailType, String dimType, String code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("DETAIL", detailType);
		map.put("DIM", dimType);
		map.put("CODE", code);
		return this.mappingDao.querySingleDimMapping(map);
	}

	@Override
	public int getTotalDimMappingCount(String detailType, String dimType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("DETAIL", detailType);
		map.put("DIM", dimType);
		return this.mappingDao.countOtherDimMapping(map);
	}

	@Override
	public Page<OtherDimMapper> queryDimMapping(int rows, int page, Map<String, Object> parameters) {
		int totalSize = 0;		
		int start = (page - 1) * rows;
		int end = page * rows;
		
		parameters.put("START", start);
		parameters.put("END", end);		
	
		totalSize = mappingDao.countOtherDimMapping(parameters);
		
		List<OtherDimMapper> list = mappingDao.queryOtherDimMapping(parameters);
		
		Page<OtherDimMapper> result = new Page<OtherDimMapper>(totalSize,list);
		return result;
	}

	@Override
	public void updateDimMapping(OtherDimMapper mapping) {
		this.mappingDao.updateOtherDimMapping(mapping);
		
	}

	@Override
	public void insertDimMapping(OtherDimMapper mapping) {
		this.mappingDao.insertOtherDimMapping(mapping);
		
	}

	@Override
	public List<Map<String,Object>> queryAllaccMapping(Map<String, Object> parameters) {
		return mappingDao.queryAccountMappingAll(parameters);
	}

	@Override
	public List<Map<String,Object>> queryAlldimMapping(Map<String, Object> parameters) {
		return mappingDao.queryOtherDimMappingAll(parameters);
	}

	@Override
	public String updateAccMapping(List<AccountMapper> list) {
		int j = 0;
		try{
			
			for (AccountMapper acc : list){
				j++;
				
				if (checkAccMappingId(acc)){
					checkAccMappingId(acc);
				} else {
					insertAccMapping(acc);
				}
				
				
			}
			
			return "成功导入" + j + "行.";
		} catch (Exception e){
			return "第" + j + "行，发生错误" + e.getMessage();
		}
		
	}

	@Override
	public String updateDimMapping(List<OtherDimMapper> list) {
		int j = 0;
		
		try{
			for (OtherDimMapper dim : list) {
				j++;
				
				if (checkDimMappingId(dim)){
					this.updateDimMapping(dim);
				} else {
					this.insertDimMapping(dim);
				}
			}
			
			return "成功导入" + j + "行.";
			
		} catch (Exception e) {
			
			return "第" + j + "行，发生错误" + e.getMessage();
		}
	}

}
