package model;

import javafx.scene.image.Image;

public class Bird {
	
	private int birdY;
	
	public final static int BIRDX = 102;
	public final static int BIRDWIDTH = 40;
	public final static int BIRDHEIGHT = 40;
	
	private double speed;
	private double birdAngle;
	
	// initialize the motion of the bird
	public final static double G = 4;
	public final static double T = 0.25;
	public final static double V0 = 20;
	
	private int imageIndex;
	
	private Image[] birdImages;
	
	public Bird() {
		
		// initialize the position of the bird
		birdY = 275;
		
		// load images of the bird
		birdImages = new Image[8];
		for(int i = 0; i < 8; i++) {
			birdImages[i] = new Image("image/bird" + i + ".png");
		}
	}
	
	public Image flyImage(){
		imageIndex++;
		return birdImages[(imageIndex/8)%birdImages.length];
	}
	
	public void step(){
		
		double v0 = speed;
		speed = v0 - G*T;
		double s = - v0*T + 0.5*G*T*T;
		birdY = birdY + (int)s; 
		birdAngle = s*5;

	}
	
	public void flappy(){
		speed = V0;
	}
	
	public double getBirdAngle() {
		return birdAngle;
	}

	public boolean hit(Column column) {
		
		int columnX = column.getColumnX();
		int columnY = column.getColumnY();
		
		if (birdY + BIRDHEIGHT > Ground.GROUNDY)
			return true;
		
		if (BIRDX + BIRDWIDTH - 4 > columnX && BIRDX < columnX + Column.COLUMNWIDTH ) {
			if(birdY < columnY + Column.COLUMNHEIGHT - 4 || birdY + BIRDHEIGHT - 4 > columnY + Column.COLUMNHEIGHT + Column.GAP) {
				return true;
			}
		}
		return false;
	}
	
	public boolean pass(Column column) {
		int columnX = column.getColumnX();
		if(BIRDX == columnX + Column.COLUMNWIDTH)
			return true;
		else
			return false;
	}
	
	public int getBirdY() {
		return birdY;
	}
	
	public void reSetBirdY() {
		birdY = 224;
		speed = V0;
	}
}
