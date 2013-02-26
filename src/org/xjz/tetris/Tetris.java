package org.xjz.tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tetris extends JFrame implements Runnable, KeyListener {
	private short isPlaying = 0/* �Ƿ���Ϸ,���� */, xOffSet = 3/* ��ǰ����ĺ����� */,
			yOffSet = 0/* ��ǰ����������� */, blockType = (short) Math.round(Math
					.random() * 6)/* �漴��ʼ�������� */, blockRotation = 0/* ��ʼ������ת�Ƕ� */,
			blockColor = (short) Math.round(Math.random() * 5)/* �漴��ʼ������ɫ */;
	private short matrix[][] = new short[21][10];/* ��������21*10�ľ��� */
	private short block[][][][] = {
			{
					{ 	{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 1, 0, 0 } },/* l */
					{ 	{ 0, 0, 0, 0 }, 
						{ 1, 1, 1, 1 }, 
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } } },/*-*/
			{
					{ 	{ 0, 0, 0, 0 }, 
						{ 1, 1, 0, 0 }, 
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 } },/* z */
					{ 	{ 0, 0, 0, 0 }, 
						{ 0, 0, 1, 0 }, 
						{ 0, 1, 1, 0 },
						{ 0, 1, 0, 0 } } },/* z| */
			{
					{ 	{ 0, 0, 0, 0 }, 
						{ 0, 1, 1, 0 }, 
						{ 1, 1, 0, 0 },
						{ 0, 0, 0, 0 } },/* xz */
					{ 	{ 0, 1, 0, 0 }, 
						{ 0, 1, 1, 0 }, 
						{ 0, 0, 1, 0 },
						{ 0, 0, 0, 0 } } },/* xz| */
			{ { 		{ 0, 0, 0, 0 }, 
						{ 0, 1, 1, 0 }, 
						{ 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0 } } },
			/** [] */
			{
					{ 	{ 0, 1, 1, 0 }, 
						{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 0, 0, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 0, 0, 1, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 }, 
						{ 1, 1, 0, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 1, 0, 0, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } } },/* f */
			{
					{ 	{ 1, 1, 0, 0 }, 
						{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 0, 1, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 1, 0, 0 }, 
						{ 0, 1, 0, 0 }, 
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 0, 0, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 1, 0, 0, 0 },
						{ 0, 0, 0, 0 } } },/* xf */
			{
					{ 	{ 0, 1, 0, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } },
					{	{ 0, 1, 0, 0 }, 
						{ 0, 1, 1, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 } },
					{ 	{ 0, 0, 0, 0 }, 
						{ 1, 1, 1, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 } },
					{	{ 0, 1, 0, 0 }, 
						{ 1, 1, 0, 0 }, 
						{ 0, 1, 0, 0 },
						{ 0, 0, 0, 0 } } } };/* t *//*
														 * �������з����4ά����,�ֱ���:�������͡�������ת
														 * ������x���ꡢ����y����
														 */
	private Image[] images = {
			new ImageIcon("D:/Java/eclipse/workspace/tetris/src/img/Red.gif")
					.getImage(),
			new ImageIcon("D:/Java/eclipse/workspace/tetris/src/img/Blue.gif")
					.getImage(),
			new ImageIcon(("D:/Java/eclipse/workspace/tetris/src/img/Pink.gif"))
					.getImage(),
			new ImageIcon(
					("D:/Java/eclipse/workspace/tetris/src/img/BBlue.gif"))
					.getImage(),
			new ImageIcon(
					("D:/Java/eclipse/workspace/tetris/src/img/Orange.gif"))
					.getImage(),
			new ImageIcon(
					("D:/Java/eclipse/workspace/tetris/src/img/Green.gif"))
					.getImage(),
			new ImageIcon("D:/Java/eclipse/workspace/tetris/src/img/Red.gif")
					.getImage() };/* ������ɫ����ͼƬ */

	public Tetris() {
		setSize(160, 335);/* ���ڴ�С */
		setVisible(true);/* ���� */
		createBufferStrategy(2);/* �Ե�ǰ���ڴ���˫���� */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* ���ùرհ�ť�¼� */
		addKeyListener(this);/* ��Ӱ�����Ӧ */
	}
	
	
	public Tetris(int[][][] i) {
		System.out.println(i.length);
	}
	

	public void paint(Graphics g) {
		Graphics tg = this.getBufferStrategy().getDrawGraphics();/* ��ȡ��̨���廭�� */
		tg.fillRect(5, 30, 150, 340);/* Ϳ�ڱ��� */
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 10; j++) {
				if (matrix[i][j] != 0)
					tg.drawImage(images[matrix[i][j]], j * 15 + 5, i * 15 + 15,
							null);/* ���ݺ�̨�����ľ�������Ѿ��̶��ĸ����� */
				if (i < 4 && j < 4
						&& block[blockType][blockRotation][i][j] != 0)
					tg.drawImage(images[blockColor + 1],
							((j + xOffSet) * 15) + 5,
							((i + yOffSet) * 15) + 15, null);/* ���Ƶ�ǰ�ܿ��Ƶķ��� */
			}
		this.getBufferStrategy().show();/* �л���̨������ǰ̨��ʾ */
	}

	public static void main(String[] args) {
		new Thread(new Tetris()).start();// ��ʼ�����沢����������Ϸ�߳�
	}

	
	public void run() {
		while (isPlaying == 0)
			/* ����Ϸ���ڽ��� �������� */
			try {
				if (check(0, 0, 0, 1))/* ���һ������1 ������ ��ʾ����һ�� ���������������ע�� */
					yOffSet += 1;/* ��������� y����+1 */
				else {/* �粻������ ˵�������� */
					if (yOffSet == 0) {/* �����ǰ�ǵ�һ������ ��Ϸ���� */
						isPlaying = 1;
						continue;/* ����ѭ�� */
					}
					freezeAndNew();/* ����ǰ���Ƶķ���ϲ������������� ����ʼ���µķ��� */
				}
				repaint();/* �ػ滭�� */
				Thread.sleep(600);/* �߳�sleep �����޸���Ϊ�Ѷ� */
			} catch (InterruptedException e) {/* ���� */
			}
	}

	private boolean check(int left, int right, int up, int down) {/*
																 * �жϷ����Ƿ���������ƶ�������
																 * ����ת
																 * ���ϲ���һ����������
																 * �ĸ������ֱ����Ҫ�ж�����
																 * �����ơ���ת������
																 * �����Ӧ�����ж���
																 * ����1 û�ж�������0
																 * ���ж�ʱֱ�Ӽ������ֵ����
																 * �������Ǵ���ľ���ֵ��1����0
																 */
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (((xOffSet + j - left + right < 0 || xOffSet + j - left
						+ right >= 10) && block[blockType][((blockRotation + up) >= block[blockType].length ? 0
						: (blockRotation + up))][i][j] != 0)
						|| ((yOffSet + i + down >= 21) && block[blockType][((blockRotation + up) >= block[blockType].length ? 0
								: (blockRotation + up))][i][j] != 0)
						|| (block[blockType][((blockRotation + up) >= block[blockType].length ? 0
								: (blockRotation + up))][i][j] != 0 && matrix[yOffSet
								+ i + down][xOffSet + j - left + right] != 0))
					return false;
		return true;
	}

	private synchronized void freezeAndNew() {/* ���鵽�� �̶����������� ����ʼ���·��� */
		boolean[] clear = new boolean[] { false, false, false, false };/*
																		 * �Ƿ�������е�����
																		 * ÿ������4
																		 * *4
																		 * ֻ���жϷ������ڵ�4�м���
																		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				if (block[blockType][blockRotation][i][j] != 0)/*
																 * ѭ���������1��ֵ�������ľ�����
																 * ����4*4�е�0
																 * û�з���ĵط�������
																 */
					matrix[i + yOffSet][j + xOffSet] = (short) (blockColor + 1);/*
																				 * ���ݵ�ǰ������ɫ���Ƶ�����
																				 * ���޸�Ϊ��ɫ
																				 */
			clear[i] = i + yOffSet >= matrix.length ? false
					: (matrix[i + yOffSet][0] != 0
							&& matrix[i + yOffSet][1] != 0
							&& matrix[i + yOffSet][2] != 0
							&& matrix[i + yOffSet][3] != 0
							&& matrix[i + yOffSet][4] != 0
							&& matrix[i + yOffSet][5] != 0
							&& matrix[i + yOffSet][6] != 0
							&& matrix[i + yOffSet][7] != 0
							&& matrix[i + yOffSet][8] != 0 && matrix[i
							+ yOffSet][9] != 0);/*
												 * һ���ж�һ��0~9�������Ƿ���ֵ ��ֵ
												 * ���Ƶ���������Ϊtrue
												 */
		}
		for (int i = 0; i < clear.length; i++)
			if (clear[i])/* ������� */
				for (int j = yOffSet + i; j > 0; j--)
					System.arraycopy(matrix[j - 1], 0, matrix[j], 0, 10);/*
																		 * ѭ���ӵ�ǰ�����ΰ�����һ�и�������
																		 * ����Ч��
																		 */
		yOffSet = blockRotation = 0;/* ���н��� ���漸���ǳ�ʼ���·��� xy�������� ��ת ��ɫ */
		xOffSet = 3;
		blockType = (short) Math.round(Math.random() * 6);
		blockRotation = (short) Math
				.round((Math.random() * (block[blockType].length - 1)));
		blockColor = (short) Math.round(Math.random() * 5);
	}

	public void keyPressed(KeyEvent e) {// 38-�� 40-�� 37-�� 39-��
		if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && check(1, 0, 0, 0)
				&& isPlaying == 0) {// left/*���� �����жϷ����Ƿ����ƶ��ķ��� ��һ��������1 ������0*/
			xOffSet--;/* ������ x��1 */
		} else if ((e.getKeyCode() == 68 || e.getKeyCode() == 39)
				&& check(0, 1, 0, 0) && isPlaying == 0) {// right/*��������
															// ���Ƶڶ���������1 ����0*/
			xOffSet++;/* ������ x��1 */
		} else if ((e.getKeyCode() == 87 || e.getKeyCode() == 38)
				&& check(0, 0, 1, 0) && isPlaying == 0) {// up
			blockRotation = (short) ((blockRotation + 1) >= block[blockType].length ? 0
					: (blockRotation + 1));/*
											 * ����ת ���ݵ�ǰ�������ͽ�����ת ��������һ���Ƕ� �ص�0
											 * ������ת
											 */
		} else if ((e.getKeyCode() == 83 || e.getKeyCode() == 40)
				&& check(0, 0, 0, 1) && isPlaying == 0) {// down
			yOffSet += 1;/* ������ y��1 */
		} else if ((e.getKeyCode() == 83 || e.getKeyCode() == 40)
				&& isPlaying == 0)
			freezeAndNew();/* �������� �̶� ������ �·��� */
		repaint();/* �ƶ��¼������ػ� */
	}

	public void keyReleased(KeyEvent arg0) {/* ���� */
	}

	public void keyTyped(KeyEvent arg0) {/* ���� */
	}
}