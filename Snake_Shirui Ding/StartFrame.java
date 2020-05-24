/*
  StartPanel.java:实现贪吃蛇游戏开始前设置界面
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartFrame extends JFrame{
	//创建初始设置窗口
	public static int Gamespeed=150; //默认游戏难度简单
	public static int GameMode=2;  //默认无墙壁地图
	public JFrame frame =new JFrame("Snake");
	public StartFrame() {
		frame.setBounds(10,10,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new StartPanel());
		frame.setVisible(true);
	};
    
	//在窗口中添加按钮，背景等组件
	public class StartPanel extends JPanel implements ActionListener{
		
		//初始化
		private ButtonGroup map;
		private ButtonGroup level;
		private int addressx1=380;
		private int addressy1=200;
		private int addressx2=380;
		private int addressy2=400;
		
		ImageIcon title=new ImageIcon("title.jpg");
		JButton StartButton=new JButton("Start!");
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//画出主界面背景
			this.setBackground(Color.WHITE);
			title.paintIcon(this,g,25,11); //导入title图片
			g.fillRect(25, 75, 850, 600);
			g.setColor(Color.WHITE);
			
			//画出地图，难度标题
			g.drawString("Map:", 300, 200);
			g.drawString("Level:", 300, 400);
		}
		public StartPanel() {
			this.setLayout(null);
			//添加开始按钮
		    super.add(StartButton);
		    StartButton.setBounds(410, 560, 80, 40);
		    StartButton.addActionListener(this);
		    map=new ButtonGroup();
			level=new ButtonGroup();
			
		    //添加地图选择单选钮
			addRadioButtonOfMap("No wall",2);
			addRadioButtonOfMap("Classic",1);
		    
		    //添加难度选择单选钮
			addRadioButtonOfLevel("Easy",120);
			addRadioButtonOfLevel("Normal",80);
			addRadioButtonOfLevel("Hard",40);
		}
		
		//更换地图方法实现
	    private void addRadioButtonOfMap(String KindOfMap,int mode) {
	    	boolean selected=mode==2;
			JRadioButton button = new JRadioButton(KindOfMap,selected);
			map.add(button);
			super.add(button);
			button.setBounds(addressx1,addressy1,80,40);
			ActionListener listener=event->GameMode=mode;
			button.addActionListener(listener);
			addressx1=addressx1+100;
			
		}
	    
		//添加难度选择单选钮的方法实现
		private void addRadioButtonOfLevel(String name, int speed) {
			boolean selected=speed==120;
			JRadioButton button = new JRadioButton(name,selected);
			level.add(button);
			super.add(button);
			button.setBounds(addressx2, addressy2, 80, 40);
			ActionListener listener=event->Gamespeed=speed;
			button.addActionListener(listener);
			addressx2=addressx2+100;
		}
		
		@Override
		//实现点击Start!按钮进入游戏进行界面
		public void actionPerformed(ActionEvent e) {
	        frame.dispose();
			JFrame frame1 =new JFrame("Snake");
			frame1.setBounds(10,10,900,720);
			frame1.setResizable(false);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.add(new SnakePanel());
			frame1.setVisible(true);
		}	
	}
}
