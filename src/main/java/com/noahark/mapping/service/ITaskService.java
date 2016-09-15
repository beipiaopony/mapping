package com.noahark.mapping.service;

import java.util.Map;

import com.noahark.mapping.bean.TaskJob;
import com.noahark.mapping.util.Page;

public interface ITaskService {

	public void addTask(TaskJob task);
	

	public Page<TaskJob> queryTaskList(int rows, int page, Map<String, Object> parameters);
	
}
