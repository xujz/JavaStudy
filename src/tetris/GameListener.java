package tetris;

import java.awt.event.KeyListener;
/**
 * 游戏驱动接口
 * @author Administrator
 *
 */
public interface GameListener extends KeyListener{
	/**
	 * 时间更新
	 *
	 */
	public void tick();
	/**
	 * 游戏开始
	 * @param scene
	 */
	public void startListen(GameController scene);
}
