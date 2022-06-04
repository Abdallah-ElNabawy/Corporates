package data.models;

public class PoolCreationDM {
	
	private String accountNumber="";
	private String category="";
	private String threholdAmount="";
	private String ceiling="";
	private String notificationSMS="";
	private String notificationMail="";
	private String corporateName="";
	private String dailyCreditLimit="";
	private String dailyDebitLimit="";
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	public String getThreholdAmount() {
		return threholdAmount;
	}
	public void setThreholdAmountr(String threholdAmount) {
		this.threholdAmount = threholdAmount;
	}
	
	
	
	public String getCeiling() {
		return ceiling;
	}
	public void setCeiling(String ceiling) {
		this.ceiling = ceiling;
	}
	
	
	
	public String getNotificationSMS() {
		return notificationSMS;
	}
	public void setNotificationSMS(String notificationSMS) {
		this.notificationSMS = notificationSMS;
	}
	
	
	
	public String getNotificationMail() {
		return notificationMail;
	}
	public void setNotificationMail(String notificationMail) {
		this.notificationMail = notificationMail;
	}
	
	
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	
	
	public String getDailyCreditLimit() {
		return dailyCreditLimit;
	}
	public void setDailyCreditLimit(String dailyCreditLimit) {
		this.dailyCreditLimit = dailyCreditLimit;
	}
	
	
	public String getDailyDebitLimit() {
		return dailyDebitLimit;
	}
	public void setDailyDebitLimit(String dailyDebitLimit) {
		this.dailyDebitLimit = dailyDebitLimit;
	}
	

}
