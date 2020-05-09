package application;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowStateListener;
import java.sql.Time;

import Model.Background;
import Model.Mario;
import Model.allResourceInit;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application{

	private Background bg;
	private Mario mario;
	private AnimationTimer timer;

	@Override
	public void start(Stage primaryStage) {
		try {

			allResourceInit.Init();
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root,900,600);
			Canvas canvas = new Canvas(900,600);
			GraphicsContext gc = canvas.getGraphicsContext2D();


			bg = new Background(0);
			mario = new Mario(0,480, bg);

			scene.setOnKeyPressed(e -> {
				if(e.getCode().getName() == "Left"){
					this.mario.leftMove();
				}
				if(e.getCode().getName() == "Right"){
					this.mario.rightMove();
				}
				if(e.getCode().getName() == "Up"){
					this.mario.jump();
				}
			});
			scene.setOnKeyReleased(e -> {
				if(e.getCode().getName() == "Left"){
					this.mario.leftStop();
				}
				if(e.getCode().getName() == "Right"){
					this.mario.rightStop();
				}
			});

			root.getChildren().add(canvas);
			timer = new AnimationTimer(){
				@Override
				public void handle(long now) {

					gc.drawImage( bg.getBgImage(), 0., 0.);

					//绘制金币
					bg.getCoins().forEach(coin ->{
						gc.drawImage(coin.getImage(), coin.getX(), coin.getY());
					});

					//绘制敌人
					bg.getEnmies().forEach(enemy -> gc.drawImage(enemy.getEnemyImage(),enemy.getX(),enemy.getY()));
					bg.getFlowers().forEach(flower -> gc.drawImage(flower.getEnemyImage(), flower.getX(), flower.getY()));

					//绘制障碍物
					bg.getObstructions().forEach(ob -> gc.drawImage(ob.getObImage(), ob.getX(), ob.getY()));

					//绘制地面
					bg.getFloor().forEach(floor -> gc.drawImage(floor.getFloorImage(), floor.getX(), floor.getY()));
					//绘制pipe
					bg.getPipe().forEach(pipe -> gc.drawImage(pipe.getImage(), pipe.getX(), pipe.getY()));

					//System.out.println(mario.getStatus()+" "+bg.getObstructions().size());

					if(mario.getX()>850){
						int  index = bg.getIndex() + 1;
						bg = new Background(index);
						mario.setX(0);
						mario.setY(480);
						mario.setBg(bg);
					}
					if(mario.getX()<-30 && bg.getIndex()>0){
						int  index = bg.getIndex() - 1;
						bg = new Background(index);
						mario.setX(0);
						mario.setY(480);
						mario.setBg(bg);
					}
					gc.drawImage(mario.getMarioImage(), mario.getX(), mario.getY());

					if(mario.getLife()==0){
						timer.stop();
						Stage alert = new Stage();
					    alert.setTitle("提示");
					    //modality要使用Modality.APPLICATION_MODEL

					    alert.setMinWidth(300);
					    alert.setMinHeight(150);

					    Button button = new Button("确定");
					    button.setOnAction(e -> System.exit(0));
					    Label label = new Label("Game over");

					    VBox layout = new VBox(10);
					    layout.setAlignment(Pos.CENTER);

					    layout.getChildren().addAll(label , button);

					    Scene scene1 = new Scene(layout);
					    alert.setScene(scene1);
					    alert.show();

					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}


			};
			timer.start();
			new Thread(mario).start();


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Super Mario");
			primaryStage.show();

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});

		} catch(Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		launch(args);


	}
}
