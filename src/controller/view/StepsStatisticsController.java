package controller.view;

import java.io.IOException;
//import java.security.SecurityPermission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.UserLogin;
import controller.model.Catalog;
import controller.model.Device;
import controller.model.PanelControlSystem;
import controller.model.Program;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.util.Callback;

public class StepsStatisticsController {
	
	
	private Connection con;
	private Statement stmt;
	
	@FXML
	private ComboBox projectComboBox;
	@FXML
	private ComboBox deviceComboBox;
	@FXML
	private ComboBox programComboBox;
	
	private PanelControlSystem pcs;
	private Stage graphStage;

    private boolean okClicked = false;

    public StepsStatisticsController() {  
        	
    	try {
    		Catalog c = Catalog.getCatalog();
			con = c.getConnection();
			stmt = con.createStatement();
		} catch (SQLException e) {
				e.printStackTrace();
		}		
    }
    public void setGraphStage(Stage graphStage) {
		
    	this.graphStage = graphStage;
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
    @FXML
    public void initialize()
    {
    	projectComboBox.getSelectionModel().clearSelection();
		projectComboBox.getItems().clear();
		deviceComboBox.getSelectionModel().clearSelection();
		deviceComboBox.getItems().clear();
		programComboBox.getSelectionModel().clearSelection();
		programComboBox.getItems().clear();
		
		ObservableList<String> projectOptions = FXCollections.observableArrayList();

		String query = "select projectName from project";
		try{
    		ResultSet rs = stmt.executeQuery(query);
    		while (rs.next())
    		{
    			String str = rs.getString("projectName");
    			projectOptions.add(str);
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
		
		if (!(projectOptions.isEmpty()))
		{	
        	projectComboBox.setItems(projectOptions);
        	projectComboBox.setVisible(true);
        	projectComboBox.setVisibleRowCount(3);
		}
    }
    @FXML
	public void updateDevices()
	{	
    	if(!(projectComboBox.getSelectionModel().isEmpty())){
    	String item = projectComboBox.getSelectionModel().getSelectedItem().toString();
    	System.out.println("Item is: "+item);
    	ObservableList<String> deviceOptions = FXCollections.observableArrayList();
    	String query = "select deviceName from Device where projectID = "
    			+ "(select projectID from project where projectName = \'" + item +"\')";
    	try{
    		ResultSet rs = stmt.executeQuery(query);
    		while (rs.next())
    		{
    			String str = rs.getString("deviceName");
    			deviceOptions.add(str);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	deviceComboBox.setItems(deviceOptions);
    	deviceComboBox.getSelectionModel().select(0);
    	deviceComboBox.setVisible(true);
    	deviceComboBox.setVisibleRowCount(3);
    	}
	}
    @FXML
	public void updateProgram()
	{	
    	if(!(deviceComboBox.getSelectionModel().isEmpty() || projectComboBox.getSelectionModel().isEmpty())){
    		ObservableList<String> programOptions = FXCollections.observableArrayList();
    		String deviceName =deviceComboBox.getSelectionModel().getSelectedItem().toString();
    		String projectName = projectComboBox.getSelectionModel().getSelectedItem().toString();
    		String SQL = "SELECT programName,duration from program where deviceID = "
   			 + "(select idDevice from device where projectID = "
      				+ "(select projectID from project where projectName=\'" + projectName
      						+ "\') and deviceName=\'" + deviceName + "\')";
	    	try{
	    		ResultSet rs = stmt.executeQuery(SQL);
	    		while (rs.next())
	    		{
	    			String str = rs.getString("programName");
	    			programOptions.add(str);
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	programComboBox.setItems(programOptions);
	    	programComboBox.getSelectionModel().select(0);
	    	programComboBox.setVisible(true);
	    	programComboBox.setVisibleRowCount(3);
    	}
	} 
    @FXML
    public void showGraph()
    {	
    	
    	if (projectComboBox.getSelectionModel().isEmpty() || 
    		programComboBox.getSelectionModel().isEmpty() ||
    		deviceComboBox.getSelectionModel().isEmpty())
    	{
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(UserLogin.getStage());
	        alert.setTitle("Selection Error");
	        alert.setHeaderText("Options not selected");
	        alert.setContentText("Kindly choose the valid options.");
	        alert.showAndWait();
    	}
    	else {
    	//defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature");
        yAxis.setAutoRanging(false);
       
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Steps Historical Data Chart");
        //defining a series
        // Now we apply query and get multiple steps of programe..
        String programName = programComboBox.getSelectionModel().getSelectedItem().toString();
        String deviceName = deviceComboBox.getSelectionModel().getSelectedItem().toString();
        String projectName = projectComboBox.getSelectionModel().getSelectedItem().toString();
        
        String SQL = "select minTemp,maxTemp,elapsedTime from steps where programID = "
        		+ "(select programID from program where programName =\'" + programName + "\' and deviceID = "
        		+ "(select idDevice from device where deviceName =\'" + deviceName + "\' and projectID = "
        		+ "(select projectID from project where projectName=\'" + projectName +"\')))";
        
        /* Creating an Instance of Graph Class*/
        /* ************************************** */
    /*	Graph g = new Graph();
        Program p = new Program(programName);
        Device d  = new Device(deviceName);
        g.setDevice(d);
        g.setProgram(p);
        /* ************************************* */
        
        ResultSet rs = null;
        try {
			 rs = stmt.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        XYChart.Series series = new XYChart.Series();
        series.setName("Program: " + programName);
        boolean flag = true;
        int prev = 0;
        try {
			while (rs.next())
			{
				int x =rs.getInt("minTemp");
				int y = rs.getInt("maxTemp");
				int z = rs.getInt("elapsedTime");
				//populating the series with data
				if (flag) {
					series.getData().add(new XYChart.Data(1, x));
					series.getData().add(new XYChart.Data(z/2, y));
					prev = z;
					flag = false;
				}
				else {
					series.getData().add(new XYChart.Data(prev, x));
					series.getData().add(new XYChart.Data(prev+z/2, y));
					prev = prev+z;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	        Scene scene  = new Scene(lineChart,800,600);
	        lineChart.getData().add(series);
	       
	        graphStage.setScene(scene);
	        graphStage.showAndWait();
    	}
    }
    
	public void setPanelControlSystem(PanelControlSystem pcs) {
		graphStage.setTitle("Historical Analysis Steps Graph");
    	graphStage.initModality(Modality.WINDOW_MODAL);
    	graphStage.initOwner(UserLogin.getStage());
        this.pcs = pcs;
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    private void handleCancel() {
        graphStage.close();
    }
}