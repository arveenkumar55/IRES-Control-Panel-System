package controller.model;
import java.util.Date;

public class Account {
	private int accountId;
	private int userId;
	private Date createdDate;
	private String description;
	public Account(){
		
	}
	public int getAccountId() {
		return accountId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int uId) {
		this.userId = uId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
