package controller.model;
public class Project {
	private String projectName;
	private Program currentProgram;
	private Device currentDevice;
	private Step currentStep;
	public Project(String name){
		projectName = name;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Program getCurrentProgram() {
		return currentProgram;
	}
	public void setCurrentProgram(Program currentProgram) {
		this.currentProgram = currentProgram;
	}
	public Device getCurrentDevice() {
		return currentDevice;
	}
	public void setCurrentDevice(Device currentDevice) {
		this.currentDevice = currentDevice;
	}
	public Step getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(Step currentStep) {
		this.currentStep = currentStep;
	}

}
