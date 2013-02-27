package cn.itcast.tetris.listener;

import cn.itcast.tetris.entities.Shape;

/**
 * ͼ�μ�����
 * 
 * @version 1.0, 01/01/08
 * 
 * @author ������
 * 
 */

public interface ShapeListener {
	/**
	 * ͼ��ѯ���Ƿ��������
	 * 
	 * 
	 * @param shape
	 * @return
	 */
	boolean isShapeMoveDownable(Shape shape);

	/**
	 * ͼ�������¼�
	 * 
	 * @param shape
	 */
	void shapeMovedDown(Shape shape);
}
