package mario;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mario.*;

class displayImageblock extends JPanel {
	/**
	 * 
	 */
	private int xspeed = 10;// ˮƽ�ٶ�
	private int yspeed = 10;// ��ֱ�ٶ�
	private int inital = 0;// ����������ʼ��λ��
	private int index = 0;// ��ʼ������²���
	private int shot = 0;// �������
	private int index2 = 0;// ��ʼ�����˲���
	private int inital2 = 0;// ���������ʼ��
	private int inital3 = 0;// Ӳ�������ʼ��
	private int index3 = 0;// ���ȵ���
	private int score = 0;// ����
	public static final int WIDTH = 25;
	public static final int HEIGHT = 30;// ����������С
	int x = 20, y = 405;// map����³�ʼλ��
	int enermyx = 515, enermyy = 405;// ���˳�ʼλ��
	int touchamount = 0;// �жϲȵĴ���
	int state = 0;// ����״̬
	int mariostate = 0;// ������Ƿ��ڵ���
	private boolean isGameover = false;// �ж���Ϸ�Ƿ����
	private boolean isTouched = false;// �ж�����º͵����Ƿ���ײ
	private boolean isTouchedmap = false;// �ж�����ºͻ�����ͼ��ײ
	private boolean isTouchedpipe = false;// �ж�����º͹ܵ���ײ
	private boolean isTouchedblockup = false;// �ж�����º�ש������ײ
	private boolean isTouchedblockdown = false;// �ж�����º�ש������ײ
	private boolean isTouchedblockleft = false;// �ж�����º�ש������ײ
	private boolean isTouchedblockright = false;// �ж�����º�ש������ײ
	private boolean isDoubletouched = false;// �ж�����º͵��˲ȵ�״̬����
	private boolean isTouchedcoin = false;// �ж�����³Ե����
	private boolean isTouchedmushroom = false;// �ж�����³Ե�Ģ��
	public List<Rectangle> mapList = new ArrayList<Rectangle>();
	public List<Rectangle> marioList = new ArrayList<Rectangle>();
	public List<Rectangle> pipeList = new ArrayList<Rectangle>();
	public List<Rectangle> blockList = new ArrayList<Rectangle>();

//һЩ�������O���Լ��@�÷���
	public void setInital(int in) {
		this.inital = in;
	}

	public int getInital() {
		return inital;
	}

	public void setIndex(int in) {
		this.index = in;
	}

	public void setIndex2(int in) {
		this.index2 = in;
	}

	public void setIndex3(int in) {
		this.index3 = in;
	}

	public int getIndex() {
		return index;
	}

	public void setShot(int x) {
		this.shot = x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int gatY() {
		return y;
	}

	// ���˵ĳ�ʼ��
	public void setEX(int x) {
		this.enermyx = x;
	}

	public void setEY(int y) {
		this.enermyy = y;
	}

	public int getEX() {
		return enermyx;
	}

	public int getEY() {
		return enermyy;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getStatemario() {
		return mariostate;
	}

	public void setStatemario(int state) {
		this.mariostate = state;
	}

	public boolean getIsgameoverVerse() {
		return !isGameover;
	}

	public void setIsgameover(boolean gameover) {
		this.isGameover = gameover;
	}

	public boolean getIstouched() {
		return isTouched;
	}

	public void setIstouched(boolean touch) {
		this.isTouched = touch;
	}

	public boolean getIstouchedmap() {
		return isTouchedmap;
	}

	public void setIstouchedmap(boolean touch) {
		this.isTouchedmap = touch;
	}

	public boolean getIstouchedpipe() {
		return isTouchedpipe;
	}

	public void setIstouchedpipe(boolean touch) {
		this.isTouchedpipe = touch;
	}

	public boolean getIstouchedblockup() {
		return isTouchedblockup;
	}

	public void setIstouchedblockup(boolean touch) {
		this.isTouchedblockup = touch;
	}

	public boolean getIstouchedblockdown() {
		return isTouchedblockdown;
	}

	public void setIstouchedblockdown(boolean touch) {
		this.isTouchedblockdown = touch;
	}

	public boolean getIstouchedblockleft() {
		return isTouchedblockleft;
	}

	public void setIstouchedblockleft(boolean touch) {
		this.isTouchedblockleft = touch;
	}

	public boolean getIstouchedblockright() {
		return isTouchedblockright;
	}

	public void setIstouchedblockright(boolean touch) {
		this.isTouchedblockright = touch;
	}

	public boolean getIstouchedcoin() {
		return isTouchedcoin;
	}

	public void setIstouchedcoin(boolean touch) {
		this.isTouchedcoin = touch;
	}

	public boolean getIstouchedmushroom() {
		return isTouchedmushroom;
	}

	public void setIstouchedmushroom(boolean touch) {
		this.isTouchedmushroom = touch;
	}

	public boolean getIsdoubletouched() {
		return isDoubletouched;
	}

	public void setIsdoubletouched(boolean touch) {
		this.isDoubletouched = touch;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		super.paint(g);

		// ����Ԫ��

		Image marioMotion1 = new ImageIcon("src/mariomotion1.png").getImage();
		Image marioMotion2 = new ImageIcon("src/mariomotion2.png").getImage();
		Image marioMotion3 = new ImageIcon("src/mariomotion3.png").getImage();
		Image marioMotion4 = new ImageIcon("src/mariomotion4.png").getImage();
		Image marioMotion5 = new ImageIcon("src/mariomotion5.png").getImage();
		Image marioMotion1v = new ImageIcon("src/mariomotion1v.png").getImage();
		Image marioMotion2v = new ImageIcon("src/mariomotion2v.png").getImage();
		Image marioMotion3v = new ImageIcon("src/mariomotion3v.png").getImage();
		Image marioMotion4v = new ImageIcon("src/mariomotion4v.png").getImage();
		Image marioMotion5v = new ImageIcon("src/mariomotion5v.png").getImage();
		Image racoonmarioMotion1 = new ImageIcon("src/racoonmario1.png").getImage();
		Image racoonmarioMotion2 = new ImageIcon("src/racoonmario2.png").getImage();
		Image racoonmarioMotion3 = new ImageIcon("src/racoonmario3.png").getImage();
		Image racoonmarioMotion4 = new ImageIcon("src/racoonmario4.png").getImage();
		Image racoonmarioMotion5 = new ImageIcon("src/racoonmario5.png").getImage();
		Image racoonmarioMotion1v = new ImageIcon("src/racoonmario1v.png").getImage();
		Image racoonmarioMotion2v = new ImageIcon("src/racoonmario2v.png").getImage();
		Image racoonmarioMotion3v = new ImageIcon("src/racoonmario3v.png").getImage();
		Image racoonmarioMotion4v = new ImageIcon("src/racoonmario4v.png").getImage();
		Image racoonmarioMotion5v = new ImageIcon("src/racoonmario5v.png").getImage();
		Image fireBall1 = new ImageIcon("src/fireball1.png").getImage();
		Image fireBall2 = new ImageIcon("src/fireball2.png").getImage();
		Image fireBall3 = new ImageIcon("src/fireball3.png").getImage();
		Image fireBall4 = new ImageIcon("src/fireball4.png").getImage();
		Image enemyMotion1 = new ImageIcon("src/enemymotion1.png").getImage();
		Image enemyMotion2 = new ImageIcon("src/enemymotion2.png").getImage();
		Image enemyMotion1v = new ImageIcon("src/enemymotion1v.png").getImage();
		Image enemyMotion2v = new ImageIcon("src/enemymotion2v.png").getImage();
		Image gameover = new ImageIcon("src/Game over.jpg").getImage();
		Image enermytouched1v = new ImageIcon("src/enermy1.png").getImage();
		Image enermytouched2 = new ImageIcon("src/enermy2.png").getImage();
		Image enermytouched1 = new ImageIcon("src/enermy1v.png").getImage();
		Image coin1 = new ImageIcon("src/coin1.png").getImage();
		Image coin2 = new ImageIcon("src/coin2.png").getImage();
		Image mushroom = new ImageIcon("src/mushroom.png").getImage();

		// ���黯������ڲ���չʾ�˶�����
//		Image[] fireball = { fireBall1, fireBall2, fireBall3, fireBall4 };
		Image[] enemymotion = { enemyMotion1, enemyMotion2 };
		Image[] enemymotionv = { enemyMotion1v, enemyMotion2v };
		Image[] marioMotion = { marioMotion1, marioMotion2, marioMotion3, marioMotion4, marioMotion5 };
		Image[] marioMotionv = { marioMotion1v, marioMotion2v, marioMotion3v, marioMotion4v, marioMotion5v };
		Image[] racoonmarioMotion = { racoonmarioMotion1, racoonmarioMotion2, racoonmarioMotion3, racoonmarioMotion4,
				racoonmarioMotion5 };
		Image[] racoonmarioMotionv = { racoonmarioMotion1v, racoonmarioMotion2v, racoonmarioMotion3v,
				racoonmarioMotion4v, racoonmarioMotion5v };
		Image[] coinMotion = { coin1, coin2 };
//		Image[] enermyTouchedmotion = { enermytouched1, enermytouched2 };
//		Image[] enermyTouchedmotionv = { enermytouched2, enermytouched1v };

		// �������е�ͼԪ��

		// ��ͼԪ��

		Image mapImage = new ImageIcon("src/map.png").getImage();
		Image pipeImage = new ImageIcon("src/pipe.png").getImage();
		Image blockImage = new ImageIcon("src/block.png").getImage();
		Image flagImage = new ImageIcon("src/flag.png").getImage();
		Image cloudImage = new ImageIcon("src/cloud.png").getImage();

		int xmap = mapImage.getWidth(null);
		int ymap = mapImage.getHeight(null);

		// ����ͼԪ��
		int[] mapImageint = { 0, 40, 80, 120, 160, 200, 240, 280, 360, 400, 440, 515, 555, 595, 635, 675, 715, 755, 795,
				835, 875, 915, 955, 995, 1035, 1075, 1115, 1155, 1195 };
		for (int i : mapImageint) {
			g.drawImage(mapImage, i, 500 - ymap, 40, 40, null);
			Rectangle mapRectangle = new Rectangle(i, 500 - ymap, 40, 40);
			mapList.add(mapRectangle);
		}

		// ˮ��Ԫ��
		int[] pipeImageint = { 475, 755 };
		for (int i : pipeImageint) {
			g.drawImage(pipeImage, i, 400, 40, 60, null);
			Rectangle pipeRectangle = new Rectangle(i, 400, 40, 60);
			mapList.add(pipeRectangle);

		}

		// ����Ԫ��
		g.drawImage(blockImage, 400, 350, 50, 20, null);
		g.drawImage(blockImage, 350, 350, 50, 20, null);
		g.drawImage(blockImage, 480, 300, 50, 20, null);
		g.drawImage(blockImage, 530, 300, 50, 20, null);// ש��
		Rectangle blockrectangle1 = new Rectangle(400, 350, 50, 20);
		Rectangle blockrectangle2 = new Rectangle(350, 350, 50, 20);
		Rectangle blockrectangle3 = new Rectangle(480, 300, 50, 20);
		Rectangle blockrectangle4 = new Rectangle(530, 300, 50, 20);
		blockList.add(blockrectangle1);
		blockList.add(blockrectangle2);
		blockList.add(blockrectangle3);
		blockList.add(blockrectangle4);

		g.drawImage(flagImage, 100, 470 - ymap, 40, 30, null);// ��������
		g.drawImage(cloudImage, 100, 100, 60, 50, null);// �ƶ�

		marioList.add(new Rectangle(getX(), gatY(), WIDTH, HEIGHT));
		// ��ʼ������,indexΪ0�������ң���֮����
		if (index == 0 && getIstouchedmushroom() == false) {
			g.drawImage(marioMotion[inital], getX(), gatY(), WIDTH, HEIGHT, null);
		} else if (index == 1 && getIstouchedmushroom() == false) {
			g.drawImage(marioMotionv[inital], getX(), gatY(), WIDTH, HEIGHT, null);
		} else if (index == 0 && getIstouchedmushroom() == true) {
			g.drawImage(racoonmarioMotion[inital], getX(), gatY(), WIDTH, HEIGHT, null);
		} else if (index == 1 && getIstouchedmushroom() == true) {
			g.drawImage(racoonmarioMotionv[inital], getX(), gatY(), WIDTH, HEIGHT, null);
		}

		// ��Ӳ���ഥ
		Rectangle coinRectangle = new Rectangle(370, 330, 15, 20);// Ӳ�Ҿ���
		Rectangle marioRectangle2 = new Rectangle(getX(), gatY(), WIDTH, HEIGHT);
		if (marioRectangle2.intersects(coinRectangle)) {
			if (score == 0) {
				Music.playCoin();
			}
			setIstouchedcoin(true);
		}

		// Ӳ���˶�
		if (getIstouchedcoin() == false) {
			g.drawImage(coinMotion[inital3 % 2], 370, 330, 15, 20, null);
			inital3 += 1;
		}

		if (getIstouchedcoin() == true) {
			setScore(1);
		}

		// Ģ���ഥ
		Rectangle mushroomRectangle = new Rectangle(500, 280, 18, 20);// Ģ������
		if (marioRectangle2.intersects(mushroomRectangle)) {
			setIstouchedmushroom(true);
		}

		if (getIstouchedmushroom() == false) {
			g.drawImage(mushroom, 500, 280, 18, 20, null);
		}

		// ��ذ��ഥ
		for (int length = 0; length < mapList.size(); length++) {
			Rectangle marioRectangle = new Rectangle(getX(), gatY(), WIDTH, HEIGHT);
			if (marioRectangle.intersects(mapList.get(length))) {
				setIstouchedmap(true);
				setStatemario(2);
			}
		}

		// ��ܵ��ഥ
		for (int length = 0; length < pipeList.size(); length++) {
			Rectangle marioRectangle = new Rectangle(getX(), gatY(), WIDTH, HEIGHT);
			if (marioRectangle.intersects(pipeList.get(length))) {
				setIstouchedpipe(true);
			}
		}

		// ��ʯ���ഥ
		for (int length = 0; length < blockList.size(); length++) {
			Rectangle marioRectangle = new Rectangle(getX(), gatY(), WIDTH, HEIGHT);
			if (marioRectangle.intersects(blockList.get(length))) {
				if (marioRectangle.getY() < blockList.get(length).getY()) {
					setIstouchedblockdown(true);
				}
				if (marioRectangle.getY() > blockList.get(length).getY()
						&& marioRectangle.getY() < blockList.get(length).getY() + 20) {
					setIstouchedblockup(true);
				}
				if (marioRectangle.getX() < blockList.get(length).getX()) {
					setIstouchedblockright(true);
				}
				if (marioRectangle.getX() > blockList.get(length).getX()
						&& marioRectangle.getX() < blockList.get(length).getX() + 50) {
					setIstouchedblockright(true);
				}
				if (!marioRectangle.intersects(blockList.get(length))) {
					setIstouchedblockup(false);
					setIstouchedblockdown(false);
					setIstouchedblockleft(false);
					setIstouchedblockright(false);
				}
			}
		}

		if (getIstouchedmap() == false && getIstouchedpipe() == false && getIstouchedblockdown() == false
				&& getStatemario() == 1) {
			setY(gatY() + 10);
		}

		if (getStatemario() == 0) {
			setY(gatY());
		}

		if (gatY() < 405) {
			setY(gatY() + 5);
		}

		if (getX() > 320 && getX() < 340) {
			setY(gatY() + 10);
		}

		if (getIstouchedmap() == true) {
			setY(gatY() - 10);
			setStatemario(0);
			setIstouchedmap(false);
		}

		if (getIstouchedpipe() == true) {
			setY(gatY() - 5);
			setStatemario(0);
			setIstouchedpipe(false);
		}

		if (getIstouchedblockdown() == true) {
			setY(gatY() - 5);
			setStatemario(0);
			setIstouchedblockdown(false);
		}

		// �жϵ����н�����
		if (getEX() > 715) {
			setIndex2(1);
			setIndex3(1);
		}

		if (getEX() < 515) {
			setIndex2(0);
			setIndex3(0);
		}

		// �����˶�
		if (index2 == 0 && isTouched == false) {
			g.drawImage(enemymotion[inital2 % 2], getEX(), getEY(), WIDTH, HEIGHT, null);
			setEX(getEX() + 5);
			inital2 += 1;
		} else if (index2 == 1 && isTouched == false) {
			g.drawImage(enemymotionv[inital2 % 2], getEX(), getEY(), WIDTH, HEIGHT, null);
			setEX(getEX() - 5);
			inital2 += 1;
		}

		// �ж�����º͵����Ƿ���ײ
		if (Math.abs(getX() - getEX()) < WIDTH && Math.abs(getEY()) - gatY() < HEIGHT) {
			setIstouched(true);
			touchamount = 1;
			setY(getEY() - HEIGHT);
		}

		if (touchamount == 1) {
			g.drawImage(enermytouched1, getEX(), getEY() + 10, 20, 20, null);
			setState(1);
		}

		// ���������Լ�����
		if (gatY() > 445 && isGameover == false || gatY() <= 0 && isGameover == false) {
			setIsgameover(true);
			Music.playDeath();
		}
		if (gatY() > 445 || gatY() <= 0) {
			g.drawImage(gameover, 400, 200, 200, 100, null);
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				// ���¿�ʼ��Ϸ
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setX(20);
					setY(405);
					setEX(515);
					setEY(405);
					setIsgameover(false);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		// �ж������λ��

		// �Ƿ���������
//		if (shot == 1) {
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					for (int i = 0;; i++)
//						g.drawImage(fireball[i%4], getX(), getY(), 20, 20, null);
//				}
//			}).start();
//		     try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

}

public class Map extends JFrame {

	/**
	 * @throws InterruptedException
	 * 
	 */
	public Map() throws InterruptedException {
		displayImageblock displayimage = new displayImageblock();
		this.setTitle("Mario Aderventrue");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		this.add(displayimage);
		Music.loop();
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				displayimage.setInital(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (KeyEvent.VK_A == e.getKeyCode() && displayimage.getX() >= 5) {
					displayimage.setIndex(1);
					displayimage.setInital((displayimage.getInital() + 1) % 3);
					displayimage.setX(displayimage.getX() - 5);
				} else if (KeyEvent.VK_A == e.getKeyCode() && displayimage.getX() < 5) {
					displayimage.setX(displayimage.getX());
					displayimage.setInital((displayimage.getInital() + 1) % 3);
				} else if (KeyEvent.VK_D == e.getKeyCode() && displayimage.getX() >= 450
						&& displayimage.gatY() >= 370) {
					displayimage.setIndex(0);
					displayimage.setInital((displayimage.getInital() + 1) % 3);
					displayimage.setX(displayimage.getX());
				} else if (KeyEvent.VK_D == e.getKeyCode() && displayimage.getX() < 450
						|| KeyEvent.VK_D == e.getKeyCode() && displayimage.getX() >= 450 && displayimage.gatY() < 370) {
					displayimage.setIndex(0);
					displayimage.setInital((displayimage.getInital() + 1) % 3);
					displayimage.setX(displayimage.getX() + 5);
				} else if (KeyEvent.VK_D == e.getKeyCode() && displayimage.getX() >= 475) {
					displayimage.setInital((displayimage.getInital() + 1) % 3);
					displayimage.setX(displayimage.getX() + 5);
				} else if (KeyEvent.VK_W == e.getKeyCode()) {
					displayimage.setInital(4);
					displayimage.setY(displayimage.gatY() - 20);
					displayimage.setStatemario(1);
					// Music.playJump();
				} else if (KeyEvent.VK_S == e.getKeyCode()) {
					displayimage.setInital(3);
				} else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
					displayimage.setShot(1);
				}
			}
		});
		// ����ļ�����Ӧֻ��ʵ����һС���֣������ϸ����Ҫ����
		while (true) {
			Thread.sleep(80);
			displayimage.repaint();
		}
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new Map();

	}

}
