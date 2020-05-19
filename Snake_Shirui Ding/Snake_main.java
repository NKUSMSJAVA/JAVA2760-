/*
   基于Java Swing实现的贪吃蛇游戏，运行该程序开始游戏
   作者:丁世瑞
   学号:1710022
*/
import javax.swing.JFrame;

public class Snake_main {
	public static void main(String[] args) {
		JFrame frame =new JFrame("Snake");
		frame.setBounds(10,10,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnakePanel());
		frame.setVisible(true);
	}

}
