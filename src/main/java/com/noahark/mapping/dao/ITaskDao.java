package com.noahark.mapping.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noahark.mapping.bean.TaskJob;

@Repository("taskDao")
public interface ITaskDao {
	
	
	@SuppressWarnings("rawtypes")
	public int countTasks(Map map);
	
	public void insert(TaskJob task);
	
	@SuppressWarnings("rawtypes")
	public List<TaskJob> queryTaskList(Map map);

}
