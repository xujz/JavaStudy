package org.xjz.tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tetris extends JFrame implements Runnable, KeyListener {
	private short isPlaying = 0/* 是否游戏,声明 */, xOffSet = 3/* 当前方块的横坐标 */,
			yOffSet = 0/* 当前方块的纵坐标 */, blockType = (short) Math.round(Math
					.random() * 6)/* 随即初始方块类型 */, blockRotation = 0/* 初始方块旋转角度 */,
			blockColor = (short) Math.round(Math.random() * 5)/* 随即初始方块颜色 */;
	private short matrix[][] = new short[21][10];/* 整个画布21*10的矩阵 */
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
														 * 保存所有方块的4维矩阵,分别是:方块类型、方块旋转
														 * 、方块x坐标、方块y坐标
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
					.getImage() };/* 各种颜色方块图片 */

	public Tetris() {
		setSize(160, 335);/* 窗口大小 */
		setVisible(true);/* 可视 */
		createBufferStrategy(2);/* 对当前窗口创建双缓冲 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* 设置关闭按钮事件 */
		addKeyListener(this);/* 添加按键响应 */
	}
	
	
	public Tetris(int[][][] i) {
		System.out.println(i.length);
	}
	

	public void paint(Graphics g) {
		Graphics tg = this.getBufferStrategy().getDrawGraphics();/* 获取后台缓冲画布 */
		tg.fillRect(5, 30, 150, 340);/* 涂黑背景 */
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 10; j++) {
				if (matrix[i][j] != 0)
					tg.drawImage(images[matrix[i][j]], j * 15 + 5, i * 15 + 15,
							null);/* 根据后台背景的矩阵绘制已经固定的各方块 */
				if (i < 4 && j < 4
						&& block[blockType][blockRotation][i][j] != 0)
					tg.drawImage(images[blockColor + 1],
							((j + xOffSet) * 15) + 5,
							((i + yOffSet) * 15) + 15, null);/* 绘制当前能控制的方块 */
			}
		this.getBufferStrategy().show();/* 切换后台画布到前台显示 */
	}

	public static void main(String[] args) {
		new Thread(new Tetris()).start();// 初始化界面并创建启动游戏线程
	}

	
	public void run() {
		while (isPlaying == 0)
			/* 当游戏正在进行 不断下落 */
			try {
				if (check(0, 0, 0, 1))/* 最后一个参数1 是下落 表示下落一格 其他参数见下面的注释 */
					yOffSet += 1;/* 如果能下落 y坐标+1 */
				else {/* 如不能下落 说明到底了 */
					if (yOffSet == 0) {/* 如果当前是第一个方块 游戏结束 */
						isPlaying = 1;
						continue;/* 不再循环 */
					}
					freezeAndNew();/* 将当前控制的方块合并到背景矩阵中 并初始化新的方块 */
				}
				repaint();/* 重绘画布 */
				Thread.sleep(600);/* 线程sleep 可以修改作为难度 */
			} catch (InterruptedException e) {/* 无用 */
			}
	}

	private boolean check(int left, int right, int up, int down) {/*
																 * 判断方块是否可以左右移动、下落
																 * 、旋转
																 * 都合并到一个方法中了
																 * 四个参数分别代表要判断左移
																 * 、右移、旋转、下落
																 * 如果对应方向有动作
																 * 则传入1 没有动作传入0
																 * 在判断时直接计算参数值即可
																 * 而不考虑传入的具体值是1还是0
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

	private synchronized void freezeAndNew() {/* 方块到底 固定到背景矩阵 并初始化新方块 */
		boolean[] clear = new boolean[] { false, false, false, false };/*
																		 * 是否可以消行的数组
																		 * 每个方块4
																		 * *4
																		 * 只需判断方块所在的4行即可
																		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				if (block[blockType][blockRotation][i][j] != 0)/*
																 * 循环将方块的1赋值到背景的矩阵中
																 * 方块4*4中的0
																 * 没有方块的地方不复制
																 */
					matrix[i + yOffSet][j + xOffSet] = (short) (blockColor + 1);/*
																				 * 根据当前方块颜色复制到背景
																				 * 可修改为灰色
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
												 * 一次判断一行0~9个方块是否有值 有值
												 * 复制到消行数组为true
												 */
		}
		for (int i = 0; i < clear.length; i++)
			if (clear[i])/* 如果消行 */
				for (int j = yOffSet + i; j > 0; j--)
					System.arraycopy(matrix[j - 1], 0, matrix[j], 0, 10);/*
																		 * 循环从当前行依次把上面一行复制下来
																		 * 下落效果
																		 */
		yOffSet = blockRotation = 0;/* 消行结束 下面几行是初始化新方块 xy方块类型 旋转 颜色 */
		xOffSet = 3;
		blockType = (short) Math.round(Math.random() * 6);
		blockRotation = (short) Math
				.round((Math.random() * (block[blockType].length - 1)));
		blockColor = (short) Math.round(Math.random() * 5);
	}

	public void keyPressed(KeyEvent e) {// 38-上 40-下 37-左 39-右
		if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && check(1, 0, 0, 0)
				&& isPlaying == 0) {// left/*左移 传入判断方块是否能移动的方法 第一个参数传1 其他传0*/
			xOffSet--;/* 能左移 x减1 */
		} else if ((e.getKeyCode() == 68 || e.getKeyCode() == 39)
				&& check(0, 1, 0, 0) && isPlaying == 0) {// right/*类似上面
															// 右移第二个参数传1 其他0*/
			xOffSet++;/* 能右移 x加1 */
		} else if ((e.getKeyCode() == 87 || e.getKeyCode() == 38)
				&& check(0, 0, 1, 0) && isPlaying == 0) {// up
			blockRotation = (short) ((blockRotation + 1) >= block[blockType].length ? 0
					: (blockRotation + 1));/*
											 * 能旋转 根据当前方块类型进行旋转 如果到最后一个角度 回到0
											 * 继续旋转
											 */
		} else if ((e.getKeyCode() == 83 || e.getKeyCode() == 40)
				&& check(0, 0, 0, 1) && isPlaying == 0) {// down
			yOffSet += 1;/* 能下落 y加1 */
		} else if ((e.getKeyCode() == 83 || e.getKeyCode() == 40)
				&& isPlaying == 0)
			freezeAndNew();/* 不能下落 固定 并消行 新方块 */
		repaint();/* 移动事件触发重绘 */
	}

	public void keyReleased(KeyEvent arg0) {/* 无用 */
	}

	public void keyTyped(KeyEvent arg0) {/* 无用 */
	}
}