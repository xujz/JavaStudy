package tetris;

public class MainTest {

	public static void main(String[] args){
		GameListener c = new TetrisGameListener();
		GameController gc = new GameController();
		gc.addListener(c);
		gc.gameStart();
	}
}
