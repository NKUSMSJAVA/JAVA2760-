package Model;

import javafx.scene.image.Image;

public class Coin {
    private int x;
    private int y;
    private Image coinImage ;
    private int i;

    public Coin(int x, int y, int i) {

        this.x = x;
        this.y = y;
        this.i = 0;
        
        setCoinImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getI(){
    	return i;
    }
    
    public void setI(int i){
    	this.i = i;
    }
    public Image getImage() {
       return coinImage;
    }

    public void setCoinImage(){
    	coinImage = allResourceInit.coinImages.get(i);
    }
}