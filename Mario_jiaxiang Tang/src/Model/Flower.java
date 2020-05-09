package Model;

import javafx.scene.image.Image;

public class Flower extends Enemy implements Runnable {
	private Image enemyImage;
	private int type = 0;
	private Thread t = new Thread(this);
	
	public Flower(int x, int y, int enemyType,Background bg) {
		super(x, y, enemyType,bg);
		setEnemyImage(type);
		t.start();
	}

	public Image getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(Image enemyImage) {
		this.enemyImage = enemyImage;
	}

	public void setEnemyImage(int type){
		enemyImage = allResourceInit.flowerImages.get(type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
