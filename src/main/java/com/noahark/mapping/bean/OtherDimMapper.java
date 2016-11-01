package com.noahark.mapping.bean;

public class OtherDimMapper {

	private static int mapSetId = 1;
	
	private int mapId;
	private String detailType;
	private String dimType;
	
	private String detailCode;
	private String detailName;
	
	private String dimCode;
	private String dimName;
	
	private String createDate;

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getDimType() {
		return dimType;
	}

	public void setDimType(String dimType) {
		this.dimType = dimType;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getDimCode() {
		return dimCode;
	}

	public void setDimCode(String dimCode) {
		this.dimCode = dimCode;
	}

	public String getDimName() {
		return dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public static int getMapSetId() {
		return mapSetId;
	}

	@Override
	public String toString() {
		return "OtherDimMapper [mapId=" + mapId + ", detailType=" + detailType + ", dimType=" + dimType
				+ ", detailCode=" + detailCode + ", detailName=" + detailName + ", dimCode=" + dimCode + ", dimName="
				+ dimName + ", createDate=" + createDate + "]";
	}
	
	
	
	
}
