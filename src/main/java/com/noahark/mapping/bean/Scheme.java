package com.noahark.mapping.bean;

public class Scheme {

	private int schemeId;
	private String schemeName;
	private String schemeDesc;
	private boolean chk = false;
	
	
	
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	public int getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(int schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSchemeDesc() {
		return schemeDesc;
	}
	public void setSchemeDesc(String schemeDesc) {
		this.schemeDesc = schemeDesc;
	}
	
	public Scheme(int schemeId, String schemeName, String schemeDesc) {
		super();
		this.schemeId = schemeId;
		this.schemeName = schemeName;
		this.schemeDesc = schemeDesc;
	}
	public Scheme() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
