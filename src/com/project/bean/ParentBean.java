package com.project.bean;

public class ParentBean extends PersonBean{
	private AddressBean address;

	public ParentBean() {

	}

	public ParentBean(String antecedent, String firstName, String lastName) {
		super(antecedent, firstName, lastName);
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}
	
}
