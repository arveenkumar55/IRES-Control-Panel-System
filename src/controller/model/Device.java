package controller.model;

public class Device {
	protected String deviceName;
	protected final static double heatingCapacity=45.456;
	protected int    deviceTemp;
	
	
	public Device(String f)
	{
		deviceName = f;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public double getHeatingCapacity() {
		return heatingCapacity;
	}

	public int getDeviceTemp() {
		return deviceTemp;
	}

	public void setDeviceTemp(int deviceTemp) {
		this.deviceTemp = deviceTemp;
	}
	public String getdevicename(){
		return 	deviceName;
	}

}
