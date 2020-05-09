package Model;

import javafx.scene.image.Image;

public class Mario implements Runnable{

	private int x;
	private int y;

	private int xmove = 0;
 	private int ymove = 0;
 	private int jumpTime =0;

 	private Background bg;

 	private Thread t = null;
 	private Image marioImage;

 	private String status;
 	private int moving = 0;
 	
 	private int deadTime = 0;

 	//private int score;
 	private int life;

 	public Mario(int x, int y, Background bg) {
 		this.x = x;
 		this.y = y;
 		this.bg = bg;

 		this.marioImage = allResourceInit.marioImages.get(0);
 		this.life = 3;

 		this.status = "right standing";

 		t = new Thread(this);
 		t.start();
 	}
 	
 	//º”»Îœﬂ≥Ãøÿ÷∆≈–∂œ◊¥Ã¨
 	@Override
 	public void run() {
 		while(life>0){
 			if(deadTime == 0){
 			
	 			boolean canLeft = true;
	 			boolean canRight = true;
	 			boolean onLand = false;
 		
 //////////////////////////////////////////////////≈ˆ◊≤≈–∂œ///////////////////////////////////////////
	 			//pipe≈–∂œ
	 			for(int i =0; i < bg.getPipe().size();i++){
	 				
	 				if(x - 60 == bg.getPipe().get(i).getX() 
	 						&& y<=bg.getPipe().get(i).getY()+60 
	 						&& y>bg.getPipe().get(i).getY()-60){
	 	 				canLeft = false;
	 	 			}
	 				
	 				if(x + 45 == bg.getPipe().get(i).getX() 
	 						&& y<bg.getPipe().get(i).getY()+50 
	 						&& y>bg.getPipe().get(i).getY()-60){
	 	 				canRight = false;
	 	 			}
	 				
	 				if(y + 60 == bg.getPipe().get(i).getY() 
	 						&& x<bg.getPipe().get(i).getX() + 60 
	 						&& x > bg.getPipe().get(i).getX()-45){
	 					onLand=true;
	 				}
	 				if(jumpTime==0 && y + 60 == bg.getPipe().get(i).getY() 
	 						&& x<=bg.getPipe().get(i).getX() + 60 
	 						&& x >= bg.getPipe().get(i).getX()-60){
	 					onLand=true;
	 				}
	 				
	 			} 
	 			
	 			
	 			//ø’÷–’œ∞≠ŒÔ≈–∂œ
	 			for(int i = 0;i<bg.getObstructions().size(); i++){
	 				
	 				//from right
	 				if(x - 60 == bg.getObstructions().get(i).getX() 
	 						&& y<=bg.getObstructions().get(i).getY()+60 
	 						&& y>bg.getObstructions().get(i).getY()-60){
	 					canLeft = false;//120,300 180,360
	 				}
	 				//from left
	 				if(x + 45 == bg.getObstructions().get(i).getX() 
	 						&& y<=bg.getObstructions().get(i).getY()+60 
	 						&& y>bg.getObstructions().get(i).getY()-60){
	 					canRight =false;
	 				}
	 				
	 				if(y + 60 == bg.getObstructions().get(i).getY() 
	 						&& x<bg.getObstructions().get(i).getX() + 61 
	 						&& x > bg.getObstructions().get(i).getX()-46){
	 					onLand=true;
	 				}
	 				
	 				//∂•µΩ’œ∞≠ŒÔ
	 				if(y - 60 == bg.getObstructions().get(i).getY() 
	 						&& x <= bg.getObstructions().get(i).getX() + 60 
	 						&& x > bg.getObstructions().get(i).getX()-45){
	 					
	 					if(bg.getObstructions().get(i).getType() == 4){
	 						bg.getRemovedObstructions().add(bg.getObstructions().get(i));
	 						bg.getObstructions().add(new Obstruction(bg.getObstructions().get(i).getX(),
	 								bg.getObstructions().get(i).getY(),2));
	 						bg.getObstructions().remove(i);
	 						//bg.getCoins().add(new Coin(bg.getObstructions().get(i).getX(),y-120,2));
	 					}
	 					
	 					if(bg.getObstructions().get(i).getType() == 3){
	 						
	 						bg.getRemovedObstructions().add(bg.getObstructions().get(i));
	 						bg.getObstructions().remove(i);
	 						bg.setHiddenObstructions();
	 						
	 					}
	 					
	 					jumpTime=0;
	 				}
	 				
	 			}
	 			
	 			//∂‘µÿ√Ê≈–∂œ
	 			for(int i =0; i<bg.getFloor().size();i++){
	 				if(y + 60 == bg.getFloor().get(i).getY() 
	 						&& x<bg.getFloor().get(i).getX() + 50 
	 						&& x > bg.getFloor().get(i).getX()-50){
	 					onLand=true;
	 				}
	 			}
	 			
	 			//◊Û”““∆∂Ø
	 			if(canLeft && xmove<0||(canRight && xmove>0)){
	 				x += xmove;
	 			}
	 			//≈–∂œ◊¥Ã¨
	 			if(onLand && jumpTime == 0){				//’æ‘⁄floor/ob…œ«“Ã¯‘æ ±º‰Œ™0£¨≈–∂œ◊¥Ã¨
	 				if(this.status.indexOf("left") != -1){
	 					if(xmove != 0){
	 						this.status = "left moving";
	 					}else{
	 						this.status = "left standing";
	 					}
	 				}else{
	 					if(xmove != 0){
	 						this.status = "right moving";
	 					}else{
	 						this.status = "right standing";
	 					}
	 				}
	 			}else{										
	 				if(jumpTime > 0){						//jumptime>0…œ…˝£¨jumptime--
	 					jumpTime--;
	 				}else{
	 					down();								//Jumptime=0£¨µΩ∂•£¨down
	 				}
	 				y += ymove;
	 			}
	 			
	 //////////////////////////////marioÕº∆¨øÿ÷∆////////////////////////////////////////////////////		
	 			
	 			//∂ØÃ¨∏ƒ±‰marioµƒÕº∆¨
	 			//moving
	 			if(status.indexOf("moving") != -1){
	 				moving++;
	 				if(moving == 4){
	 					moving = 0;
	 				}
	 			}
	 			//jumping
	 			if(status.indexOf("left") != -1){
	 				if(status.indexOf("jumping") == -1){
	 					setMarioImage(moving+5);
	 				}else{
	 					setMarioImage(9);
	 				}
	 			}else{
	 				if(status.indexOf("jumping") == -1){
	 					setMarioImage(moving);
	 				}else{
	 					setMarioImage(4);
	 				}
	 			}
	 			
	 ///////////////////////////////////µ–»À≈ˆ◊≤≈–∂œ/////////////////////////////////////////////////////////////
	 			//triangle
	 			for(int i = 0;i<bg.getEnemies().size();i++){
	 				//from right
	 				if(x - 60== bg.getEnemies().get(i).getX() 
	 						&& y<bg.getEnemies().get(i).getY()+50 
	 						&& y>bg.getEnemies().get(i).getY()-60){
	
	 					life--;
	 					Dead();
	 					status = "right standing";
	 	
	 				}
	 				//from left
	 				if(x < bg.getEnemies().get(i).getX()+60 
	 						&& x > bg.getEnemies().get(i).getX()-45 
	 						&& y<bg.getEnemies().get(i).getY()+50 
	 						&& y>bg.getEnemies().get(i).getY()-50){
	 					life--;
	 					
	 					Dead();
	 					
	 					status = "right standing";
	 					
	 				}
	 				//from up
	 				if( y + 60 == bg.getEnemies().get(i).getY() 
	 						&& x<bg.getEnemies().get(i).getX()+40 
	 						&& x>bg.getEnemies().get(i).getX()-40){
	 					bg.getRemovedEnemies().add(bg.getEnemies().get(i));
	 					bg.getEnemies().remove(i);
	 					if(status.indexOf("left") != -1){
	 						status="left standing";
	 					}else{
	 						status = "right standing";
	 					}
	 					jump();
	 					setJumpTime(5);
	 				}
	 			}
	 			
	 			//flower
	 			for(int i = 0;i<bg.getFlowers().size();i++){
	 				
	 				//right
	 				if(x - 60 == bg.getFlowers().get(i).getX() 
	 						&& y<bg.getFlowers().get(i).getY()+50 
	 						&& y>bg.getFlowers().get(i).getY()-60){
	 					
	 					life--;
	 					Dead();
	 					status = "right standing";
	 										
	 				}
	 				
	 				//left
	 				if(x + 35 == bg.getFlowers().get(i).getX() 
	 						&& y<bg.getFlowers().get(i).getY()+50 
	 						&& y>bg.getFlowers().get(i).getY()-60){
	 					life--;
	 					Dead();
	 					status = "right standing";
	 					
	 				}
	 				
	 				//from up
	 				if( y + 60 >= bg.getFlowers().get(i).getY() 
	 						&& x<bg.getFlowers().get(i).getX()+55 
	 						&& x>bg.getFlowers().get(i).getX()-45){
	 					life--;
	 					Dead();
	 					if(status.indexOf("right") == -1){
	 						status = "left standing";
	 					}
	 					status = "right standing";
	 					
	 				}
	 			}
	 			if(y>540){
	 				life--;
					Dead();
					status = "right standing";
					
	 			}
	 			
 			
 			}else{
 				deadTime--;
 				down();
 				y += ymove;
 				if(deadTime == 0){
 					
 					setMarioImage(0);
 					x = 0;
 					y = 470;
 					ymove = 0 ;
 					bg.reset();
 					status="right standing";
 					
 				}
 			}
 			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 	}


////////////////////////////////“∆∂Øøÿ÷∆////////////////////////////////////////////
 	public void leftMove() {
 		
 		xmove = -5;
 		if(this.status.indexOf("jumping") != -1) {
 			this.status = "left jumping";
 		}else {
 			this.status = "left moving";

 		}
 	}

 	public void rightMove() {
 		xmove = 5;
 		if(this.status.indexOf("jumping") != -1) {
 			this.status = "right jumping";
 		}else {
		   this.status = "right moving";
		 }
	}

	 public void leftStop() {
		 this.xmove = 0;
		 if(this.status.indexOf("jumping") != -1) {
			  this.status = "left jumping";
		 }else {
		   this.status = "left standing";
		 }
	 }

	 public void rightStop() {
		 this.xmove = 0;
		 if(this.status.indexOf("jumping") != -1) {
			 this.status = "right jumping";
		 }else {
			 this.status = "right standing";
		 }
	 }


	 public void jump() {
		 if(this.status.indexOf("jumping") == -1){
			 if(this.status.indexOf("left") != -1) {
				 this.status = "left jumping";
			 }else {
				 this.status = "right jumping";
			 }

			 ymove = -10;
			 jumpTime = 18;
		 }
	 }

	 public void down() {
		 if(this.status.indexOf("left") != -1){
			 this.status = "left jumping";
		 }else {
			 this.status = "right jumping";
		 }
		 ymove = 10;
	 }
	 
	 public void Dead() {
	 	xmove = 0;
	 	deadTime = 50;
	 	y = y+20;
		setMarioImage(allResourceInit.marioDead);
	}
////////////////////////////////////////////////////
	 public int getX() {
		 return x;
	 }

	 public void setX(int x) {
			this.x = x;
	}
//////////////////////////////////////////////
	public void setY(int y) {
			this.y = y;
		}
	 public int getY() {
		 return y;
	 }
////////////////////////////////////////////////////
	public int getXmove() {
		return xmove;
	}
	
	public void setXmove(int xmove) {
		this.xmove = xmove;
	}
//////////////////////////////////////////////////
	public int getYmove() {
		return ymove;
	}
	
	public void setYmove(int ymove) {
		this.ymove = ymove;
	}
//////////////////////////////////////////////////
	public int getJumpTime() {
		return jumpTime;
	}
	public void setJumpTime(int jumpTime) {
		this.jumpTime = jumpTime;
	}
//////////////////////////////////////////////////////////
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
/////////////////////////////////////////////////////////
	public int getMoving() {
		return moving;
	}
	
	public void setMoving(int moving) {
		this.moving = moving;
	}
////////////////////////////////////////////////////////

	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}

//////////////////////////////////////////////////////
	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		 this.bg = bg;
	 }
///////////////////////////////////////////////////////
	 public Image getMarioImage() {
		 return marioImage;
	 }
	 
	public void setMarioImage(int type) {
		this.marioImage = allResourceInit.marioImages.get(type);
	}

	private void setMarioImage(Image marioDead) {
		this.marioImage = marioDead;
	}
}