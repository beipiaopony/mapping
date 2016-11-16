package com.noahark.mapping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.IntegrationAccMapping;
import com.noahark.mapping.bean.OtherDimMapper;
import com.noahark.mapping.dao.IMappingDao;
import com.noahark.mapping.service.IMappingService;
import com.noahark.mapping.util.Page;

@Service("mappingService")
public class MappingServiceImpl implements IMappingService {

	@Resource
	private IMappingDao mappingDao;

	@Override
	public AccountMapper checkAccMapping(String accFromCode, String accToCode, String table) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("FROM_ACCOUNT", accFromCode);
		map.put("TO_ACCOUNT", accToCode);
		map.put("MAP_TABLE", table);

		return mappingDao.querySingleAccMapping(map);
	}

	private void checkAccMapping(AccountMapper mapping){
		if (mapping.getFromAccountCode() == null || mapping.getFromAccountCode().length() == 0) {
			throw new RuntimeException("ERP科目编码为空。");
		} else if (mapping.getToAccountCode() == null || mapping.getToAccountCode().length() == 0) {
			throw new RuntimeException("HFM科目编码为空。");
		} else if (mapping.getDetailCode6() == null || mapping.getDetailCode6().length() == 0) {
			throw new RuntimeException("表名为空。");
		}
	}
	
	@Override
	public boolean checkAccMappingId(AccountMapper mapping) {
		if (mapping == null) {
			throw new RuntimeException("数据为空。");
		} else {
			checkAccMapping(mapping);
		}

		AccountMapper tmp = checkAccMapping(mapping.getFromAccountCode(), mapping.getToAccountCode(),
				mapping.getDetailCode6());
		if (tmp != null) {
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

		Page<AccountMapper> result = new Page<AccountMapper>(totalSize, list);

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

	private void checkDimMapping(OtherDimMapper mapping){
		if (mapping.getDetailType() == null || mapping.getDetailType().length() == 0) {
			throw new RuntimeException("源数据维度类型为空。");
		} else if (mapping.getDimType() == null || mapping.getDimType().length() == 0) {
			throw new RuntimeException("HFM维度类型为空。");
		} else if (mapping.getDetailCode() == null || mapping.getDetailCode().length() == 0) {
			throw new RuntimeException("源数据维度编码为空。");
		} else if (mapping.getDimCode() == null || mapping.getDimCode().length() == 0) {
			throw new RuntimeException("HFM维度编码为空。");
		}
	}
	
	private void valideInteProdMapping(OtherDimMapper mapping){
		if (mapping.getDetailCode() == null || mapping.getDetailCode().length() == 0) {
			throw new RuntimeException("一体化产品编码为空。");
		} else if (mapping.getDimCode() == null || mapping.getDimCode().length() == 0) {
			throw new RuntimeException("HFM产品编码为空。");
		}
	}
	
	@Override
	public boolean checkDimMappingId(OtherDimMapper mapping) {
		if (mapping == null) {
			throw new RuntimeException("数据为空。");
		} else {
			checkDimMapping(mapping);
		} 

		OtherDimMapper tmp = checkDimMapping(mapping.getDetailType(), mapping.getDimType(), mapping.getDetailCode());
		if (tmp != null) {
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

		Page<OtherDimMapper> result = new Page<OtherDimMapper>(totalSize, list);
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
	public List<Map<String, Object>> queryAllaccMapping(Map<String, Object> parameters) {
		return mappingDao.queryAccountMappingAll(parameters);
	}

	@Override
	public List<Map<String, Object>> queryAlldimMapping(Map<String, Object> parameters) {
		return mappingDao.queryOtherDimMappingAll(parameters);
	}

	@Override
	public String updateAccMapping(List<AccountMapper> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		int j = 0;

		try {

			for (AccountMapper acc : list) {
				j++;

				if (checkAccMappingId(acc)) {
					this.updateAccMapping(acc);
				} else {
					this.insertAccMapping(acc);
				}

			}

			return "成功导入" + j + "行.";
		} catch (Exception e) {
			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}

	}

	@Override
	public String updateDimMapping(List<OtherDimMapper> list) {

		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		int j = 0;

		try {
			for (OtherDimMapper dim : list) {
				j++;

				if (checkDimMappingId(dim)) {
					this.updateDimMapping(dim);
				} else {
					this.insertDimMapping(dim);
				}
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public void removeAccMapping() {
		mappingDao.removeAccMapping();

	}

	@Override
	public String insertAccMapping(List<AccountMapper> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		int j = 0;

		removeAccMapping();
		
		try {

			for (AccountMapper acc : list) {
				j++;
				checkAccMapping(acc);
				this.insertAccMapping(acc);

			}

			return "成功导入" + j + "行.";
		} catch (Exception e) {
			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public String insertDimMapping(List<OtherDimMapper> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		removeDimMapping();
		
		int j = 0;

		try {
			for (OtherDimMapper dim : list) {
				j++;

				checkDimMapping(dim);
				this.insertDimMapping(dim);
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public void removeDimMapping() {
		this.mappingDao.removeDimMapping();
		
	}

	@Override
	public OtherDimMapper checkInteProdMapping(String productCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PRODUCT", productCode);
		return this.mappingDao.querySingleInteProdMapping(map);
	}

	@Override
	public boolean checkInteProdMapping(OtherDimMapper mapping) {
		OtherDimMapper tmp = checkInteProdMapping(mapping.getDetailCode());
		if (tmp!= null){
			return true;
		} else {
			return false;
		}		
	}

	@Override
	public int queryTotalInteProdMappingCount() {
		return this.mappingDao.countInteProdMapping(null);
	}

	@Override
	public Page<OtherDimMapper> queryInteProdMapping(int rows, int page, Map<String, Object> parameters) {
		int totalSize = 0;
		int start = (page - 1) * rows;
		int end = page * rows;

		parameters.put("START", start);
		parameters.put("END", end);

		totalSize = queryTotalInteProdMappingCount();

		List<OtherDimMapper> list = mappingDao.queryInteProdMapping(parameters);

		Page<OtherDimMapper> result = new Page<OtherDimMapper>(totalSize, list);
		return result;
	}

	@Override
	public void updateInteProdMapping(OtherDimMapper mapping) {
		this.mappingDao.updateInteProdMapping(mapping);
		
	}

	@Override
	public void insertInteProdMapping(OtherDimMapper mapping) {
		this.mappingDao.insertInteProdMapping(mapping);
		
	}

	@Override
	public String updateInteProdMapping(List<OtherDimMapper> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		int j = 0;

		try {
			for (OtherDimMapper dim : list) {
				j++;

				valideInteProdMapping(dim);
				
				if (checkInteProdMapping(dim)) {
					this.updateInteProdMapping(dim);
				} else {
					this.insertInteProdMapping(dim);
				}
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public String insertInteProdMapping(List<OtherDimMapper> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		removeInteProdMapping();
		
		int j = 0;

		try {
			for (OtherDimMapper dim : list) {
				j++;

				valideInteProdMapping(dim);				
				this.insertInteProdMapping(dim);
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public void removeInteProdMapping() {
		this.mappingDao.removeInteProdMapping();		
	}

	@Override
	public int queryTotalInteAccMappingCount() {
		return this.mappingDao.countInteAccMapping(null);
	}

	@Override
	public Page<IntegrationAccMapping> queryInteAccMapping(int rows, int page, Map<String, Object> parameters) {
		int totalSize = 0;
		int start = (page - 1) * rows;
		int end = page * rows;

		parameters.put("START", start);
		parameters.put("END", end);

		totalSize = queryTotalInteAccMappingCount();

		List<IntegrationAccMapping> list = mappingDao.queryInteAccMapping(parameters);

		Page<IntegrationAccMapping> result = new Page<IntegrationAccMapping>(totalSize, list);
		return result;
	}

	@Override
	public void insertInteAccMapping(IntegrationAccMapping mapping) {
		this.mappingDao.insertInteAccMapping(mapping);
		
	}

	private void checkInteAccMapping(IntegrationAccMapping mapping){
		if (mapping.getErpAccountCode() == null || mapping.getErpAccountCode().length() == 0) {
			throw new RuntimeException("一体化科目编码为空。");
		} else if (mapping.getHfmAccountCode() == null || mapping.getHfmAccountCode().length() == 0) {
			throw new RuntimeException("HFM科目编码为空。");
		} else if (mapping.getPlate() == null || mapping.getPlate().length() == 0) {
			throw new RuntimeException("业务类型为空。");
		} else if (mapping.getDataType() == null || mapping.getDataType().length() == 0) {
			throw new RuntimeException("数据类型为空。");
		}
	}
	
	@Override
	public String updateInteAccMapping(List<IntegrationAccMapping> list) {
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		int j = 0;

		try {
			
			for (IntegrationAccMapping dim : list) {
				j++;
				checkInteAccMapping(dim);
				if (!checkInteAccMappingExists(dim)) {
					this.insertInteAccMapping(dim);
				} else {
					this.updateInteAccMapping(dim);
				}
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public void removeInteAccMapping() {
		this.mappingDao.removeInteAccMapping();		
	}

	@Override
	public List<Map<String, Object>> queryAllInteProdMapping(Map<String, Object> parameters) {
		return this.mappingDao.queryAllInteProdMapping(parameters);
	}

	@Override
	public List<Map<String, Object>> queryAllInteAccMapping(Map<String, Object> parameters) {
		return this.mappingDao.queryAllInteAccMapping(parameters);
	}

	@Override
	public String insertInteAccMapping(List<IntegrationAccMapping> list) {
		
		if (list == null || list.size() == 0) {
			return "文件无有效的数据。";
		}

		removeInteAccMapping();
		
		int j = 0;

		try {
			for (IntegrationAccMapping dim : list) {
				j++;

				checkInteAccMapping(dim);				
				this.insertInteAccMapping(dim);
			}

			return "成功导入" + j + "行.";

		} catch (Exception e) {

			throw new RuntimeException("第" + j + "行，发生错误" + e.getMessage());
		}
	}

	@Override
	public boolean checkInteAccMappingExists(IntegrationAccMapping mapping) {
		IntegrationAccMapping tmp = this.mappingDao.querySingleInteAccMapping(mapping);
		if (tmp == null){
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public void updateInteAccMapping(IntegrationAccMapping mapping) {
		this.mappingDao.updateInteAccMapping(mapping);
		
	}

}
