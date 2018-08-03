package controller.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Step {
	 private final IntegerProperty stepno;
	
	 private final StringProperty elapsedTime;

	 private final IntegerProperty maxTemp;

	 private final IntegerProperty minTemp;

	
	
    public Step(int stepno,int maxTemp,int minTemp,String elapsedTime){
    	this.stepno=new SimpleIntegerProperty(stepno);
    	this.elapsedTime=new SimpleStringProperty(elapsedTime);
    	this.maxTemp=new SimpleIntegerProperty(maxTemp);
    	this.minTemp=new SimpleIntegerProperty(minTemp);   	
    }
	public int getStepno() {
		return stepno.get();
	}
	public String getElapsedTime() {
		return elapsedTime.get();
	}
	public int getMaxTemp() {
		return maxTemp.get();
	}
	public int getMinTemp() {
		return minTemp.get();
	}
	public IntegerProperty getstepnoproperty(){
		return stepno;
	}
	public StringProperty getelapsedtimeproperty(){
		return elapsedTime;
	}
	public IntegerProperty getmaxTempproperty(){
		return maxTemp;
	}
	public IntegerProperty getminTempproperty(){
		return minTemp;
	}
}
