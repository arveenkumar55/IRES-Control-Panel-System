package controller.model;
public class PanelControlSystem {

	private Catalog catalog;
	private User user;
	private static PanelControlSystem pcs;
	
	private PanelControlSystem(){

		catalog = Catalog.getCatalog();	//SINGELTON
		user  = new User();
		user.setStatement(catalog.getConnection());
		System.out.println("User and Catalog Instance was created!!!!");
	}
	
	/* Applying  SINGELTON  PATTERN */
	public static PanelControlSystem getPanelControlSystem(){
		
		if(pcs == null){
			pcs = new PanelControlSystem();
		}
		return pcs;
	}
	
	public void createDatabase(){
		catalog.createDatabase();
	}//method end
	
	public String createUserAccount(char type,String name, String address, String pwd, String services){
		return user.createUserAccount(type, name, address, pwd, services);
	}//method end
	
	public String getLogIn(String username, String pwd){
		if( user != null)
		return user.getLogIn(username, pwd);
		else
			return "";
	}//method end;
		
	public void provideFeedback(String text){
		/*Feedback fb = new Feedback();
		fb.setFeedback(text)*/;
	}//method end

	public String getAccountType(){
		if(user != null)
			return user.getAccountType();
		else{
			System.out.println("user is null");
			return "";
		}
	}
	
}//class end
