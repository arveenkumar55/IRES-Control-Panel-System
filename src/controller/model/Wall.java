package controller.model;
public class Wall {
	protected int wallTemp;

	public Wall() {
	}
    public Wall(int temp){
    	wallTemp=temp;
    }
	public int getWallTemp() {
		return wallTemp;
	}

	public void setWallTemp(int wallTemp) {
		this.wallTemp = wallTemp;
	}

}
