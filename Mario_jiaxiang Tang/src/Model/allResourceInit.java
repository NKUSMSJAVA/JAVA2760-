package Model;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class allResourceInit {
	public static List<Image> marioImages = new ArrayList<Image>();
	public static List<Image> stoneImages = new ArrayList<Image>();
	public static List<Image> pipeImages = new ArrayList<Image>();
	public static List<Image> enemyImages = new ArrayList<Image>();
	public static List<Image> coinImages = new ArrayList<Image>();
	public static List<Image> flowerImages = new ArrayList<Image>();
	public static Image backgroundImage = null;
	public static Image endBackgroundImage = null;
	public static Image marioDead = null;
	
	URL classPath = getClass().getClassLoader().getResource("Resource");
	public static String path = "File:C:/Users/Administrator/workspace/Mario/src/Resource/";
	

	public static void Init() {
		
		//load Mario images
		for(int i =1; i<=10; i++){
			marioImages.add(new Image(path+i+".png"));
		}
		
		//load obstruction floor pipe images
		for (int i =1; i<=7; i++){
			if(i<=4){
				pipeImages.add(new Image(path+"pipe"+i+".gif"));
			}
			stoneImages.add(new Image(path+"stone"+i+".gif"));				
		}
		
		backgroundImage = new Image(path+"firststage.gif");	
		marioDead = new Image(path + "marioDead.png");
		endBackgroundImage = new Image(path + "firststageend.gif");
		//load triangle
		for(int i =1; i <= 3; i++){
			enemyImages.add(new Image(path+"triangle"+i+".gif"));
			//coinImages.add(new Image(path+"coin"+(i-1)+".png"));
		}
		for(int i=1; i<=2; i++){
			flowerImages.add(new Image(path+"flower"+i+".gif"));
		}
	}
}
