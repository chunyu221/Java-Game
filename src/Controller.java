import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable, EventHandler<KeyEvent>{
	

	@FXML
	public Pane _field;
	
	@FXML
	public ImageView ice;
	
	@FXML
	public ImageView initial_ice;
	
	@FXML
	public Circle cir;
	public ImageView role;
	
	@FXML
	public Arc arc;
	
	@FXML
	public Rectangle energy;
	
	@FXML
	public Text score;
	
	private Timeline animation;
	private boolean b = true;
	
	@Override
	public void handle(KeyEvent e) {
		
		if(e.getCode()==KeyCode.SPACE && b) {
			
			b = false;
			animation.pause();																		//蓄力條暫停
			arc.setRadiusX(4.3*energy.getHeight());													//依蓄力條長度調整弧大小
			arc.setRadiusY(3.6*energy.getHeight());
			arc.setLayoutX(856-arc.getRadiusX());
			arc.setLayoutY(420);
			
			role.setLayoutX(0);																		//球動畫
			role.setLayoutY(0);
			PathTransition pt = new PathTransition(Duration.millis(1500), arc, role);
			pt.play();
			
			pt.setOnFinished((d) -> {
				
				 if (arc.getLayoutX()-arc.getRadiusX() > ice.getLayoutX()-20 && (arc.getLayoutX()-arc.getRadiusX() < ice.getLayoutX()+135)) {
					b = true;
					double distance = 800 - ice.getLayoutX();
					KeyFrame start = new KeyFrame(Duration.ZERO,
							new KeyValue(ice.layoutXProperty(), ice.getLayoutX()),
							new KeyValue(initial_ice.layoutXProperty(), initial_ice.getLayoutX()),
							new KeyValue(role.layoutXProperty(), role.getLayoutX())
					);
					KeyFrame end = new KeyFrame(Duration.millis(distance),
							new KeyValue(ice.layoutXProperty(), 800),
							new KeyValue(initial_ice.layoutXProperty(), initial_ice.getLayoutX() + distance),
							new KeyValue(role.layoutXProperty(), role.getLayoutX() + distance)
					);
					Timeline t = new Timeline();
					t.getKeyFrames().addAll(start, end);
					t.play();
					newscore();
									
					t.setOnFinished((f) -> {
						initial_ice.setLayoutX(800);
						double layout = Math.random() * 620;
						ice.setLayoutX(layout);
						
						animation.play();
					});
				}
				
				else {
					String[] tokens = score.getText().split(" ");
					int s = Integer.parseInt(tokens[1]);
					returnscore.setScore(s);
					
					try {
						Fall();
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
				}
				
			});
		}
	}
				
	public void newscore() {																//+分數
		String[] tokens = score.getText().split(" ");
		int s = Integer.parseInt(tokens[1]);
		s+=10;
		score.setText("Score: " + Integer.toString(s));
		returnscore.setScore(s);
	}
	
	public void Fall() throws IOException  {												//掉落動畫
		
		KeyFrame start = new KeyFrame(Duration.ZERO,
				new KeyValue(role.layoutXProperty(), role.getLayoutX()),
				new KeyValue(role.layoutYProperty(), role.getLayoutY())
		);
		
		KeyFrame end = new KeyFrame(Duration.millis(500),
				new KeyValue(role.layoutXProperty(), role.getLayoutX()-40),
				new KeyValue(role.layoutYProperty(), role.getLayoutY()+200)
		);
		
		Timeline t = new Timeline();
		t.getKeyFrames().addAll(start, end);
		t.play();
		t.setOnFinished((f) -> {
			
			Parent game = null;
			try {
				game = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			Scene gameScene = new Scene(game); 
			gameScene.getRoot().requestFocus();
			FinalProject_108502513.currentStage.setScene(gameScene);
		});
				
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		int time1 = 1000;
		int time2 = 2000;
		
		if(MenuCotroller.getdifficulty() == false) {
			time1 = 500;
			time2 = 1000;
		}
		
		KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(energy.heightProperty(), energy.getHeight()));
		KeyFrame bottom = new KeyFrame(Duration.millis(time1), new KeyValue(energy.heightProperty(), 0));
		KeyFrame end = new KeyFrame(Duration.millis(time2), new KeyValue(energy.heightProperty(), energy.getHeight()));
		animation = new Timeline();
		animation.getKeyFrames().addAll(start, bottom, end);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

}
