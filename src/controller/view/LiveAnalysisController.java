package controller.view;

import java.io.IOException;

import controller.UserLogin;
import controller.model.Device;
import controller.model.MainclassAnalysis;
import controller.model.PanelControlSystem;
import controller.model.Program;
import controller.model.Project;
import controller.model.Step;
import controller.model.Wall;

//import java.awt.TextField;

/*import application.MainclassAnalysis;
import application.Device;
import application.Program;
import application.Project;
import application.Step;
import application.Wall;*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LiveAnalysisController {
	int check;
	
   @FXML
   private TableColumn<Step, Integer> Stepnocolumn;
   
   @FXML
   private TableColumn<Step,Integer> MaxTempcolumn;

   @FXML
   private TableColumn<Step,Integer> MinTempcolumn;

   @FXML
   private TableColumn<Step,String> Timecolumn;
   
   @FXML
   private TextField Tempid;
   
   /* INTERACTION with Domain Classes     */
   /* Creating Instance of Project Class  */
   
	Project p5=new Project("Boys Hostel");
	Device d1=new Device("Device1");
	Device d2=new Device("Device2");
	Device d3=new Device("Device3");
	Device d4=new Device("Device4");
	Device d5=new Device("Device5");
	Wall w=new Wall(55);	
	Program p1=new Program("P1");
	Program p2=new Program("P2");
	Program p3=new Program("P3");
	
	
	private PanelControlSystem pcs;
	private MainclassAnalysis Mainclass;
	@FXML
    private TableView<Step> Tableid;
        

    @FXML
    private ComboBox projectid;

    @FXML
    private ComboBox deviceid;

    @FXML
    private ComboBox programid;// = new ComboBox();;

    
        
    public LiveAnalysisController() {
    	check=0;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	  Stepnocolumn.setCellValueFactory(cellData -> cellData.getValue().getstepnoproperty().asObject());
    	  MaxTempcolumn.setCellValueFactory(cellData -> cellData.getValue().getmaxTempproperty().asObject());
    	  MinTempcolumn.setCellValueFactory(cellData -> cellData.getValue().getminTempproperty().asObject());
    	  Timecolumn.setCellValueFactory(cellData -> cellData.getValue().getelapsedtimeproperty());//lastNameProperty());
    	
    	projectid.getItems().addAll(p5.getProjectName());//,p6.getProjectName(),p7.getProjectName());
    	String output=projectid.getPromptText();
    	if(output == "Boys Hostel"){
    		deviceid.getSelectionModel().clearSelection();
    		deviceid.getItems().clear();
        	deviceid.getItems().addAll(d1.getDeviceName(),d2.getDeviceName(),d3.getDeviceName());
    	}
        
    }
    public void setMainApp(MainclassAnalysis mainApp, PanelControlSystem pcs) {
        this.pcs = pcs;
    	this.Mainclass = mainApp;

    }
    @FXML
    void Projecthandler() {
    	String output = projectid.getSelectionModel().getSelectedItem().toString();
    	if(output == "Boys Hostel"){
    		deviceid.getSelectionModel().clearSelection();
    		deviceid.getItems().clear();
    		deviceid.getItems().addAll(d1.getDeviceName(),d2.getDeviceName(),d3.getDeviceName());
        	
    	}
    	else if(output == "Project2"){
    		deviceid.getSelectionModel().clearSelection();
    		deviceid.getItems().clear();  		
    		deviceid.getItems().addAll(d1.getDeviceName(),d2.getDeviceName(),d3.getDeviceName(),d4.getDeviceName(),d5.getDeviceName());

    	}
    	else if(output == "Project3"){
    		deviceid.getSelectionModel().clearSelection();
    		deviceid.getItems().clear();
    		deviceid.getItems().addAll(d1.getDeviceName(),d2.getDeviceName());

    	} 	    	
    }
    

    @FXML
    void devicehandler() {
    	
    	String output1 = deviceid.getSelectionModel().getSelectedItem().toString();
    	if(check == 0){
    	if(output1 == "Device1"){
    		check++;
    		programid.getSelectionModel().clearSelection();
    		programid.getItems().clear();
    	
    		programid.getItems().addAll(p1.getName(),p2.getName(),p3.getName());
        	    	}
    	else if(output1 == "Device2"){
    		check++;
    		programid.getSelectionModel().clearSelection();
    		programid.getItems().clear();
    	  		
        	programid.getItems().addAll(p1.getName(),p2.getName());

        	//String output1 = deviceid.getSelectionModel().getSelectedItem().toString();

    	}
    	else if(output1 == "Device3"){
    		check++;
    		programid.getSelectionModel().clearSelection();
    		programid.getItems().clear();
    	
        	programid.getItems().addAll(p1.getName());

    	}
    	}
    	else if(check ==1){
    		if(output1 == "Device1"){
    			programhandler();
    			System.out.println("in check1 2nd time device pressed");
    			
    			Tableid.getSelectionModel().clearSelection();
    	    	Tableid.getItems().clear();
        		check++;
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();
        	
        		programid.getItems().addAll(p1.getName(),p2.getName(),p3.getName());
            	    	}
        	else if(output1 == "Device2"){
        		programhandler();
        		Tableid.getSelectionModel().clearSelection();
            	Tableid.getItems().clear();
        		check++;
        		if( programid != null ){
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();
        	  		
            	programid.getItems().addAll(p1.getName(),p2.getName());
        		}
            	//String output1 = deviceid.getSelectionModel().getSelectedItem().toString();

        	}
        	else if(output1 == "Device3"){
        		programhandler();
        		Tableid.getSelectionModel().clearSelection();
            	Tableid.getItems().clear();
        		check++;
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();
        	
            	programid.getItems().addAll(p1.getName());

        	
    		   		   
    		   	}
    		    }	
    	else if(check ==2){
    		if(output1 == "Device1"){
    			programhandler();
    			Tableid.getSelectionModel().clearSelection();
    	    	Tableid.getItems().clear();
        		check++;
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();
        		programid.getItems().addAll(p1.getName(),p2.getName(),p3.getName());
            	    	
    		}
        	
    		else if(output1 == "Device2"){
    			programhandler();
        		Tableid.getSelectionModel().clearSelection();
            	Tableid.getItems().clear();
        		check++;
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();  		
            	programid.getItems().addAll(p1.getName(),p2.getName());

            	//String output1 = deviceid.getSelectionModel().getSelectedItem().toString();

        	}
        	else if(output1 == "Device3"){
        		programhandler();
        		Tableid.getSelectionModel().clearSelection();
            	Tableid.getItems().clear();
        		check++;
        		programid.getSelectionModel().clearSelection();
        		programid.getItems().clear();
               	programid.getItems().addAll(p1.getName());
   		   
        	}
    	}	 	
    }

    @FXML
    void programhandler() {
    	String temp=Integer.toString(w.getWallTemp());//w.getWallTemp();
    	Tempid.setText(temp);
    	if(deviceid.getSelectionModel().getSelectedItem().toString() == "Device1"){
    	if( programid != null){
    		if(programid.getSelectionModel().getSelectedItem() != null){
    	if(	programid.getSelectionModel().getSelectedItem().toString() == "P1"){
    	
    	Tableid.getSelectionModel().clearSelection();
    	Tableid.getItems().clear();
    	Tableid.getItems().addAll(Mainclass.getDeviceSteps());
    
    }
    else if(programid.getSelectionModel().getSelectedItem().toString() == "P2"){
    	//check=0;
    	Tableid.getSelectionModel().clearSelection();
    	Tableid.getItems().clear();
    	Tableid.getItems().addAll(Mainclass.getDeviceSteps1());
    	
    }
    else if(programid.getSelectionModel().getSelectedItem().toString() == "P3"){
    	//check=0;
    	Tableid.getSelectionModel().clearSelection();
    	Tableid.getItems().clear();
    	Tableid.getItems().addAll(Mainclass.getDeviceSteps2());
    	
    }}} 	
    	}
    	else if(deviceid.getSelectionModel().getSelectedItem().toString() == "Device2"){
    		
    		
    		if(programid != null ){//&& programid.getSelectionModel().getSelectedItem().toString()!=null){ 
    		if(programid.getSelectionModel().getSelectedItem() != null){
    			if(	programid.getSelectionModel().getSelectedItem().toString() == "P1"){
    		//	 check=0;
    			 Tableid.getSelectionModel().clearSelection();
    		    	Tableid.getItems().clear();
    		    	Tableid.getItems().addAll(Mainclass.getDeviceSteps3());
    		    }
    		 else if(	programid.getSelectionModel().getSelectedItem().toString() == "P2"){
    			// check=0;
    			 Tableid.getSelectionModel().clearSelection();
 		    	Tableid.getItems().clear();
 		    	Tableid.getItems().addAll(Mainclass.getDeviceSteps4());
 		    } 
    		}}
    	}
    	
    	else if(deviceid.getSelectionModel().getSelectedItem().toString() == "Device3"){
   
    		if( programid != null ){
    		
    			if(programid.getSelectionModel().getSelectedItem() != null){
    			if(	programid.getSelectionModel().getSelectedItem().toString()!=null){
    		if(	programid.getSelectionModel().getSelectedItem().toString() == "P1"){
   			//check=0;	
   			 Tableid.getSelectionModel().clearSelection();
   		    	Tableid.getItems().clear();
   		    	Tableid.getItems().addAll(Mainclass.getDeviceSteps4());
   		    }
   		    else if(programid.getSelectionModel().getSelectedItem().toString() == "P2"){
   		    	//check=0;
   		    	Tableid.getSelectionModel().clearSelection();
   		    	Tableid.getItems().clear();
   		    	Tableid.getItems().addAll(Mainclass.getDeviceSteps5());
   		    	
   		    }
    		}
    		}
    	}
   	}
    	//Tableid.getSelectionModel().clearSelection();
    	//Tableid.getItems().clear();
    	//Tableid.getItems().addAll(Mainclass.getDeviceSteps());
    
    	//	Tableid.getItems().add(0, s1);//getItems().addAll(s1.getStepno());//,s1.getElapsedTime(),s1.getMaxTemp(),s1.getMinTemp());//.setItems( Mainclass.getDeviceSteps());
    //	Tableid.getItems().add(1, s2);
    //	Tableid.getItems().add(3, s3);    	
    }
    
    @FXML
	private void handleBackButton(){
		loadMenuFxml();			//Moving back to Main Menu
	}
	
	private void loadMenuFxml(){
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("menu.fxml"));
		AnchorPane menu = null;
		try {
			menu = (AnchorPane) loader.load();
			MenuController AC = loader.getController();
			if(pcs != null){
				if(AC!=null)
				AC.setPanelControlSystem(pcs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(menu);
		Stage stg = UserLogin.getStage();
		if( stg != null){
			stg.setScene(scene);
			stg.show();
		}
	}
    
}
