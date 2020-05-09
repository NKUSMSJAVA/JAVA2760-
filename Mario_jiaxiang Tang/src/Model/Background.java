package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background implements Runnable {
	//private Mario mario;
	private Image bgImage;
	private List<Obstruction> obstructions = new ArrayList<Obstruction>();
	private List<Pipe> pipe = new ArrayList<Pipe>();
	private List<Floor> floor =new ArrayList<Floor>();
	private List<Enemy> enemies =new ArrayList<Enemy>();
	private List<Coin> coins = new ArrayList<Coin>();
	private List<Flower> flowers = new ArrayList<Flower>();
	
	private List<Obstruction> removedObstructions = new ArrayList<Obstruction>();
	private List<Enemy> removedEnemies =new ArrayList<Enemy>();
	private List<Coin> removeedCoins = new ArrayList<Coin>();
	private List<Flower> removedFlowers = new ArrayList<Flower>();

	private List<Obstruction> backedObstructions = new ArrayList<Obstruction>();
	private List<Enemy> backedEnemies =new ArrayList<Enemy>();
	private List<Coin> backedCoins = new ArrayList<Coin>();
	private List<Flower> backedFlowers = new ArrayList<Flower>();
	
	private int index;
	private List<Obstruction> hiddenObstructions = new ArrayList<Obstruction>();
	
	public Background(int index){

		this.index = index;
		
		//加入背景
		bgImage = allResourceInit.backgroundImage;
		
		if(index == 0){
			//加入地面
			for (int i =0; i<15; i++){
				floor.add(new Floor(i*60, 540));
			}
			//加入障碍物
			this.obstructions.add(new Obstruction(120,360,2));
			this.obstructions.add(new Obstruction(360,360,4));
			this.obstructions.add(new Obstruction(420,360,2));
			this.obstructions.add(new Obstruction(480,360,4));
			//加入pipe
			this.pipe.add(new Pipe(240,490,allResourceInit.pipeImages.get(0)));
			this.pipe.add(new Pipe(300,490,allResourceInit.pipeImages.get(1)));
			this.pipe.add(new Pipe(240,550,allResourceInit.pipeImages.get(2)));
			this.pipe.add(new Pipe(300,550,allResourceInit.pipeImages.get(3)));
			//加入敌人
			this.enemies.add(new Enemy(600,480,1,this));
			this.flowers.add(new Flower(270,540,2,this));
			//this.enemies.add(new Enemy(270,550,2));
			
			obstructions.forEach(ob ->{
				backedObstructions.add(ob);
			});
			}
			
			if(index == 1){
				
				//加入地面
				for (int i =0; i<15; i++){
					floor.add(new Floor(i*60, 540));
				}
				
				//加入障碍物
				this.obstructions.add(new Obstruction(120,360,2));
				this.obstructions.add(new Obstruction(360,300,4));
				this.obstructions.add(new Obstruction(420,300,2));
				this.obstructions.add(new Obstruction(480,300,4));
				
				//加入pipe
				this.pipe.add(new Pipe(240,490,allResourceInit.pipeImages.get(0)));
				this.pipe.add(new Pipe(300,490,allResourceInit.pipeImages.get(1)));
				this.pipe.add(new Pipe(240,550,allResourceInit.pipeImages.get(2)));
				this.pipe.add(new Pipe(300,550,allResourceInit.pipeImages.get(3)));
				
				this.pipe.add(new Pipe(420,490,allResourceInit.pipeImages.get(0)));
				this.pipe.add(new Pipe(480,490,allResourceInit.pipeImages.get(1)));
				this.pipe.add(new Pipe(420,550,allResourceInit.pipeImages.get(2)));
				this.pipe.add(new Pipe(480,550,allResourceInit.pipeImages.get(3)));
				
				//加入敌人
				this.enemies.add(new Enemy(600,480,1,this));
				this.flowers.add(new Flower(270,510,2,this));
				this.flowers.add(new Flower(460,550,2,this));
				
				//back data
				
				obstructions.forEach(ob ->{
					backedObstructions.add(ob);
				});
			}		
			
			
			if(index==2){
				//加入地面
				for (int i =0; i<15; i++){
					if(i<=2)
						{floor.add(new Floor(i*60, 540));}
					if(i>=13){
						{floor.add(new Floor(i*60, 540));}
					}
				}
				
				//加入障碍物
				obstructions.add(new Obstruction(180, 420, 2));
				obstructions.add(new Obstruction(180, 240, 3));
				
				//hidden关卡
				hiddenObstructions.add(new Obstruction(300,420,0));
				hiddenObstructions.add(new Obstruction(360,420,0));
				hiddenObstructions.add(new Obstruction(420,420,0));
				hiddenObstructions.add(new Obstruction(480,420,0));
				hiddenObstructions.add(new Obstruction(180,240,2));
				
				//back data
				
				obstructions.forEach(ob ->{
					backedObstructions.add(ob);
				});
			}
			
			if(index == 3){
				this.bgImage = allResourceInit.endBackgroundImage;
				
				//加入地面
				for (int i =0; i<15; i++){
					floor.add(new Floor(i*60, 540));
				}
				
				//加入障碍物
				for(int i = 0; i<=5; i++){
					for(int j = i; j>=0; j--){
						obstructions.add(new Obstruction(120+60*i, 480-j*60, 1));
					}
				}
				
				obstructions.forEach(ob ->{
					backedObstructions.add(ob);
				});
			}
	}

	public List<Pipe> getPipe() {
		return pipe;
	}

	public void setPipe(List<Pipe> pipe) {
		this.pipe = pipe;
	}

	public List<Floor> getFloor() {
		return floor;
	}

	public void setFloor(List<Floor> floor) {
		this.floor = floor;
	}

	public void setBgImage(Image bgImage) {
		this.bgImage = bgImage;
	}

	public List<Obstruction> getObstructions() {
		return obstructions;
	}

	public void setObstructions(List<Obstruction> obstructions) {
		this.obstructions = obstructions;
	}

	public Image getBgImage() {
		return bgImage;
	}

	public List<Enemy> getEnmies() {
		return enemies;
	}

	public void setEnmies(List<Enemy> enmies) {
		this.enemies = enmies;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the removedObstructions
	 */
	public List<Obstruction> getRemovedObstructions() {
		return removedObstructions;
	}

	/**
	 * @param removedObstructions the removedObstructions to set
	 */
	public void setRemovedObstructions(List<Obstruction> removedObstructions) {
		this.removedObstructions = removedObstructions;
	}

	/**
	 * @return the removedEnemies
	 */
	public List<Enemy> getRemovedEnemies() {
		return removedEnemies;
	}

	/**
	 * @param removedEnemies the removedEnemies to set
	 */
	public void setRemovedEnemies(List<Enemy> removedEnemies) {
		this.removedEnemies = removedEnemies;
	}

	/**
	 * @return the removeedCoins
	 */
	public List<Coin> getRemoveedCoins() {
		return removeedCoins;
	}

	/**
	 * @param removeedCoins the removeedCoins to set
	 */
	public void setRemoveedCoins(List<Coin> removeedCoins) {
		this.removeedCoins = removeedCoins;
	}

	/**
	 * @return the removedFlowers
	 */
	public List<Flower> getRemovedFlowers() {
		return removedFlowers;
	}

	/**
	 * @param removedFlowers the removedFlowers to set
	 */
	public void setRemovedFlowers(List<Flower> removedFlowers) {
		this.removedFlowers = removedFlowers;
	}

	@Override
	public void run() {
		while(true){


		}
	}

	public void reset() {
		
	/*	coins = backedCoins;
		enemies = backedEnemies;
		flowers = backedFlowers;
		obstructions = backedObstructions;
*/
		removedEnemies.forEach(enemy ->{
			enemies.add(enemy);
		});
		
		removedFlowers.forEach(flower -> {
			flowers.add(flower);
		});
		
		obstructions.removeAll(obstructions);
		
		backedObstructions.forEach(ob ->{
			obstructions.add(ob);
		});
	}

	public void setHiddenObstructions() {
		hiddenObstructions.forEach( ob-> {
			obstructions.add(ob);
		});
	}

	/**
	 * @return the hiddenObstructions
	 */
	public List<Obstruction> getHiddenObstructions() {
		return hiddenObstructions;
	}

	/**
	 * @param hiddenObstructions the hiddenObstructions to set
	 */
	public void setHiddenObstructions(List<Obstruction> hiddenObstructions) {
		this.hiddenObstructions = hiddenObstructions;
	}

}
