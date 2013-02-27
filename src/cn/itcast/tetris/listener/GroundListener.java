package cn.itcast.tetris.listener;

import cn.itcast.tetris.entities.Ground;

/**
 * ���������
 * 
 * @version 1.0, 01/01/08
 * 
 * @author ������
 * 
 */

public interface GroundListener {
	/**
	 * ��Ҫ�����¼�
	 * 
	 * 
	 * @param ground
	 * 
	 * @param lineNum
	 *            ��Ҫ�������е��к�
	 * @param lineNum
	 */
	void beforeDeleteFullLine(Ground ground, int lineNum);

	/**
	 * ���������¼�
	 * 
	 * @param ground
	 * @param deletedLineCount
	 *            ��������������
	 */
	void fullLineDeleted(Ground ground, int deletedLineCount);

	void groundIsFull(Ground ground);
}
