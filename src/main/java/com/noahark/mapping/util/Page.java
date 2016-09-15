package com.noahark.mapping.util;

import java.util.List;

public class Page<T> {

	private int total;
	
	private List<T> rows;
	
	private String message;
	
	public Page(){}
	
	
	public Page(int total,List<T> rows){
		this.total = total;
		this.rows = rows;
	}
	
	public Page(int total,String message){
		this.total = total;
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
