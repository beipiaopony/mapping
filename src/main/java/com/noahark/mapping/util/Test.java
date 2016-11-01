package com.noahark.mapping.util;

import java.io.IOException;
import java.util.List;

import com.noahark.mapping.bean.OtherDimMapper;

public class Test {

	public static void main(String[] args) {
		
		List<OtherDimMapper> list;
		
		try {
			list = ReadExcel.readDimExcel("E:/epm/项目-Mapping(1).xls");
			
			System.out.println(list.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
