package Model;

import javafx.scene.image.Image;

public class Enemy implements Runnable{
    private int x;
    private int y;
    private boolean goLeft; // enemy going to left side
    private int dead; // how long dead enemy have to be on screen
    private int moveLength; // how long is enemy step
    private Image enemyImage ;
    private int type = 0;
	private boolean isDead = false;
	private int enemyType;
	
	private Background bg;

	private int upMax;
	private int downMax;

	private Thread t = new Thread(this);

    public Enemy(int x, int y, int enemyType, Background bg) {

        this.x = x;
        this.y = y;
        this.bg = bg;

        this.goLeft = true;
        this.dead = 0;
        this.moveLength = 10;
        this.enemyType = enemyType;
        setEnemyImage(type);

        t.start();
    }


	@Override
	public void run() {
		while(!isDead){
			boolean canLeft = true;
			boolean canRight = true;
			boolean canUp = true;
			boolean canDown = true;
			
			for(int i =0; i < bg.getPipe().size();i++){
 				if(x - 60 == bg.getPipe().get(i).getX() && y<=bg.getPipe().get(i).getY()+60 && y>=bg.getPipe().get(i).getY()-60){
 	 				canLeft = false;
 	 				goLeft = false;
 	 			}
 				if(x + 60 == bg.getPipe().get(i).getX() && y<bg.getPipe().get(i).getY()+50 && y>bg.getPipe().get(i).getY()-50){
 	 				canRight = false;
 	 				goLeft = true;
 	 			}
 				if(x==0){
 					canLeft = false;
 	 				goLeft = false;
 				}
 				if(x==840){
 					canRight = false;
 	 				goLeft = true;
 				}
 			} 
			
 			for(int i = 0;i<bg.getObstructions().size(); i++){
 				if(x - 60== bg.getObstructions().get(i).getX() && y<bg.getObstructions().get(i).getY()+50 && y>bg.getObstructions().get(i).getY()-60){
 					canLeft = false;
 				}
 				if(x + 60 == bg.getObstructions().get(i).getX() && y<bg.getObstructions().get(i).getY()+50 && y>bg.getObstructions().get(i).getY()-60){
 					canRight =false;
 				}
 			}
 			
			if(enemyType == 1){
				if(this.isGoLeft() && canLeft){
					this.x -= 5;
				}
				if(!this.isGoLeft() && canRight){
					this.x += 5;
				}
				if(type == 0){
					type = 1;
				}else{
					type = 0;
				}
				setEnemyImage(type);
			}
			
			if(enemyType == 2){
				
				if(y==540){
 					canDown = false;
 	 				goLeft = true;
 				}
 				if(y == 430){
 					canUp = false;
 	 				goLeft = false;
 				}
				if(!this.goLeft && canDown){
					this.y += 5;
				}
				if(this.goLeft && canUp){
					this.y -= 5;
				}
				if(type == 0){
					type = 1;
				}else{
					type = 0;
				}
				
				if(goLeft && x == upMax){
					goLeft = false;
				}
				if(!goLeft && x == downMax){
					goLeft = true;
				}
				setEnemyImage(type);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getDead() {
		return dead;
	}

	public void setDead(int dead) {
		this.dead = dead;
	}

	public int getMoveLength() {
		return moveLength;
	}

	public void setMoveLength(int moveLength) {
		this.moveLength = moveLength;
	}

	public Image getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(int type) {

		enemyImage = allResourceInit.enemyImages.get(type);

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isGoLeft() {
		return goLeft;
	}

	public void setY(int y) {
		this.y = y;
	}


	public int getUpMax() {
		return upMax;
	}


	public void setUpMax(int upMax) {
		this.upMax = upMax;
	}


	public int getDownMax() {
		return downMax;
	}


	public void setDownMax(int downMax) {
		this.downMax = downMax;
	}


	public Background getBg() {
		return bg;
	}


	public void setBg(Background bg) {
		this.bg = bg;
	}




}
