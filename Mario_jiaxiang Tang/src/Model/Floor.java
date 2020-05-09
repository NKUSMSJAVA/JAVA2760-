package Model;

import javafx.scene.image.Image;

public class Floor {

	private int x;
	private int y;
	
	private Image floorImage;
	
	public Floor(int x, int y){
		this.setX(x);
		this.setY(y);
		setFloorImage(allResourceInit.stoneImages.get(6));
	}

	public Image getFloorImage() {
		return floorImage;
	}

	public void setFloorImage(Image floorImage) {
		this.floorImage = floorImage;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
