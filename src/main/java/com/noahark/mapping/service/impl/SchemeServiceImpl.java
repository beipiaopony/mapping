package com.noahark.mapping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noahark.mapping.bean.Scheme;
import com.noahark.mapping.dao.ISchemeDao;
import com.noahark.mapping.service.ISchemeService;


@Service("schemeService")
public class SchemeServiceImpl implements ISchemeService {

	@Resource
	private ISchemeDao schemeDao;
	
	@Override
	public List<Scheme> getSchemeList() {
		// TODO Auto-generated method stub
		return schemeDao.querySchemes();
	}

}
