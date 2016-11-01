package com.noahark.mapping.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelUtil {

	public static Workbook createWorkBook(List<Map<String,Object>> list, String []keys,String columnNames[]) {
		
		// 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("Sheet1");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        
        
     // 创建第一行
        Row row = sheet.createRow((short) 0);

      
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();

       
        cs.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);

      
        
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        
        for (int i = 0; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
        	Map<String,Object> map = list.get(i);
        	
            Row row1 = sheet.createRow(i+1);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(map.get(keys[j]) == null?"": map.get(keys[j]).toString());
                //cell.setCellStyle(cs2);
            }
        }
        
        return wb;
		
	}
}
