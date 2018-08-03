package controller.model;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class Catalog {
	private Connection con;
	private Statement stmt;
	private static Catalog db;
	
	private Catalog(){
		/*try {	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apdb?useSSL=false","root","ranag");
			System.out.println("Connection Established...");
			stmt = con.createStatement();
			
		} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Connection failed...");
				e.printStackTrace();
		}*/
		String userName = "root";

		/** The password for the MySQL account (or empty for anonymous) */
		String password = "12345";

		/** The name of the computer running MySQL */
		String serverName = "localhost";

		/** The port of the MySQL server (default is 3306) */
		int portNumber = 3306;

		/**
		 * The name of the database we are testing with (this default is
		 * installed with MySQL)
		 */
		String dbName = "myschema";
		try {
		     con = null;
	
			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);
			System.out.println("trying to get connection!! ");
			con = DriverManager.getConnection("jdbc:mysql://" + serverName
					+ ":" + portNumber + "/" + dbName, connectionProps);
			stmt = con.createStatement();
	}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static Catalog getCatalog(){
		if(db == null){
			db = new Catalog();
		}
		return db;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void createDatabase(){
		try {	
			stmt.executeUpdate("create table User( user_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, user_name varchar(100) not null, address varchar(100), services varchar(200))");	
			stmt.executeUpdate("create table account ( account_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, user_id INTEGER, foreign key( user_id) references user(user_id) ,created_on Date, description varchar(100), password varchar(20))");
		
		} catch (SQLException e) {
				System.out.println("Invalid Query...");
				e.printStackTrace();
		}
	}
	
	public static void main(String [] args)
	{
		Catalog c = new Catalog();
		c.createDatabase();
	}
}
