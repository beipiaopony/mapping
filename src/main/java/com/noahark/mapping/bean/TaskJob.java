package com.noahark.mapping.bean;

import java.util.Date;

public class TaskJob {
	
	private int job_id;
	private int map_set_id;
	private String map_set_name;
	private int dependency_job_id;
	private int cmd_id;
	private String cmd_script;
	private String year;
	private String period;
	private String user;
	private String params;
	private String application;
	private String user_id;
	private int status;
	private String ip;
	private Date job_start_time;
	private String start_time;
	private String end_time;
	private String entity;
	private String scenario;
	private String view;
	private String message;
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public int getMap_set_id() {
		return map_set_id;
	}
	public void setMap_set_id(int map_set_id) {
		this.map_set_id = map_set_id;
	}
	public String getMap_set_name() {
		return map_set_name;
	}
	public void setMap_set_name(String map_set_name) {
		this.map_set_name = map_set_name;
	}
	public int getDependency_job_id() {
		return dependency_job_id;
	}
	public void setDependency_job_id(int dependency_job_id) {
		this.dependency_job_id = dependency_job_id;
	}
	public int getCmd_id() {
		return cmd_id;
	}
	public void setCmd_id(int cmd_id) {
		this.cmd_id = cmd_id;
	}
	public String getCmd_script() {
		return cmd_script;
	}
	public void setCmd_script(String cmd_script) {
		this.cmd_script = cmd_script;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getJob_start_time() {
		return job_start_time;
	}
	public void setJob_start_time(Date job_start_time) {
		this.job_start_time = job_start_time;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
	
	
	

}
