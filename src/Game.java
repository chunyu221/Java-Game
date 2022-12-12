import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalProject_108502513 extends Application{
	public static Stage currentStage;
	public static Scene menuScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		currentStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		menuScene = new Scene(root);
		
		currentStage.setTitle("GAME");
		currentStage.setScene(menuScene);
		currentStage.setResizable(false);
		currentStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
