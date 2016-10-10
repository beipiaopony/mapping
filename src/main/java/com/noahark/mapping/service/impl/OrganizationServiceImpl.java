package com.noahark.mapping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noahark.mapping.bean.Organization;
import com.noahark.mapping.dao.IOrganizationDao;
import com.noahark.mapping.service.IOrganizationService;


@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {

	@Resource
	private IOrganizationDao organizationDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Organization> getOrganizationList(Map parameter) {
		
		List<Organization> list  = organizationDao.queryOrganizations(parameter);		
		
		list.add(list.size(), new Organization(-1,"admin","all companies","all companies"));

		return list;
	}

	@Override
	public List<Organization> getOrganizationListByUser(Map parameter) {
		List<Organization> list  = organizationDao.queryOrgByUser(parameter);
/*		boolean flg = true;
		
		for (Organization org : list){
			if (org.getOrgCode().equalsIgnoreCase((String) parameter.get("userName"))){
				flg = false;
				break;
			}
		}
		
		if (flg) {
			list.add(0, new Organization(-1,(String) parameter.get("userName"),(String) parameter.get("userName"),(String) parameter.get("userName")));
		}*/
		
		return list;
	}

	@Override
	public List<Organization> getOrganizationListByAdmin(Map parameter) {
		List<Organization> list  = organizationDao.queryOrganizations(parameter);		
		
		list.add(0, new Organization(-1,"admin","admin","admin"));

		return list;
	}

}
