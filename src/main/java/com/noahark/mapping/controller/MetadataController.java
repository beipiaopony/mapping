package com.noahark.mapping.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.noahark.mapping.bean.AccountMapper;
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

	@RequestMapping(value = "/download/{detailType}/{dimType}")
	public String downloadDim(@PathVariable String detailType, @PathVariable String dimType, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileName = "excel文件";

		if (detailType.equals("Detail_01")) {
			fileName = "组织-Mapping";
		} else if (detailType.equals("Detail_02")) {
			fileName = "ICP-Mapping";
		} else if (detailType.equals("Detail_03")) {
			fileName = "项目-Mapping";
		} else if (detailType.equals("Detail_04")) {
			fileName = "产品-Mapping";
		} else if (detailType.equals("ACCOUNT")) {
			fileName = "科目-Mapping";
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DETAIL", detailType);
		parameters.put("DIM", dimType);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		if (detailType.equals("ACCOUNT")) {
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
		parameters.put("DETAIL", detailType);
		parameters.put("DIM", dimType);

		return mappingService.queryDimMapping(rows, page, parameters);

	}

	@RequestMapping(value = "/query/{appName}/{userName}", method = RequestMethod.POST)
	public ModelAndView fileUpload(@PathVariable String appName, @PathVariable String userName,
			@RequestParam("file") CommonsMultipartFile file, @RequestParam("detailType") String detailType,
			@RequestParam("dimType") String dimType) throws IOException {

		System.out.println(detailType);
		System.out.println(dimType);

		String msg = "";
		
		try {
			long startTime = System.currentTimeMillis();
			// System.out.println("fileName：" + file.getOriginalFilename());
			String folder = System.getProperty("java.io.tmpdir");
			String path = folder + new Date().getTime() + file.getOriginalFilename();

			System.out.println("fileName：" + path);
			File newFile = new File(path);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);

			if (detailType.equals("account")) {
				List<AccountMapper> list = ReadExcel.readAccExcel(path);
				msg = mappingService.updateAccMapping(list);
			} else {

				List<OtherDimMapper> list = ReadExcel.readDimExcel(path);
				msg = mappingService.updateDimMapping(list);
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}

		
		ModelAndView mv = new ModelAndView("uploadFileResult");
		mv.addObject("message", msg);
		mv.addObject("userName", userName);
		mv.addObject("appName", appName);
		return mv;

	}

}
