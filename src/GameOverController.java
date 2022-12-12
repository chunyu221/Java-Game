import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GameOverController implements Initializable {
	
	@FXML
	public Text score;
	
	@FXML
	public ImageView _new;
	
	@FXML
	public void onRestartPressed() throws IOException {
		Parent game = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene gameScene = new Scene(game);
		gameScene.getRoot().requestFocus();
		FinalProject_108502513.currentStage.setScene(gameScene);
	}
	
	@FXML
	public void onExitPressed() {
		FinalProject_108502513.currentStage.close();
	}
	
	public void newscore() throws IOException {
		score.setText(String.valueOf(returnscore.getScore()));
		if(returnscore.getScore() > returnscore.getHighScore()) {
			returnscore.setHighScore(returnscore.getScore());
			_new.setVisible(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		try {
			newscore();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
