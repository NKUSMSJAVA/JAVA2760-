/*
    SnakePanel.java:实现贪吃蛇游戏进行中的界面设计
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakePanel extends JPanel implements KeyListener, ActionListener{
	
	//导入蛇身体，蛇头以及食物的图形
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon food = new ImageIcon("food.png");
	
	//初始化蛇的数据，蛇长度，得分
	int len=3;
	int score=0;
	int[] snake_x=new int[750];
	int[] snake_y=new int[750];
	String fx ="R"; //方向:R,L,U,D,控制蛇运动方向
	boolean isStarted=false; //代表游戏开始
	boolean isFailed=false; //代表游戏结束
	Timer timer=new Timer(StartFrame.Gamespeed,this); //设置闹钟
	
	//食物坐标
	int food_x;
	int food_y;
	
	Random rand =new Random(); //用于生成随机位置的食物
	public SnakePanel() {
		initSnake(); //游戏界面初始化
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		title.paintIcon(this,g,25,11);
		g.fillRect(25, 75, 850, 600);
		g.setColor(Color.WHITE);
		//显示蛇长度，当前得分
		g.drawString("Len:"+len, 750, 35);
		g.drawString("Score:"+score, 750, 50);
		
        //设置classic地图模式下的地图
		if (StartFrame.GameMode==1) {
			g.setColor(Color.GREEN);
			for(int i=1;i<35;i++) {
				g.fillRect(25*i, 75,25,25);
				g.fillRect(25*i, 650, 25, 25);
			}
			for (int j=1;j<23;j++) {
				g.fillRect(25, 75+25*j, 25, 25);
				g.fillRect(850, 75+25*j, 25, 25);
			}
			
		}

		
		//根据蛇头的方向导入不同朝向的蛇头图片
		if(fx=="R") {
			right.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="L") {
			left.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="D") {
			down.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="U") {
			up.paintIcon(this, g, snake_x[0], snake_y[0]);
		}
		
		//导入蛇身体图片
		for (int i=1;i<len;i++) {
			body.paintIcon(this, g, snake_x[i], snake_y[i]);
		}
		
		//导入食物图片
		food.paintIcon(this, g, food_x, food_y);
		
		//如果游戏没开始，界面显示按空格键开始游戏
		if (isStarted==false) {
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("arial",Font.BOLD,40));
		    g.drawString("Press Space to Start",260,300);
		}
		
		//如果游戏结束了，界面显示游戏结束，按空格键重新开始
		if(isFailed) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,40));
			g.drawString("Game Over:Press Space to Restart", 130, 300);
		}
	}
	
	//初始化蛇游戏开始时蛇，食物的位置，清零得分
	public void initSnake() {
		len=3;
		fx="R";
		snake_x[0]=100;
		snake_y[0]=100;
		snake_x[1]=75;
		snake_y[1]=100;
		snake_x[2]=50;
		snake_y[2]=100;
		
		//根据游戏模式的不同生成随机食物位置
		switch (StartFrame.GameMode) {
		    case 1:{
			    food_x=50+25*rand.nextInt(32);
			    food_y=100+25*rand.nextInt(22);
			    break;
		    }
		    case 2:{
		    	food_x=25+25*rand.nextInt(34);
				food_y=75+25*rand.nextInt(24);
				break;
		    }
		}
		
		//处理食物位置与蛇身体位置重叠的情况
		for (int i=1;i<len;i++) {
			while (snake_x[i]==food_x && snake_y[i]==food_y) {
				switch (StartFrame.GameMode) {
				    case 1:{
					    food_x=50+25*rand.nextInt(32);
					    food_y=100+25*rand.nextInt(22);
					    break;
				    }
				    case 2:{
				    	food_x=25+25*rand.nextInt(34);
						food_y=75+25*rand.nextInt(24);
						break;
				    }
			    }
			}
		}
		score=0;
	}
	
	@Override
	//实现游戏中按键作用
	public void keyPressed(KeyEvent e) {
		int keyCode =e.getKeyCode();
		/*实现在游戏未开始时按空格开始游戏
		  游戏开始时，按空格暂停游戏
		  游戏结束时，按空格键重新开始
		*/
		if(keyCode==KeyEvent.VK_SPACE) {
			if(isFailed) {
				isFailed=false;
				initSnake();
			}
			isStarted=!isStarted;
			repaint();
			
		//通过上下左右按键控制蛇移动的方向
		}else if (keyCode==KeyEvent.VK_LEFT && fx!="R") {
			fx="L";
		}else if (keyCode==KeyEvent.VK_RIGHT && fx!="L") {
			fx="R";
		}else if (keyCode==KeyEvent.VK_UP && fx!="D") {
			fx="U";
		}else if (keyCode==KeyEvent.VK_DOWN && fx!="U") {
			fx="D";
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	//
	public void actionPerformed(ActionEvent arg0) {
		if (isStarted && !isFailed) {
			//按照闹钟计时速度移动蛇身
		    for (int i=len-1;i>0;i--) {
		    	snake_x[i]=snake_x[i-1];
		    	snake_y[i]=snake_y[i-1];
	    	}
		    if (fx=="R") {
		    	snake_x[0]=snake_x[0]+25;
		        if(snake_x[0]>850) snake_x[0]=25;
		    }else if(fx=="L") {
		    	snake_x[0]=snake_x[0]-25;
		        if(snake_x[0]<25) snake_x[0]=850;
		    }else if(fx=="U") {
		    	snake_y[0]=snake_y[0]-25;
		        if(snake_y[0]<75) snake_y[0]=650;
		    }else if(fx=="D") {
		    	snake_y[0]=snake_y[0]+25;
		        if(snake_y[0]>670) snake_y[0]=75;
		    }
		    
		    //蛇吃到食物后，重新生成新的食物，蛇的身体增长，分数增加
		    if (snake_x[0]==food_x && snake_y[0]==food_y) {
		    	len++;
		    	snake_x[len-1]=snake_x[len-2];
		    	snake_y[len-1]=snake_y[len-2];
		    	score=score+10;
		    	
		    	//重新生成食物
		    	switch (StartFrame.GameMode) {
				    case 1:{
					    food_x=50+25*rand.nextInt(32);
					    food_y=100+25*rand.nextInt(22);
					    break;
				    }
				    case 2:{
				    	food_x=25+25*rand.nextInt(34);
						food_y=75+25*rand.nextInt(24);
						break;
				    }
			    }
		    	for (int i=1;i<len;i++) {
					while (snake_x[i]==food_x && snake_y[i]==food_y) {
						switch (StartFrame.GameMode) {
						    case 1:{
							    food_x=50+25*rand.nextInt(32);
							    food_y=100+25*rand.nextInt(22);
							    break;
						    }
						    case 2:{
						    	food_x=25+25*rand.nextInt(34);
								food_y=75+25*rand.nextInt(24);
								break;
						    }
					    }
					}
				}
		    }
		    
		    //蛇头撞到蛇身，或者蛇头撞入墙壁，游戏结束
		    for (int i=1;i<len;i++) {
		    	if (StartFrame.GameMode==1) {
		    		if (snake_x[0]==25 || snake_x[0]==850 || snake_y[0]==75 ||snake_y[0]==650) {
		    			isFailed=true;
		    		} 
		    	}
		    	if(snake_x[i]==snake_x[0] && snake_y[i]==snake_y[0]) {
		    		isFailed=true;
		    	}
		    }
		    repaint();
		}
		timer.start();
	}
}
