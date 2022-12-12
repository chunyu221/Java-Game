import java.io.IOException;

public class returnscore {
	
	private static int score;
	public static void setScore(int val) {
		score = val;
	}
	public static int getScore() {
		return score;
	}
	
	private static int highscore;
	
	public static void setHighScore(int val) throws IOException {
		highscore = val;		
	}
	public static int getHighScore() {
		return highscore;
	}
}