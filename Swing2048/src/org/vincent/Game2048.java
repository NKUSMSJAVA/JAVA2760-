package org.vincent;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Game2048 extends JPanel {
	
	enum State {
		start, won, running, over
	}

	final Color[] colorTable = { new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3), new Color(0xffdac3),
			new Color(0xe7b08e), new Color(0xe7bf8e), new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
			new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710) };
	final static int target = 2048;
	static int highest;
	static int score;
	// ������ɫ
	private Color gridColor = new Color(0xBBADA0);
	// ������ɫ
	private Color emptyColor = new Color(0xCDC1B4);
	// ��ʼ������ɫ
	private Color startColor = new Color(0xF5DEB3);
	private Random rand = new Random();
	private Tile[][] tiles;
	// ������߿���
	private int side = 4;
	private State gamestate = State.start;
	// �ж��ܷ�����ƶ�
	private boolean checkingAvailableMoves;

	// ���췽��
	public Game2048() {
		setPreferredSize(new Dimension(900, 700));
		setBackground(new Color(0xFAF8EF));
		setFont(new Font("SansSerif", Font.BOLD, 48));

		// ����
		setFocusable(true);

		// ������
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				startGame(); // ��ʼ��Ϸ����
				repaint(); // ˢ�»���
			}
		});

		// ���̼���
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					moveUp();
					break;
				case KeyEvent.VK_DOWN:
					moveDown();
					break;
				case KeyEvent.VK_LEFT:
					moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					moveRight();
					break;
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawGrid(g);
	}

	// ��ʼ��Ϸ����
	void startGame() {
		if (gamestate != State.running) {
			score = 0;
			highest = 0;
			gamestate = State.running;

			// ����������ڴ�ռ�
			tiles = new Tile[side][side];

			// ��ʼʱ�������������
			addRandomTile();
			addRandomTile();
		}
	}

	void drawGrid(Graphics2D g) {
		g.setColor(gridColor);
		g.fillRoundRect(200, 100, 499, 499, 15, 15);
		if (gamestate == State.running) {
			for (int r = 0; r < side; r++) {
				for (int c = 0; c < side; c++) {
					if (tiles[r][c] == null) {
						g.setColor(emptyColor);
						g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
					} else {
						drawTile(g, r, c);
					}
				}
			}
		} else {
			g.setColor(startColor);
			g.fillRoundRect(215, 115, 469, 469, 7, 7);
			g.setColor(gridColor.darker());
			g.setFont(new Font("SansSerif", Font.BOLD, 128));
			g.drawString("2048", 310, 270);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			if (gamestate == State.won) {
				g.drawString("You Win", 390, 350);
			} else if (gamestate == State.over)
				g.drawString("Game Over", 400, 350);
			g.setColor(gridColor);
			g.drawString("�����������ʼ��Ϸ", 350, 470);
			g.drawString("(ʹ�á� �� �� ��������ƶ�����)", 310, 530);
		}
	}

	void drawTile(Graphics2D g, int r, int c) {
		int value = tiles[r][c].getValue();
		g.setColor(colorTable[(int) (Math.log(value) / Math.log(2)) + 1]);
		g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
		String s = String.valueOf(value);
		g.setColor(value < 128 ? colorTable[0] : colorTable[1]);
		FontMetrics fm = g.getFontMetrics();
		int asc = fm.getAscent();
		int dec = fm.getDescent();
		int x = 215 + c * 121 + (106 - fm.stringWidth(s)) / 2;
		int y = 115 + r * 121 + (asc + (106 - (asc + dec)) / 2);
		g.drawString(s, x, y);
	}

	private void addRandomTile() {
		int pos = rand.nextInt(side * side);
		int row, col;

		// 0-15��������һ����
		// ����ڿհ׿�����������
		do {
			pos = (pos + 1) % (side * side);
			row = pos / side;
			col = pos % side;
		} while (tiles[row][col] != null);
		int val = rand.nextInt(10) == 0 ? 4 : 2;
		tiles[row][col] = new Tile(val);
	}

	// �ƶ���move����
	// �β�ΪcountDownFrom��y�ĸı�ֵ��x�ĸı�ֵ
	private boolean move(int countDownFrom, int yIncr, int xIncr) {

		boolean moved = false;
		
		//����ÿһ����
		for (int i = 0; i < side * side; i++) {
			int j = Math.abs(countDownFrom - i);
			//����{0,1,2,3}
			int r = j / side;
			//����{0,1,2,3}
			int c = j % side;
			if (tiles[r][c] == null)
				continue;
			int nextR = r + yIncr;
			int nextC = c + xIncr;
			//�ƶ������ϱ߽�㲻��
			while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
				Tile next = tiles[nextR][nextC];
				Tile curr = tiles[r][c];
				//��һ��Ϊ�գ������ƶ�(��ֵ���տ�)
				if (next == null) {
					if (checkingAvailableMoves)
						return true;
					tiles[nextR][nextC] = curr;
					tiles[r][c] = null;
					r = nextR;
					c = nextC;
					nextR += yIncr;
					nextC += xIncr;
					moved = true;
				} else if (next.canMergeWith(curr)) { //�ж��Ƿ���Ժϲ�
					if (checkingAvailableMoves)
						return true;
					int value = next.mergeWith(curr);
					
					//�ж��Ƿ�����µ����ֵ��
					if (value > highest)
						highest = value;
					score += value;
					
					//���ԭλ�÷���
					tiles[r][c] = null;
					moved = true;
					break;
				} else
					break;
			}
		}
		
		if (moved) {
			System.out.println("highest:"+highest);
			System.out.println("score:"+score);
			//�����ֵ��ﵽĿ��ʤ������
			if (highest < target) {
				clearMerged();
				addRandomTile();
				if (!movesAvailable()) {
					gamestate = State.over;
				}
			} else if (highest == target)
				gamestate = State.won;
		}
		return moved;
	}

	//move...�������Ӧ�����ƶ�һ��
	// �����ƶ�y����-1��x���겻��
	//���������ƶ����ȴ����ϱ���
	//�����������ȴ����±���
	boolean moveUp() {
		return move(0, -1, 0);
	}

	// �����ƶ�y����+1��x���겻��
	boolean moveDown() {
		return move(side * side - 1, 1, 0);
	}

	// �����ƶ�y���겻�䣬x����-1
	boolean moveLeft() {
		return move(0, 0, -1);
	}

	// �����ƶ�y���겻�䣬x����+1
	boolean moveRight() {
		return move(side * side - 1, 0, 1);
	}

	
	void clearMerged() {
		for (Tile[] row : tiles)
			for (Tile tile : row)
				if (tile != null)
					tile.setMerged(false);
	}

	boolean movesAvailable() {
		checkingAvailableMoves = true;
		//�п����ƶ�
		boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
		checkingAvailableMoves = false;
		return hasMoves;
	}

	// ������
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// ��������
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("2048");
			f.setResizable(true);
			// ��ʼ���湹�췽��
			f.add(new Game2048(), BorderLayout.CENTER);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		});
	}
}

//���Tile��
class Tile {

	// �ÿ��ڱ����ƶ����Ƿ�ϲ���
	private boolean merged;
	private int value;

	Tile(int val) {
		value = val;
	}

	int getValue() {
		return value;
	}

	void setMerged(boolean m) {
		merged = m;
	}

	// �Ƿ���Ժ���һ��ϲ��ķ���
	boolean canMergeWith(Tile other) {
		// ֻ�е������Լ���mergedΪFalse����һ���null����һ���mergedΪFalse�������ֵ��ͬ���Ժϲ�
		return !merged && other != null && !other.merged && value == other.getValue();
	}

	// �ϲ�����
	int mergeWith(Tile other) {
		if (canMergeWith(other)) {
			value *= 2;
			merged = true;
			return value;
		}
		return -1;
	}
}