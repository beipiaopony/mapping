package com.noahark.mapping.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noahark.mapping.bean.Scheme;

@Repository("schemeDao")
public interface ISchemeDao {
	
	public List<Scheme> querySchemes();

}
