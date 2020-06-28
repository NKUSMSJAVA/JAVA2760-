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
	// 网格颜色
	private Color gridColor = new Color(0xBBADA0);
	// 方块颜色
	private Color emptyColor = new Color(0xCDC1B4);
	// 开始界面颜色
	private Color startColor = new Color(0xF5DEB3);
	private Random rand = new Random();
	private Tile[][] tiles;
	// 网格各边块数
	private int side = 4;
	private State gamestate = State.start;
	// 判断能否继续移动
	private boolean checkingAvailableMoves;

	// 构造方法
	public Game2048() {
		setPreferredSize(new Dimension(900, 700));
		setBackground(new Color(0xFAF8EF));
		setFont(new Font("SansSerif", Font.BOLD, 48));

		// 焦点
		setFocusable(true);

		// 鼠标监听
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				startGame(); // 开始游戏方法
				repaint(); // 刷新画板
			}
		});

		// 键盘监听
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

	// 开始游戏方法
	void startGame() {
		if (gamestate != State.running) {
			score = 0;
			highest = 0;
			gamestate = State.running;

			// 给方块分配内存空间
			tiles = new Tile[side][side];

			// 开始时随机生成两个块
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
			g.drawString("点击任意区域开始游戏", 350, 470);
			g.drawString("(使用↑ ↓ ← →方向键移动方块)", 310, 530);
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

		// 0-15的数代表一个块
		// 随机在空白块生成两个块
		do {
			pos = (pos + 1) % (side * side);
			row = pos / side;
			col = pos % side;
		} while (tiles[row][col] != null);
		int val = rand.nextInt(10) == 0 ? 4 : 2;
		tiles[row][col] = new Tile(val);
	}

	// 移动的move方法
	// 形参为countDownFrom，y的改变值，x的改变值
	private boolean move(int countDownFrom, int yIncr, int xIncr) {

		boolean moved = false;
		
		//遍历每一个块
		for (int i = 0; i < side * side; i++) {
			int j = Math.abs(countDownFrom - i);
			//行数{0,1,2,3}
			int r = j / side;
			//列数{0,1,2,3}
			int c = j % side;
			if (tiles[r][c] == null)
				continue;
			int nextR = r + yIncr;
			int nextC = c + xIncr;
			//移动方向上边界点不动
			while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
				Tile next = tiles[nextR][nextC];
				Tile curr = tiles[r][c];
				//下一点为空，可以移动(赋值给空块)
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
				} else if (next.canMergeWith(curr)) { //判断是否可以合并
					if (checkingAvailableMoves)
						return true;
					int value = next.mergeWith(curr);
					
					//判断是否出现新的最大值块
					if (value > highest)
						highest = value;
					score += value;
					
					//清空原位置方块
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
			//块最大值否达到目标胜利条件
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

	//move...方法向对应方向移动一格
	// 向上移动y坐标-1，x坐标不变
	//向上向左移动优先从左上遍历
	//向下向右优先从右下遍历
	boolean moveUp() {
		return move(0, -1, 0);
	}

	// 向下移动y坐标+1，x坐标不变
	boolean moveDown() {
		return move(side * side - 1, 1, 0);
	}

	// 向左移动y坐标不变，x坐标-1
	boolean moveLeft() {
		return move(0, 0, -1);
	}

	// 向右移动y坐标不变，x坐标+1
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
		//有可用移动
		boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
		checkingAvailableMoves = false;
		return hasMoves;
	}

	// 主方法
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// 窗体设置
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("2048");
			f.setResizable(true);
			// 开始界面构造方法
			f.add(new Game2048(), BorderLayout.CENTER);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		});
	}
}

//块的Tile类
class Tile {

	// 该块在本次移动中是否合并了
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

	// 是否可以和另一块合并的方法
	boolean canMergeWith(Tile other) {
		// 只有当满足自己的merged为False，另一块非null，另一块的merged为False，两块的值相同可以合并
		return !merged && other != null && !other.merged && value == other.getValue();
	}

	// 合并方法
	int mergeWith(Tile other) {
		if (canMergeWith(other)) {
			value *= 2;
			merged = true;
			return value;
		}
		return -1;
	}
}