package com.journaldev.spring.form.model;

import com.journaldev.spring.form.validator.Address;

@Address.List({ 
    @Address(
      field = "mainAddress", 
      fieldMatch = "otherAddress", 
      message = "Main Address do not match with other address!"
    )/*, 
    @Address(
      field = "otherAddress", 
      fieldMatch = "matchOtherAddress", 
      message = "Other Address do not match!"
    )*/
})
public class AddressForm {
	
	private String mainAddress;
	private String matchMainAddress;
	private String otherAddress;
	private String matchOtherAddress;
	
	
	public String getMainAddress() {
		return mainAddress;
	}
	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}
	public String getMatchMainAddress() {
		return matchMainAddress;
	}
	public void setMatchMainAddress(String matchMainAddress) {
		this.matchMainAddress = matchMainAddress;
	}
	public String getOtherAddress() {
		return otherAddress;
	}
	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}
	public String getMatchOtherAddress() {
		return matchOtherAddress;
	}
	public void setMatchOtherAddress(String matchOtherAddress) {
		this.matchOtherAddress = matchOtherAddress;
	}
	
	
	
	
}
