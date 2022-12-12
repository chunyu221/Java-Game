import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class MenuCotroller implements Initializable{
	
	@FXML
	public Text high;
	
	@FXML
	public void onEasyPressed() throws IOException {
		difficulty = true;
		Parent game = FXMLLoader.load(getClass().getResource("FinalProject.fxml"));
		Scene gameScene = new Scene(game);
		gameScene.getRoot().requestFocus();
		FinalProject_108502513.currentStage.setScene(gameScene);
	}
	
	@FXML
	public void onHardPressed() throws IOException {
		difficulty = false;
		Parent game = FXMLLoader.load(getClass().getResource("FinalProject.fxml"));
		Scene gameScene = new Scene(game);
		gameScene.getRoot().requestFocus();
		FinalProject_108502513.currentStage.setScene(gameScene);
	}
	
	@FXML
	public void onExitPressed() {
		FinalProject_108502513.currentStage.close();
	}
	
	private static boolean difficulty = true;
	public static boolean getdifficulty() {
		return difficulty;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		high.setText("High Score: " + String.valueOf(returnscore.getHighScore()));
	}
}
