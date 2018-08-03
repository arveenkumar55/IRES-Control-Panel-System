package controller.view;

import java.io.IOException;

import controller.UserLogin;
import controller.model.MainclassAnalysis;
import controller.model.PanelControlSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
	private PanelControlSystem pcs;
	@FXML
	private Label msgLabel;
	private boolean allAccess;			//This is kept to check programs access to customer or customer care

	public MenuController(){
		System.out.println("Menu Controller Constructor");
		msgLabel = new Label();
		allAccess = false;
	}
	

	

	
	@FXML
	private void viewLiveAnalysisHandler(){
		if(allAccess){
			MainclassAnalysis obj = new MainclassAnalysis();
			obj.setPCS(pcs);
			obj.start(UserLogin.getStage());
		}else{
			printError();
		}
	}
	
	
	
	
	
	
	@FXML
	private void handleBackButton(){
		UserLogin ul = new UserLogin();
		Stage s = ul.getStage();
		try {
			ul.start(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setPanelControlSystem(PanelControlSystem pcs){
		System.out.println("pcs is set");
		this.pcs = pcs;
		char type = AccountController.getAccountType();
		if(type == 'c'){
			System.out.println("Created Acc Type: "+type);
			allAccess = true;
		}else if(type == 'r'){
			System.out.println("Created Acc Type: "+type);
			allAccess = false;
		}else{
			String type1 = pcs.getAccountType();
			System.out.println("ACCOUNT : "+type1);
			if(type1.equals("Customer Account") || type1.equals("")){
				allAccess = true;
			}else{
				allAccess = false;
			}}
	}
	
	public void initRootLayout(String xml) {
        	try {
            	FXMLLoader loader1 = new FXMLLoader();
        		loader1.setLocation(LoginController.class.getResource(xml));
        	    AnchorPane p = (AnchorPane) loader1.load();
        
        	   
        	    
        	    
        
        	    Scene scene = new Scene(p);
                Stage stg = UserLogin.getStage();
        		if( stg != null){
        			stg.setScene(scene);
        			stg.show();
        		}
            }catch (IOException e) {
                e.printStackTrace();
            }
    }
	
	
	public void printError(){
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(UserLogin.getStage());
        alert.setTitle("Access denied");
        alert.setHeaderText("Customer Care Account Access");
        alert.setContentText("Access denied! Only customer can view All fields");
        alert.showAndWait();
	}
}
