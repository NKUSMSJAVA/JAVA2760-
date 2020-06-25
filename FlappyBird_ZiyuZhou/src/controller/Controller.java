package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Bird;
import model.Column;
import model.Ground;

public class Controller implements Runnable{
	
	public static boolean gameStart;
	public static int score;
	
	private Bird bird;
	private Column column1;
	private Column column2;
	private Ground ground;
	
	@FXML
	private Pane worldPane;
	@FXML
    private Label scoreLabel;
    @FXML
    private ImageView birdImageView;
    @FXML
    private ImageView columnImageView1;
    @FXML
    private ImageView columnImageView2;
    @FXML
    private ImageView groundImageView;
    @FXML
    private ImageView startImageView;
    @FXML
    private ImageView restartImageView;
    
    @FXML
    public void worldPaneClicked(MouseEvent event) {
//    	System.out.println("world clicked");
		if(gameStart) {
			bird.flappy();
		}
    }
    
    @FXML
	public void handleKeyPressed(KeyEvent event) {
//    	System.out.println("Key Pressed");
    	if(event.getCode() == KeyCode.SPACE) {
    		if(!gameStart) {
        		startImageView.setVisible(false);
        		startImageView.setDisable(true);
        		restartImageView.setVisible(false);
        		restartImageView.setDisable(true);
    			gameStart = true;
    			gameStarted();
    		}
    		if(gameStart) {
    			bird.flappy();
    		}
    	}
    }
    
    @FXML
    public void startClicked(MouseEvent event) {
    	if(!gameStart) {

    		startImageView.setVisible(false);
    		startImageView.setDisable(true);
    		gameStart = true;
    		gameStarted();
    	}
    }
    
    @FXML
    public void restartClicked(MouseEvent event) {
    	if(!gameStart) {
    		
    		restartImageView.setVisible(false);
    		restartImageView.setDisable(true);
    		gameStart = true;
    		gameStarted();
    	}
 	
    }
    
    public void gameStarted() {
    	if(gameStart) {
    		System.out.println("Game Start!");  	
    		score = 0;
    		
    		birdImageView.setLayoutY(bird.getBirdY()); 
    		columnImageView1.setVisible(true);
    		columnImageView2.setVisible(true);
    		columnImageView1.setLayoutX(column1.getColumnX());
    		columnImageView1.setLayoutY(column1.getColumnY());
    		columnImageView2.setLayoutX(column2.getColumnX());
    		columnImageView2.setLayoutY(column2.getColumnY());
    		
    		groundImageView.setLayoutX(ground.getGroundX());
    		groundImageView.setLayoutY(Ground.GROUNDY);
    		
    		Platform.runLater(new Runnable() {
    		    @Override
    		    public void run() {
    		    	scoreLabel.setText("Score: " + score);
    		    }
    		});

    		Thread t = new Thread(this);
    		t.start();
    	}
    }
    
    @FXML
    void initialize() {
    	bird = new Bird();
    	column1 = new Column(1);
    	column2 = new Column(2);
    	ground = new Ground(); 
    	startImageView.setPickOnBounds(true);
    }
    
	@Override
	public void run() {
//		System.out.println("New Thread");
		while(gameStart) {
			
			bird.step();
			birdImageView.setLayoutY(bird.getBirdY());
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					birdImageView.setRotate(bird.getBirdAngle());
					birdImageView.setImage(bird.flyImage());
				}
			});
			
			column1.step();
			column2.step();
			columnImageView1.setLayoutX(column1.getColumnX());
			columnImageView1.setLayoutY(column1.getColumnY());
			columnImageView2.setLayoutX(column2.getColumnX());
			columnImageView2.setLayoutY(column2.getColumnY());
			
			ground.step();
			groundImageView.setLayoutX(ground.getGroundX());
			groundImageView.setLayoutY(Ground.GROUNDY);
			
			
			if(bird.hit(column1) || bird.hit(column2)) {
				System.out.println("hit"); 
				gameStart = false;
				
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	scoreLabel.setText("Game Over! Score: " + score);
				    }
				});

			}
			
			if(bird.pass(column1) || bird.pass(column2)) {
				score++;
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() { 
				    	scoreLabel.setText("Score: " + score);
				    }
				});

			}
			try {
				Thread.sleep(1000/70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(!gameStart) {
	    		restartImageView.setVisible(true);
	    		restartImageView.setDisable(false);
				column1.reSetColumnX();
				column2.reSetColumnX();
				bird.reSetBirdY();
			}
		}
	}
} 

