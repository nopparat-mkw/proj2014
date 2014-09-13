package com.project.bean;
public class AddressBean {
	private String addNo;
	private String moo;
	private String street;
	private String subDistrict;
	private String district;
	private String province;
	private String zipCode;

	public AddressBean() {

	}

	public AddressBean(String addNo, String moo, String street, String subDistrict,
			String district, String province, String zipCode) {
		super();
		this.addNo = addNo;
		this.moo = moo;
		this.street = street;
		this.subDistrict = subDistrict;
		this.district = district;
		this.province = province;
		this.zipCode = zipCode;
	}

	public String getAddNo() {
		return addNo;
	}

	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}

	public String getMoo() {
		return moo;
	}

	public void setMoo(String moo) {
		this.moo = moo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
