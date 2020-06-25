package model;

public class Ground {

	private int groundX;
	public final static int GROUNDY = 500;
	public final static int GROUNDWIDTH = 432;
	public final static int GROUNDHEIGHT = 149;
	
	public Ground() {
		groundX = 0;
	}
	
	public void step(){
		groundX--;
		if(groundX <= -(365)){
			groundX = 0;
		}
	}

	public int getGroundX() {
		return groundX;
	}
}
