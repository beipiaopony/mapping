package com.noahark.mapping.service;

import java.util.List;
import java.util.Map;

import com.noahark.mapping.bean.Organization;

public interface IOrganizationService {

	@SuppressWarnings("rawtypes")
	public List<Organization> getOrganizationList(Map parameter);
	
	public List<Organization> getOrganizationListByUser(Map parameter);
	
	public List<Organization> getOrganizationListByAdmin(Map parameter);
	
}
