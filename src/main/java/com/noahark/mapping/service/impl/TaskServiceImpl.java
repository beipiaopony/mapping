package com.noahark.mapping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noahark.mapping.bean.TaskJob;
import com.noahark.mapping.dao.ITaskDao;
import com.noahark.mapping.service.ITaskService;
import com.noahark.mapping.util.Page;


@Service("taskService")
public class TaskServiceImpl implements ITaskService {

	@Resource
	private ITaskDao taskDao;
	
	@Override
	public void addTask(TaskJob task) {
		taskDao.insert(task);

	}

	@Override
	public Page<TaskJob> queryTaskList(int rows, int page, Map<String, Object> parameters) {
		int totalSize = 0;
		
		int start = (page - 1) * rows;
		int end = page * rows;
		
		parameters.put("START", start);
		parameters.put("END", end);
		
		System.out.println(parameters);
		
		List<TaskJob> list = taskDao.queryTaskList(parameters);
		totalSize = taskDao.countTasks(parameters);
		
		
		Page<TaskJob> result = new Page<TaskJob>(totalSize,list);
		
		return result;
	}

}
