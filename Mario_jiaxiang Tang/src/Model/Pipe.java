package Model;

import javafx.scene.image.Image;

public class Pipe {
	private int x;
	private int y;
	private Image image;

	public Pipe(int x, int y, Image image) {
		super();
		this.x = x;
		this.image = image;
		this.y = y;
	}

	public Image getImage() {
		return image;
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

	void setY(int y) {
		this.y = y;
	}
}
