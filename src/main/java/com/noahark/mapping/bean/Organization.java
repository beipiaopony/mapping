package com.noahark.mapping.bean;

public class Organization {
	
	private int orgId;

	private String orgCode;
	private String orgName;
	private String orgDesc;
	
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
		
	public String getOrgDesc() {
		return orgDesc;
	}
	
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
	public Organization(int orgId, String orgCode, String orgName, String orgDesc) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
	}
	
	
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
