package cn.itcast.tetris.listener;

/**
 * ��Ϸ������
 * 
 * @version 1.0, 01/01/08
 * 
 * @author ������
 * 
 */

public interface GameListener {

	/**
	 * ��Ϸ��ʼ��
	 */
	void gameStart();

	/**
	 * ��Ϸ������
	 */
	void gameOver();

	/**
	 * ��Ϸ��ͣ��
	 */
	void gamePause();

	/**
	 * ��Ϸ������
	 */
	void gameContinue();
}
