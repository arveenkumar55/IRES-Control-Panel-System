package controller.model;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class User {
	
	private String userName;
	private  int   userId;
	private String address;
	private String services;
	private Statement stmt;
	private Account userAccount;
	
	public User(){	//new account
		
		userAccount  = new Account();
	}
	
	public User(Connection con){
		userName = "";
		address = "";
		//userAccount  = new Account();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in creating Statement object!");
			e.printStackTrace();
		}
	}
	
	public void setStatement(Connection con){
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in creating Statement object!");
			e.printStackTrace();
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	// This method is called by createUserAccount Method()
	public void createAccount(String pwd,int uid,Statement stmt,String desc){	// from Account class
		/************ Creating Account Instance****************/
		//userAccount = new Account();
		System.out.println("Setting desc: "+desc);
		userAccount.setCreatedDate(new Date());
		userAccount.setDescription(desc);
		userAccount.setUserId(uid);
		System.out.println("Setting desc: "+userAccount.getDescription());
		System.out.println("Setting uid: "+userAccount.getUserId());
		services = desc;
		
		/******************************************************/
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String query1 = "insert into account(user_id,created_on, description, password) values( "+ uid + " , \'"+ date + "\' , \'" + desc +"\' , \'" + pwd +"\' )";
		try{
			stmt.execute(query1);
		}catch(SQLException e){
			System.out.println("SQL Syntax ERROR! ");
			e.printStackTrace();
		}
	}
	
	public String getAccountType(){
		
		if(userAccount != null){
			System.out.println("services: "+services);
			System.out.println("userID:"+userAccount.getUserId());
			System.out.println("desc: "+ userAccount.getDescription());
			return userAccount.getDescription();		
		}
		else{
			System.out.println("userAccount object is null");
			return "";
		}
	}
	public int getPrimaryKeyValue(){
		int id = 0;
		try {	
			ResultSet rs = stmt.executeQuery("select user_id from user");
			while(rs.next()){
				id = rs.getInt("user_id");
			}
		} catch (SQLException e) {
				System.out.println("Invalid Query...");
				e.printStackTrace();
		}
		return id;
	}
	
	public String createUserAccount(char type,String name, String address, String pwd, String services){
		if( type == 'c' ){	
			
			User customer = new Customer();
			
			customer.setUserName(name);
			customer.setAddress(address);
			String query = "insert into user(user_name, address) values( \'"+ name + "\' , \'" + address + "\' )";
			try{
				stmt.executeUpdate(query);
			}catch(SQLException e){
				System.out.println("SQL Syntax ERROR! ");
				e.printStackTrace();
			}
			int id = getPrimaryKeyValue();
			System.out.println("Creating customer account!");
			customer.createAccount(pwd,id,stmt,"Customer Account");
		}else{
			User care = new CustomerCareRepresentative();
			care.setUserName(name);
			care.setAddress(address);
			care.setServices(services);
			String query1 = "insert into user(user_name, address, services) values( \'"+ name + "\' , \'" + address + "\' , \'"+ services +"\' )";
			try{
				stmt.executeUpdate(query1);
			}catch(SQLException e){
				System.out.println("SQL Syntax ERROR! ");
				e.printStackTrace();
			}
			int id = getPrimaryKeyValue();
			care.createAccount(pwd,id,stmt,"Customer Care Account");
		}
		return "Account created successfully!";
	}//method end
	
	
	public String getLogIn(String username, String pwd){
		
		//userAccount = new Account();
		
		System.out.println("Username: "+username);
		System.out.println("Password: "+pwd);
		String test1 = "select user_id from user where user_name = \'"+ username + "\'";
		int uid = 0;
		try{
			ResultSet rs = stmt.executeQuery(test1);
			if (!rs.next()){
				return "Invalid Username!";
			}
			else{	// we have not allowed duplicates usernames....
				uid = rs.getInt("user_id");
			}
			String test2 = 	"select account_id from account where user_id = "+uid + " and password = \'"+pwd + "\'";
			ResultSet r2 = stmt.executeQuery(test2);
			if( !r2.next()){
				return "Invalid Password!";
			}
			else{
				
				String test = "select description from account where user_id = ( select user_id from user where user_name = \'"+ username + "\' ) ";
				ResultSet rs2 = stmt.executeQuery(test);
				if(rs2.next()){
					String desc = rs2.getString("description");
					userAccount.setDescription(desc);
					System.out.println("getLogIn: "+desc);
				}
				
				return "Logged in successfully!";
			}
		}catch(SQLException e){
			System.out.println("SQL Syntax ERROR! ");
			e.printStackTrace();
		}
		return "";
	}
	
		
	public void setServices(String srv){
		services = srv;
	}
	
	public String getServices(){
		return services;
	}
	
}
