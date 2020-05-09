/*References:
https://zhuanlan.zhihu.com/p/59600990
https://www.zhihu.com/question/267289552/answer/330775596
https://blog.csdn.net/qq_37482202/article/details/88987007
https://hackernoon.com/code-a-java-game-with-almost-zero-coding-skills-z442w31dh
https://www.quora.com/How-could-you-make-a-game-using-only-Java
http://zetcode.com/tutorials/javagamestutorial/minesweeper/
https://codegym.cc/projects/games/com.codegym.games.minesweeper
https://www.iteye.com/blog/bylijinnan-1468985

作者：James_Cui
感谢William对于游戏的评测^^
*/

public class StartGame{
	public static void main(String[] args) {
		MineSweeper NewGame=new MineSweeper();
		NewGame.run();
	}
}
