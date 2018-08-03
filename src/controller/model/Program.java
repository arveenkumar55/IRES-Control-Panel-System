package controller.model;
import java.util.List;

public class Program {

	private String name;
	protected  Wall wall; 	     // object of wall to get wall temp..
	protected Device device; 	// object of device to get 
	protected List<Step> sList;
	public Program(String n) {	
	     name=n;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getDeviceTemp()
	{
		return device.getDeviceTemp();
	}
	public List<Step> getSteps()
	{
		return sList;
	}
	public int getWallTemp()
	{
		return wall.getWallTemp();
	}
    public String getName(){
    	return name;
    }
}
