package com.noahark.mapping.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.IntegrationAccMapping;
import com.noahark.mapping.bean.OtherDimMapper;
import com.noahark.mapping.service.IMappingService;
import com.noahark.mapping.util.ExcelUtil;
import com.noahark.mapping.util.Page;
import com.noahark.mapping.util.ReadExcel;

@Controller
@RequestMapping("metadata")
public class MetadataController extends BaseController {

	@Autowired
	private IMappingService mappingService;

	@RequestMapping(value = "/query/{appName}/{userName}", method = RequestMethod.GET)
	public ModelAndView queryMetadata(@PathVariable String appName, @PathVariable String userName) {

		ModelAndView mv = new ModelAndView("queryMetadata");

		return mv;
	}
	
	@RequestMapping(value = "/uploadResult/{appName}/{userName}", method = RequestMethod.GET)
	public ModelAndView queryResult(@PathVariable String appName, @PathVariable String userName) {
		
		ModelAndView mv = new ModelAndView("uploadFileResult");
		//mv.addObject("message", msg);
		
		return mv;
	}

	
	private void backupData(String detailType){
		FileOutputStream os = null;
		String path = "E:/mappingbck/" + new Date().getTime() + "_" + detailType + ".xls";
		try {
			os = new FileOutputStream(path);
			createExcelFile(detailType,os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void createExcelFile(String detailType, OutputStream os){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DETAIL", "");
		parameters.put("DIM", "");
		
		if (detailType.equals("Account")) {
			String columnNames[] = { "ERP科目编码", "ERP科目名称", "HFM科目编码", "HFM科目名称",
					"调整系数", /* "取数方式编码","取数方式名称", */
					/* "数据类型编码","数据类型名称","币种代码","币种名称", */"ICP编码(ERP)", "ICP名称(ERP)", "项目编码(ERP)", "项目名称(ERP)",
					"产品编码(ERP)", "产品名称(ERP)", "数据类型编码(ERP)", "数据类型名称(ERP)", "取数方式编码(ERP)", "取数方式名称(ERP)", "报表编码",
					"报表名称", "C1编码(ERP)", "C1名称(ERP)", "C2编码(ERP)", "C2名称(ERP)", "组织编码(HFM)", "ICP编码(HFM)",
					"custom1编码(HFM)", "custom2编码(HFM)", "custom3编码(HFM)", "custom4编码(HFM)", "年度编码(HFM)", "期间编码(HFM)",
					"情景编码(HFM)" };// 列名

			String keys[] = { "ACCOUNT_CODE_FROM", "ACCOUNT_NAME_FROM", "ACCOUNT_CODE_TO", "ACCOUNT_NAME_TO",
					"COEF", /* "DATA_FETCH", "DATA_FETCH_NAME", */
					/*
					 * "DATA_TYPE","DATA_TYPE_NAME","CURRENCY_TYPE",
					 * "CURRENCY_TYPE_NAME",
					 */"DETAIL_CODE1", "DETAIL_NAME1", "DETAIL_CODE2", "DETAIL_NAME2", "DETAIL_CODE3", "DETAIL_NAME3",
					"DETAIL_CODE4", "DETAIL_NAME4", "DETAIL_CODE5", "DETAIL_NAME5", "DETAIL_CODE6", "DETAIL_NAME6",
					"DETAIL_CODE7", "DETAIL_NAME7", "DETAIL_CODE8", "DETAIL_NAME8", "DIM_01", "DIM_02", "DIM_03",
					"DIM_04", "DIM_05", "DIM_06", "DIM_07", "DIM_08", "DIM_09" };// map中的key

			List<Map<String, Object>> list = mappingService.queryAllaccMapping(parameters);

			try {
				ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if(detailType.equals("InteAcc")) {
			String columnNames[] = { "一体化科目编码", "一体化科目名称", "HFM科目编码", "HFM科目名称" , "业务类别", "数据类型"};// 列名
			String keys[] = { "ERP_ACCOUNT", "ERP_ACCOUNT_NAME", "HFM_ACCOUNT","HFM_ACCOUNT_NAME" , "PLATE","DATATYPE"};// map中的key

			List<Map<String, Object>> list = mappingService.queryAllInteAccMapping(parameters);

			try {
				ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if(detailType.equals("InteProd")) {
			
			String columnNames[] = { "一体化产品编码", "一体化产品名称", "HFM产品编码", "HFM产品名称" };// 列名
			String keys[] = { "ERP_PRO_CODE", "ERP_PRO_NAME", "HFM_PRO_CODE","HFM_PRO_NAME" };// map中的key

			List<Map<String, Object>> list = mappingService.queryAllInteProdMapping(parameters);

			try {
				ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			String columnNames[] = { "ERP维度类型", "HFM维度类型", "ERP编码", "ERP名称", "HFM编码", "HFM名称" };// 列名
			String keys[] = { "DETAIL_TYPE", "DIM_TYPE", "DETAIL_CODE", "DETAIL_CODE_NAME", "DIM_CODE",
					"DIM_CODE_NAME" };// map中的key

			List<Map<String, Object>> list = mappingService.queryAlldimMapping(parameters);

			try {
				ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value = "/download/{detailType}/{dimType}")
	public String downloadDim(@PathVariable String detailType, @PathVariable String dimType, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileName = "excel文件";

		if (detailType.equals("Account")) {
			fileName = "科目-Mapping";
		} else if (detailType.equals("InteAcc")) {
			fileName = "一体化科目-Mapping";
		} else if (detailType.equals("InteProd")) {
			fileName = "一体化产品-Mapping";
		} else {
			fileName = "其他维度-Mapping";
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		createExcelFile(detailType,os);

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/query/inteAccMapping")
	public Page<IntegrationAccMapping> queryInteAccMapping(@RequestParam int page, @RequestParam int rows,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return mappingService.queryInteAccMapping(rows, page, parameters);
	}
	
	@ResponseBody
	@RequestMapping(value = "/query/intePordMapping")
	public Page<OtherDimMapper> queryInteProdMapping(@RequestParam int page, @RequestParam int rows,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return mappingService.queryInteProdMapping(rows, page, parameters);
	}
	
	@ResponseBody
	@RequestMapping(value = "/query/accMapping")
	public Page<AccountMapper> queryAccMapping(@RequestParam int page, @RequestParam int rows,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		return mappingService.queryAccMapping(rows, page, parameters);
	}

	@ResponseBody
	@RequestMapping(value = "/query/dimMapping/{detailType}/{dimType}")
	public Page<OtherDimMapper> queryDimMapping(@PathVariable String detailType, @PathVariable String dimType,
			@RequestParam int page, @RequestParam int rows, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		String dimMappingType = request.getParameter("dimMappingType");

		System.out.println("dimMappingType = " + dimMappingType);
		parameters.put("DETAIL", dimMappingType);
		parameters.put("DIM", null);

		return mappingService.queryDimMapping(rows, page, parameters);

	}

	@RequestMapping(value = "/query/{appName}/{userName}", method = RequestMethod.POST)
	public String fileUpload(@PathVariable String appName, @PathVariable String userName,
			@RequestParam("file") CommonsMultipartFile file, @RequestParam("detailType") String detailType,
			@RequestParam("dimType") String dimType, @RequestParam("updateType") String updateType, RedirectAttributes attr) throws IOException {

		System.out.println(detailType);
		System.out.println(dimType);
		System.out.println(updateType);

		String msg = "";

		if (file == null || file.getOriginalFilename() == null || file.getOriginalFilename().length() == 0) {
			msg = "请选择要导入的文件!";
		} else {
			try {
				long startTime = System.currentTimeMillis();
				// System.out.println("fileName：" + file.getOriginalFilename());
				String folder = System.getProperty("java.io.tmpdir");
				String path = folder + new Date().getTime() + file.getOriginalFilename();

				System.out.println("fileName：" + path);
				File newFile = new File(path);
				// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
				file.transferTo(newFile);

				backupData(detailType);
				
				if (detailType.equals("Account")) {					
					List<AccountMapper> list = ReadExcel.readAccExcel(path);
					if (updateType.equals("2")) {
						msg = mappingService.insertAccMapping(list);
					} else {
						msg = mappingService.updateAccMapping(list);
					}					
				} else if (detailType.equals("InteAcc")) {
					List<IntegrationAccMapping> list = ReadExcel.readInteAccExcel(path);
					if (updateType.equals("2")){
						msg = mappingService.insertInteAccMapping(list);
					} else {						
						msg = mappingService.updateInteAccMapping(list);
					}
					
				} else if (detailType.equals("InteProd")) {
					List<OtherDimMapper> list = ReadExcel.readInteProdExcel(path);
					if (updateType.equals("2")) {
						msg = mappingService.insertInteProdMapping(list);
					} else {
						msg = mappingService.updateInteProdMapping(list);
					}					
					
				} else {
					List<OtherDimMapper> list = ReadExcel.readDimExcel(path);
					if (updateType.equals("2")){
						msg = mappingService.insertDimMapping(list);
					} else {
						msg = mappingService.updateDimMapping(list);
					}
					
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}

		}

		attr.addFlashAttribute("message", msg);
		attr.addFlashAttribute("userName", userName);
		attr.addFlashAttribute("appName", appName);
		
	
		return "redirect:/metadata/uploadResult/" + appName + "/" + userName ;

	}

}
