package com.noahark.mapping.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noahark.mapping.bean.Organization;
import com.noahark.mapping.bean.Scheme;
import com.noahark.mapping.bean.TaskJob;
import com.noahark.mapping.service.IOrganizationService;
import com.noahark.mapping.service.ISchemeService;
import com.noahark.mapping.service.ITaskService;
import com.noahark.mapping.service.IUserService;
import com.noahark.mapping.util.Page;

@Controller
@RequestMapping("interface")
public class UploadDataController extends BaseController {

	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ISchemeService schemeService;

	@Autowired
	private IOrganizationService organizationService;

	@RequestMapping(value = "/upload/{appName}/{userName}", method = RequestMethod.GET)
	public ModelAndView upload(@PathVariable String appName,
			@PathVariable String userName) {

		
		System.out.println("appName=" + appName);
		System.out.println("userName=" + userName);
		
		String user = getUserName(userName);
		String role = userService.getUserRole(appName, user);

		ModelAndView mv = new ModelAndView("uploadData");

		mv.addObject("userName", user);
		mv.addObject("appName", appName);
		mv.addObject("userRole", role);
		
		
		if (userService.isAdmin(role)){
			mv.addObject("uploadEntity", "admin");
			mv.addObject("ComBoxEditable","true");
		} else {
			mv.addObject("uploadEntity", user);
			mv.addObject("ComBoxEditable","false");
		}
		

		// throw new RuntimeException("ccc");
		return mv;
	}

	@RequestMapping(value = "/queryLog/{appName}/{userName}", method = RequestMethod.GET)
	public ModelAndView queryLog(@PathVariable String appName,@PathVariable String userName) {

		String user = getUserName(userName);

		String role = userService.getUserRole(appName, user);
		
		ModelAndView mv = new ModelAndView("queryTask");

		mv.addObject("userName", user);
		mv.addObject("appName", appName);
		mv.addObject("userRole", role);

		return mv;
	}

	public UploadDataController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "organizations/{appName}/{userName}")
	public List<Organization> listOrganizations(@PathVariable String appName,@PathVariable String userName) {

		
		String role = userService.getUserRole(appName, userName);
		
		//System.out.println("listOrganizations");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userName", userName);
		parameters.put("appName", appName);
		parameters.put("userRole", role);
		
		System.out.println("appName=" + appName);
		System.out.println("userName=" + userName);
		System.out.println("role=" + role);
		
		if (userService.isAdmin(role)){
			return organizationService.getOrganizationList(parameters);
		} else {
			return organizationService.getOrganizationListByUser(parameters);
		}
		
		
	}

	@ResponseBody
	@RequestMapping(value = "interfaceTables")
	public List<Scheme> listInterfaceTable() {

		return schemeService.getSchemeList();
	}

	@ResponseBody
	@RequestMapping(value = "/upload/{appName}/{userName}", method = RequestMethod.POST)
	public ModelAndView uploadData(@PathVariable String appName, @PathVariable String userName, HttpServletRequest request,
			HttpServletResponse response) {

		String user = getUserName(userName);
		String role = userService.getUserRole(appName, user);
		
		// System.out.println(userName);
		String entity = request.getParameter("uploadEntity");
		String year = request.getParameter("uploadYear");
		String month = request.getParameter("uploadMonth");
		String scenario = request.getParameter("uploadScenario");
		String[] tables = request.getParameterValues("uploadTables");
		String model = request.getParameter("uploadModel");
		

		for (int i = 0; i < tables.length; i++) {
			TaskJob job = new TaskJob();
			// job.setJob_id(job_id);
			job.setMap_set_id(Integer.valueOf(tables[i]));
			job.setCmd_id(5);
			job.setYear(year);
			job.setPeriod(month);
			job.setEntity(entity);
			job.setScenario(scenario);
			job.setUser(user);
			job.setStatus(0);
			job.setIp("10.1.104.43");
			job.setJob_start_time(new Date());
			job.setUser_id("b5903f63-6987-452d-848e-9ce3010dd544");
			job.setParams(model);
			job.setApplication(appName);
			taskService.addTask(job);

		}

		// flush(response,msg.getMessage());
		ModelAndView mv = new ModelAndView("uploadResult");
		mv.addObject("message", "上传成功，任务已开始。");
		mv.addObject("userName", user);
		mv.addObject("appName", appName);
		return mv;

	}

	@ResponseBody
	@RequestMapping(value = "/queryLog/queryData/{appName}/{userName}")
	public Page<TaskJob> queryTaskLog(@PathVariable String appName, @PathVariable String userName, @RequestParam int page, @RequestParam int rows, HttpServletRequest request,
			HttpServletResponse response) {

		String role = userService.getUserRole(appName, userName);
		
		String table = request.getParameter("queryTable");
		String year = request.getParameter("queryYear");
		String month = request.getParameter("queryMonth");
		String entity = request.getParameter("queryEntity");

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("appName",appName);
		parameters.put("userName",userName);
		parameters.put("all", 1);
		parameters.put("entity", (entity == null ? null:entity+"%"));
		parameters.put("year", year);
		parameters.put("month", month);
		parameters.put("userRole", role);
		
		if (table == null || table.equals("")){
			parameters.put("tableid", null);
		} else {
			parameters.put("tableid", Integer.valueOf(table));
		}

		return taskService.queryTaskList(rows, page, parameters);
	}

}
