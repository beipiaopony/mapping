package com.noahark.mapping.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noahark.mapping.bean.Organization;

@Repository("organizationDao")
public interface IOrganizationDao {

	@SuppressWarnings("rawtypes")
	public List<Organization> queryOrganizations(Map parameter);
	
	@SuppressWarnings("rawtypes")
	public List<Organization> queryOrgByUser(Map parameter);
	
	@SuppressWarnings("rawtypes")
	public List<Organization> queryOrgByAdmin(Map parameter);
}
