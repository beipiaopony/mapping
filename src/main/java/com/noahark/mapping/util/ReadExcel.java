package com.noahark.mapping.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noahark.mapping.bean.AccountMapper;
import com.noahark.mapping.bean.IntegrationAccMapping;
import com.noahark.mapping.bean.OtherDimMapper;

public class ReadExcel {
	
	public static List<IntegrationAccMapping> readInteAccExcel(String path) throws IOException{
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = getPostfix(path);
			
			if (!Common.EMPTY.equals(postfix)) {
				
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readInteAccXls(path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readInteAccXlsx(path);
				}
			} else {
				
				System.out.println(path + Common.NOT_EXCEL_FILE);
				
				return null;
			}
		}
		
		return null;
	}	
	
	public static List<OtherDimMapper> readInteProdExcel(String path) throws IOException{
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = getPostfix(path);
			
			if (!Common.EMPTY.equals(postfix)) {
				
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readInteProdXls(path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readInteProdXlsx(path);
				}
			} else {
				
				System.out.println(path + Common.NOT_EXCEL_FILE);
				
				return null;
			}
		}
		
		return null;
	}
	
	public static List<OtherDimMapper> readDimExcel(String path) throws IOException{
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = getPostfix(path);
			
			if (!Common.EMPTY.equals(postfix)) {
				
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readDimXls(path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readDimXlsx(path);
				}
			} else {
				
				System.out.println(path + Common.NOT_EXCEL_FILE);
				
				return null;
			}
		}
		
		return null;
	}
	
	public static List<AccountMapper> readAccExcel(String path) throws IOException {
		
		if (path == null || Common.EMPTY.equals(path)) {
			
			return null;
			
		} else {
			String postfix = getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readAccXls(path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readAccXlsx(path);
				}
			} else {
				System.out.println(path + Common.NOT_EXCEL_FILE);
				return null;
			}
		}
		
		return null;

	}

	public static String getPostfix(String path) {
		if (path == null || Common.EMPTY.equals(path.trim())) {
			return Common.EMPTY;
		}
		if (path.contains(Common.POINT)) {
			return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
		}
		return Common.EMPTY;
	}

	public static List<AccountMapper> readAccXlsx(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		AccountMapper acc;

		List<AccountMapper> list = new ArrayList<AccountMapper>();

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				
				String col1 = getValue(xssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				acc = new AccountMapper();

				acc.setFromAccountCode(col1);
				acc.setFromAccountName(getValue(xssfRow.getCell(1)));

				acc.setToAccountCode(getValue(xssfRow.getCell(2)));
				acc.setToAccountName(getValue(xssfRow.getCell(3)));

				acc.setCoef(Integer.valueOf(getValue(xssfRow.getCell(4))));

				acc.setDetailCode1(getValue(xssfRow.getCell(5)));
				acc.setDetailName1(getValue(xssfRow.getCell(6)));
				acc.setDetailCode2(getValue(xssfRow.getCell(7)));
				acc.setDetailName2(getValue(xssfRow.getCell(8)));
				acc.setDetailCode3(getValue(xssfRow.getCell(9)));
				acc.setDetailName3(getValue(xssfRow.getCell(10)));
				acc.setDetailCode4(getValue(xssfRow.getCell(11)));
				acc.setDetailName4(getValue(xssfRow.getCell(12)));
				acc.setDetailCode5(getValue(xssfRow.getCell(13)));
				acc.setDetailName5(getValue(xssfRow.getCell(14)));
				acc.setDetailCode6(getValue(xssfRow.getCell(15)));
				acc.setDetailName6(getValue(xssfRow.getCell(16)));
				acc.setDetailCode7(getValue(xssfRow.getCell(17)));
				acc.setDetailName7(getValue(xssfRow.getCell(18)));
				acc.setDetailCode8(getValue(xssfRow.getCell(19)));
				acc.setDetailName8(getValue(xssfRow.getCell(20)));

				acc.setDim01(getValue(xssfRow.getCell(21)));
				acc.setDim02(getValue(xssfRow.getCell(22)));
				acc.setDim03(getValue(xssfRow.getCell(23)));
				acc.setDim04(getValue(xssfRow.getCell(24)));
				acc.setDim05(getValue(xssfRow.getCell(25)));
				acc.setDim06(getValue(xssfRow.getCell(26)));
				acc.setDim07(getValue(xssfRow.getCell(27)));
				acc.setDim08(getValue(xssfRow.getCell(28)));
				acc.setDim09(getValue(xssfRow.getCell(29)));

				list.add(acc);

			}
		}

		return list;
	}

	public static List<AccountMapper> readAccXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		AccountMapper acc;

		List<AccountMapper> list = new ArrayList<AccountMapper>();

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);

			if (hssfRow != null) {
				
				String col1 = getValue(hssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				acc = new AccountMapper();

				acc.setFromAccountCode(col1);
				acc.setFromAccountName(getValue(hssfRow.getCell(1)));

				acc.setToAccountCode(getValue(hssfRow.getCell(2)));
				acc.setToAccountName(getValue(hssfRow.getCell(3)));

				acc.setCoef(Integer.valueOf(getValue(hssfRow.getCell(4))));

				acc.setDetailCode1(getValue(hssfRow.getCell(5)));
				acc.setDetailName1(getValue(hssfRow.getCell(6)));
				acc.setDetailCode2(getValue(hssfRow.getCell(7)));
				acc.setDetailName2(getValue(hssfRow.getCell(8)));
				acc.setDetailCode3(getValue(hssfRow.getCell(9)));
				acc.setDetailName3(getValue(hssfRow.getCell(10)));
				acc.setDetailCode4(getValue(hssfRow.getCell(11)));
				acc.setDetailName4(getValue(hssfRow.getCell(12)));
				acc.setDetailCode5(getValue(hssfRow.getCell(13)));
				acc.setDetailName5(getValue(hssfRow.getCell(14)));
				acc.setDetailCode6(getValue(hssfRow.getCell(15)));
				acc.setDetailName6(getValue(hssfRow.getCell(16)));
				acc.setDetailCode7(getValue(hssfRow.getCell(17)));
				acc.setDetailName7(getValue(hssfRow.getCell(18)));
				acc.setDetailCode8(getValue(hssfRow.getCell(19)));
				acc.setDetailName8(getValue(hssfRow.getCell(20)));

				acc.setDim01(getValue(hssfRow.getCell(21)));
				acc.setDim02(getValue(hssfRow.getCell(22)));
				acc.setDim03(getValue(hssfRow.getCell(23)));
				acc.setDim04(getValue(hssfRow.getCell(24)));
				acc.setDim05(getValue(hssfRow.getCell(25)));
				acc.setDim06(getValue(hssfRow.getCell(26)));
				acc.setDim07(getValue(hssfRow.getCell(27)));
				acc.setDim08(getValue(hssfRow.getCell(28)));
				acc.setDim09(getValue(hssfRow.getCell(29)));

				list.add(acc);
			}
		}

		return list;

	}

	public static List<IntegrationAccMapping> readInteAccXlsx(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		IntegrationAccMapping dim;

		List<IntegrationAccMapping> list = new ArrayList<IntegrationAccMapping>();

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				
				String col1 = getValue(xssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new IntegrationAccMapping();

				dim.setErpAccountCode(col1);
				dim.setErpAccountName(getValue(xssfRow.getCell(1)));
				dim.setHfmAccountCode(getValue(xssfRow.getCell(2)));
				dim.setHfmAccountName(getValue(xssfRow.getCell(3)));
				dim.setPlate(getValue(xssfRow.getCell(4)));
				dim.setDataType(getValue(xssfRow.getCell(5)));
				
				list.add(dim);

			}
		}

		return list;
	}
	
	public static List<OtherDimMapper> readInteProdXlsx(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		OtherDimMapper dim;

		List<OtherDimMapper> list = new ArrayList<OtherDimMapper>();

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				
				String col1 = getValue(xssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new OtherDimMapper();

				dim.setDetailCode(col1);
				dim.setDetailName(getValue(xssfRow.getCell(1)));
				dim.setDimCode(getValue(xssfRow.getCell(2)));
				dim.setDimName(getValue(xssfRow.getCell(3)));

				list.add(dim);

			}
		}

		return list;
	}
	
	public static List<OtherDimMapper> readDimXlsx(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		OtherDimMapper dim;

		List<OtherDimMapper> list = new ArrayList<OtherDimMapper>();

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				
				String col1 = getValue(xssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new OtherDimMapper();

				dim.setDetailType(col1);
				dim.setDimType(getValue(xssfRow.getCell(1)));
				dim.setDetailCode(getValue(xssfRow.getCell(2)));
				dim.setDetailName(getValue(xssfRow.getCell(3)));
				dim.setDimCode(getValue(xssfRow.getCell(4)));
				dim.setDimName(getValue(xssfRow.getCell(5)));

				list.add(dim);

			}
		}

		return list;
	}

	public static List<IntegrationAccMapping> readInteAccXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		IntegrationAccMapping dim;

		List<IntegrationAccMapping> list = new ArrayList<IntegrationAccMapping>();

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				
				String col1 = getValue(hssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new IntegrationAccMapping();
				dim.setErpAccountCode(col1);
				dim.setErpAccountName(getValue(hssfRow.getCell(1)));
				dim.setHfmAccountCode(getValue(hssfRow.getCell(2)));
				dim.setHfmAccountName(getValue(hssfRow.getCell(3)));
				dim.setPlate(getValue(hssfRow.getCell(4)));
				dim.setDataType(getValue(hssfRow.getCell(5)));
				list.add(dim);
			}
		}

		return list;
	}
	
	public static List<OtherDimMapper> readInteProdXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		OtherDimMapper dim;

		List<OtherDimMapper> list = new ArrayList<OtherDimMapper>();

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				
				String col1 = getValue(hssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new OtherDimMapper();
				dim.setDetailCode(col1);
				dim.setDetailName(getValue(hssfRow.getCell(1)));
				dim.setDimCode(getValue(hssfRow.getCell(2)));
				dim.setDimName(getValue(hssfRow.getCell(3)));
				list.add(dim);
			}
		}

		return list;
	}
	
	public static List<OtherDimMapper> readDimXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		OtherDimMapper dim;

		List<OtherDimMapper> list = new ArrayList<OtherDimMapper>();

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				String col1 = getValue(hssfRow.getCell(0));
				
				if (col1 == null || col1.length() == 0){
					continue;
				}
				
				dim = new OtherDimMapper();

				dim.setDetailType(col1);
				dim.setDimType(getValue(hssfRow.getCell(1)));
				dim.setDetailCode(getValue(hssfRow.getCell(2)));
				dim.setDetailName(getValue(hssfRow.getCell(3)));
				dim.setDimCode(getValue(hssfRow.getCell(4)));
				dim.setDimName(getValue(hssfRow.getCell(5)));

				list.add(dim);
			}
		}

		return list;
	}

	private static String getValue(XSSFCell xssfcell) {
		if (xssfcell == null) {
			return "";
		}

		if (xssfcell.getCellType() == xssfcell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfcell.getBooleanCellValue());
		} else if (xssfcell.getCellType() == xssfcell.CELL_TYPE_NUMERIC) {
			return String.valueOf(Math.round(xssfcell.getNumericCellValue()));
		} else {
			return String.valueOf(xssfcell.getStringCellValue());
		}
	}

	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "";
		}
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(Math.round(hssfCell.getNumericCellValue()));
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
