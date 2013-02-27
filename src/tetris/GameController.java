package tetris;

import java.awt.Graphics;
/**
 * 游戏控制器
 * @author Administrator
 *
 */
public class GameController {


	protected GameListener listener;
	protected Scene scene;//
	
	protected Shape curShape;
	private int status = STATUS_STOP;
	public final static int STATUS_PLAYING = 1;
	public final static int STATUS_PAUSE = 2;
	public final static int STATUS_STOP = 3;
	
	private GameUI ui = new GameUI(this);
	public GameController(){
		scene = new Scene();
	}
	public void setStatus(int s){
		status = s;
	}
	public void left(){
		if(status != STATUS_PLAYING){
			return;
		}
		Shape s = getCurShape();
		if(!this.canGoNextStep(s, -1, 0)){
			return;
		}
		else{
			s.goLeft();
			drawAll();
		}

	}
	public void trans(){
		if(status != STATUS_PLAYING){
			return;
		}
		getCurShape().trans();
		drawAll();
	}
	public void right(){
		if(status != STATUS_PLAYING){
			return;
		}
		Shape s = getCurShape();
		if(!this.canGoNextStep(s, 1, 0)){
			return;
		}
		else{
			s.goRight();
			drawAll();
		}
	}
	public boolean canGoNextStep(Shape s,int tx,int ty){
		int [][] space = scene.getSpace();
		int [][] block = s.getBlock();
		int x = s.getLocX();
		int y = s.getLocY();
		x = x + tx;
		y = y + ty;
		if(y+s.getBottomStart()>=space.length||y<0){
			return false;
		}
		else if(x+s.getRightStart()>=space[0].length||x+s.getLeftStart()<0){
			return false;
		}
		else{
			for(int j=0;j<block.length;j++){
				for(int i=0;i<block.length;i++){
					if(block[j][i]==1&&space[y+j][x+i]==1){
						return false;
					}
				}
			}
		}
		return true;
	}
	public void down(){
		if(status != STATUS_PLAYING){
			return;
		}
		Shape s = getCurShape();
		if(!this.canGoNextStep(s, 0, 1)){
			scene.addShape(s, s.getLocX(), s.getLocY());
			this.curShape = null;
			drawAll();
			return;
		}
		else{
			s.goDown();
			drawAll();
		}
	}
	public Shape getCurShape(){
		if(curShape == null){
			curShape = new Shape();
			if(checkEnd()){
				gameOver();
			}
		}
		return curShape;
	}
	public void drawAll(){
		ui.clearPanel();
		Graphics g = ui.getGraphics();
		scene.drawMe(g);
		getCurShape().drawMe(g);
		ui.setScore(CommonUtil.getUserControl().getScore()+"");
	}

	public void addListener(GameListener listener){
		this.listener = listener;
		
		
	}
	public void gameStart(){
		ui.init();
		ui.addListener(listener);
		listener.startListen(this);
		
	}
	public void newGame(){
		scene.clearSapce();
		curShape = null;
	}
	public void gameOver(){
		curShape = null;
		status = STATUS_STOP;
		javax.swing.JOptionPane.showMessageDialog(null, "Game Over!");
	}
	public boolean checkEnd(){
		for(int j=0;j<curShape.getBlock().length;j++){
			for(int i = 0;i<curShape.getBlock()[0].length;i++){
				if(curShape.getBlock()[j][i]==1&&scene.getSpace()[curShape.getLocY()+j][curShape.getLocX()+i]==1){
					return true;
				}
			}
		}
		return false;
	}
}
