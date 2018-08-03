package controller.model;

import java.io.IOException;

import controller.view.LiveAnalysisController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainclassAnalysis extends Application{
	private BorderPane rootLayout;
	private PanelControlSystem pcs;
    private ObservableList<Step> panelstep = FXCollections.observableArrayList();
    private ObservableList<Step> panelstep1 = FXCollections.observableArrayList();
    private ObservableList<Step> panelstep2 = FXCollections.observableArrayList();
    
    private ObservableList<Step> panelstep3 = FXCollections.observableArrayList();
    private ObservableList<Step> panelstep4 = FXCollections.observableArrayList();
    private ObservableList<Step> panelstep5 = FXCollections.observableArrayList();
     
    
    private Stage primaryStage;
        
    public MainclassAnalysis(){
    
        	panelstep.add(new Step(0,15,20,"03:45:10"));
        	panelstep.add(new Step(1,16,22,"02:44:00"));
        	panelstep.add(new Step(2,12,22,"03:25:12"));
        	panelstep.add(new Step(3,17,25,"01:10:10"));
        	panelstep.add(new Step(4,11,28,"04:40:15"));
        	panelstep.add(new Step(5,10,30,"03:02:01"));
            
        	panelstep1.add(new Step(0,20,30,"04:20:11"));
        	panelstep1.add(new Step(1,22,32,"03:33:04"));
        	panelstep1.add(new Step(2,23,33,"04:34:44"));
        	panelstep1.add(new Step(3,24,34,"05:45:45"));
        	panelstep1.add(new Step(4,26,36,"02:14:12"));
        	panelstep1.add(new Step(5,28,38,"01:47:12"));
            
        	panelstep2.add(new Step(0,10,20,"01:17:23"));
        	panelstep2.add(new Step(1,11,21,"05:32:00"));
        	panelstep2.add(new Step(2,31,11,"04:33:40"));
        	panelstep2.add(new Step(3,12,22,"07:11:10"));
        	panelstep2.add(new Step(4,13,23,"03:03:02"));
        	panelstep2.add(new Step(5,14,24,"01:09:10"));
            
        	panelstep3.add(new Step(0,11,20,"02:15:10"));
        	panelstep3.add(new Step(1,19,28,"02:18:19"));
        	panelstep3.add(new Step(2,10,22,"03:12:12"));
        	panelstep3.add(new Step(3,14,25,"03:41:11"));
        	panelstep3.add(new Step(4,13,27,"04:14:12"));
        	panelstep3.add(new Step(5,18,28,"05:40:13"));
            
        	panelstep4.add(new Step(0,15,35,"04:15:03"));
        	panelstep4.add(new Step(1,16,36,"03:51:04"));
        	panelstep4.add(new Step(2,15,35,"04:15:03"));
        	panelstep4.add(new Step(3,17,37,"02:05:20"));
        	panelstep4.add(new Step(4,18,38,"01:50:04"));
        	panelstep4.add(new Step(5,19,39,"06:55:06"));
            
        	panelstep5.add(new Step(0,14,24,"02:04:10"));
        	panelstep5.add(new Step(1,15,25,"01:00:20"));
        	panelstep5.add(new Step(2,12,22,"02:14:12"));
        	panelstep5.add(new Step(3,16,26,"04:08:30"));
        	panelstep5.add(new Step(4,17,27,"03:09:40"));
        	panelstep5.add(new Step(5,18,28,"02:01:11"));
            
        	
        }
        
        public ObservableList<Step> getDeviceSteps() {
            return panelstep;
        }
        
        public ObservableList<Step> getDeviceSteps1() {
            return panelstep1;
        }
        
        public ObservableList<Step> getDeviceSteps3() {
            return panelstep3;
        }
        
        public ObservableList<Step> getDeviceSteps4() {
            return panelstep4;
        }
        
        public ObservableList<Step> getDeviceSteps5() {
            return panelstep5;
        }
        
        public ObservableList<Step> getDeviceSteps2() {
            return panelstep2;
        }
        
        public void setPCS(PanelControlSystem pcs){
        	this.pcs = pcs;
        }
        
        @Override
        public void start(Stage primaryStage) {
        	System.out.println("Hello");
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Live Analysis");
            try {
				func();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        }
        
        public Stage getPrimaryStage() {
            return primaryStage;
            
        }
        public void func() throws IOException{
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(MainclassAnalysis.class.getResource("/controller/view/Analysispanel.fxml"));
        	//System.out.println("X:"+MainclassAnalysis.class.getResource("/controller/view/Analysispanel.fxml"));
            AnchorPane p = (AnchorPane) loader.load();
            
            LiveAnalysisController controller = loader.getController();
            controller.setMainApp(this, pcs);
            Scene scene = new Scene(p);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
    }