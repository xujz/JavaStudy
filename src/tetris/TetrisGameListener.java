package tetris;

import java.awt.event.KeyEvent;
/**
 * 游戏驱动类
 * @author Administrator
 *
 */
public class TetrisGameListener extends Thread implements GameListener {
	private GameController controller;
	/**
	 * 时间更新
	 */
	public void tick() {
		controller.down();
	}

	public void run(){
		while(true){
			tick();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void startListen(GameController controller){
		this.controller = controller;
		this.start();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			controller.left();
		}
		else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
			controller.right();
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			controller.down();
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			controller.trans();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
