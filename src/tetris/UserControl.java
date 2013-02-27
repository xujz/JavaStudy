
package tetris;

public class UserControl {

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int level){
		if(level==1){
			score = score + 1;
		}
		else if(level==2){
			score = score+3;
		}
		else if(level == 3){
			score = score +8;
		}
		else if(level==4){
			score = score+16;
		}
		
	}
}
