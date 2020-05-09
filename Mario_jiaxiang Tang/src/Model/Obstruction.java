package Model;

import javafx.scene.image.Image;

public class Obstruction {

	private int x;
	private int y;
	private int type;//type of obstruction 
	
	private Image obImage;
	
	public Obstruction(int x, int y, int type){
		this.setX(x);
		this.setY(y);
		this.type = type;
		
		setObImage();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getObImage() {
		return obImage;
	}
	public void setObImage() {
		this.obImage = allResourceInit.stoneImages.get(this.type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
