package data.models;

public class VoucherPoolCreationDM {
	
	private String voucherAccountNumber="";
	private String expirationDays="";
	private String corporateName="";
	private String required="";
	
	
	
	
	public String getVoucherAccountNumber() {
		return voucherAccountNumber;
	}
	public void setVoucherAccountNumber(String voucherAccountNumber) {
		this.voucherAccountNumber = voucherAccountNumber;
	}
	
	
	public String getExpirationDays() {
		return expirationDays;
	}
	public void setExpirationDays(String expirationDays) {
		this.expirationDays = expirationDays;
	}
	
	
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	
	
	public String getRequiredValue() {
		return required;
	}
	public void setRequiredValue(String required) {
		this.required = required;
	}

}
