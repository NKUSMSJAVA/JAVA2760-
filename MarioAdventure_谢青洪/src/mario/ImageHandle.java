package mario;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

import javax.swing.JFrame;

/**
 * 地图图片
 */
class CutImageDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediaTracker mediaTracker;
	public Image[][] imagesBlock;
	public static Image imageBlock;

	public static Image getImageblock() {
		return imageBlock;
	}

	public CutImageDemo() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mediaTracker = new MediaTracker(this);
		Image image = Toolkit.getDefaultToolkit().getImage("src/mapsheet.png");
		imagesBlock = CutImageDemo.cutImage(image, 5, 4, 0, 0, 48, 80, 48, 60, this);
		int index = 0;
		imageBlock = imagesBlock[2][0];
		mediaTracker.addImage(imageBlock, 1);
		for (Image[] images2 : imagesBlock) {
			for (Image image2 : images2) {
				mediaTracker.addImage(image2, index++);
			}
		}
		this.setVisible(true);
	}

	public static Image[][] cutImage(Image image, int rows, int cols, int x, int y, int width, int height, int changeX,
			int changeY, Component component) {
		Image[][] images = new Image[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ImageFilter filter = new CropImageFilter(x + j * changeX, y + i * changeY, width, height);
				images[i][j] = component.createImage(new FilteredImageSource(image.getSource(), filter));

			}
		}
		return images;
	}

	public void paint(Graphics g) {
		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (mediaTracker.checkAll()) {
			for (int row = 0, len = imagesBlock.length; row < len; row++) {
				for (int col = 0, length = imagesBlock[row].length; col < length; col++) {
					Image img = imagesBlock[row][col];
					int imgWidth = img.getWidth(null);
					int imgHeight = img.getHeight(null);
					int x = col * (imgWidth + 10) + 15;
					int y = row * (imgHeight + 15) + 40;
					g.drawImage(img, x, y, null);
				}
			}
		}
	}

}

/**
 * 馬里奧
 */
class CutImageDemo1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediaTracker mediaTracker;
	public Image[][] imagesMotion;
	public static Image imageMarioMotionRight1;
	public static Image imageMarioMotionRight2;
	public static Image imageMarioMotionRight3;
	public static Image imageMarioMotionUp;
	public static Image imageMarioMotionDown;

	public CutImageDemo1() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mediaTracker = new MediaTracker(this);
		Image image02 = Toolkit.getDefaultToolkit().getImage("src/马里奥.jpg");
		imagesMotion = CutImageDemo.cutImage(image02, 1, 15, 0, 0, 32, 60, 32, 0, this);
		int index = 0;
		imageMarioMotionRight1 = imagesMotion[0][0];
		imageMarioMotionRight2 = imagesMotion[0][1];
		imageMarioMotionRight3 = imagesMotion[0][2];
		imageMarioMotionUp = imagesMotion[0][6];
		imageMarioMotionDown = imagesMotion[0][14];

		for (Image[] images3 : imagesMotion) {
			for (Image image3 : images3) {
				mediaTracker.addImage(image3, index++);
			}
		}
		this.setVisible(true);
	}

	public static Image getImageMarioMotionRight1() {
		return imageMarioMotionRight1;
	}

	public static Image getImageMarioMotionRight2() {
		return imageMarioMotionRight2;
	}

	public static Image getImageMarioMotionRight3() {
		return imageMarioMotionRight3;
	}

	public static Image getImageMarioMotionUp() {
		return imageMarioMotionUp;

	}

	public static Image getImageMarioMotionDown() {
		return imageMarioMotionDown;
	}

	public static Image[][] cutImage(Image image, int rows, int cols, int x, int y, int width, int height, int changeX,
			int changeY, Component component) {
		Image[][] images = new Image[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ImageFilter filter = new CropImageFilter(x + j * changeX, y + i * changeY, width, height);
				images[i][j] = component.createImage(new FilteredImageSource(image.getSource(), filter));

			}
		}
		return images;
	}

	public void paint(Graphics g) {
		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (mediaTracker.checkAll()) {
			for (int row = 0, len = imagesMotion.length; row < len; row++) {
				for (int col = 0, length = imagesMotion[row].length; col < length; col++) {
					Image img = imagesMotion[row][col];
					int imgWidth = img.getWidth(null);
					int imgHeight = img.getHeight(null);
					int x = col * (imgWidth + 10) + 15;
					int y = row * (imgHeight + 15) + 40;
					g.drawImage(img, x, y, null);
				}
			}
		}
	}

}

/**
 * 敵人
 * 
 * @author admin
 *
 */
class CutImageDemo2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediaTracker mediaTracker;
	public Image[][] imagesMotionEnemy;
	public static Image imageEnemyMotionRight1;
	public static Image imageEnemyMotionRight2;
	public static Image imageEnemyMotionRight3;

	public CutImageDemo2() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mediaTracker = new MediaTracker(this);
		Image image = Toolkit.getDefaultToolkit().getImage("src/enemysheet.png");
		imagesMotionEnemy = CutImageDemo.cutImage(image, 1, 15, 0, 0, 16, 30, 16, 0, this);
		int index = 0;
		imageEnemyMotionRight1 = imagesMotionEnemy[0][0];
		imageEnemyMotionRight2 = imagesMotionEnemy[0][1];
		imageEnemyMotionRight3 = imagesMotionEnemy[0][2];

		for (Image[] images3 : imagesMotionEnemy) {
			for (Image image3 : images3) {
				mediaTracker.addImage(image3, index++);
			}
		}
		this.setVisible(true);
	}

	public static Image[][] cutImage(Image image, int rows, int cols, int x, int y, int width, int height, int changeX,
			int changeY, Component component) {
		Image[][] images = new Image[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ImageFilter filter = new CropImageFilter(x + j * changeX, y + i * changeY, width, height);
				images[i][j] = component.createImage(new FilteredImageSource(image.getSource(), filter));

			}
		}
		return images;
	}

	public static Image getImageEnemyMotionRight1() {
		return imageEnemyMotionRight1;
	}

	public static Image getImageEnemyMotionRight2() {
		return imageEnemyMotionRight2;
	}

	public static Image getImageEnemyMotionRight3() {
		return imageEnemyMotionRight3;
	}

	public void paint(Graphics g) {
		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (mediaTracker.checkAll()) {
			for (int row = 0, len = imagesMotionEnemy.length; row < len; row++) {
				for (int col = 0, length = imagesMotionEnemy[row].length; col < length; col++) {
					Image img = imagesMotionEnemy[row][col];
					int imgWidth = img.getWidth(null);
					int imgHeight = img.getHeight(null);
					int x = col * (imgWidth + 10) + 15;
					int y = row * (imgHeight + 15) + 40;
					g.drawImage(img, x, y, null);
				}
			}
		}
	}

}

/**
 * 主函数测试
 * 
 * 
 *
 */
public class ImageHandle extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new CutImageDemo();
		new CutImageDemo1();
//		new CutImageDemo2();

	}

}
