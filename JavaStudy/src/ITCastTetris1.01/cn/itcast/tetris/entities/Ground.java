// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Ground.java

package cn.itcast.tetris.entities;

import cn.itcast.tetris.listener.GroundListener;
import cn.itcast.tetris.util.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

// Referenced classes of package cn.itcast.tetris.entities:
//			UnitType, Shape

public class Ground
{

	private Set listeners;
	private UnitType obstacles[][];
	private Color stubbornObstacleColor;
	public static final Color DEFAULT_GRIDDING_COLOR;
	private Color griddingColor;
	public static final Color DEFAULT_OBSTACLE_COLOR;
	private Color obstacleColor;
	public static final Color DEFAULT_FULL_LINE_COLOR;
	private Color fullLineColor;
	private boolean drawGridding;
	private boolean colorfulSupport;
	private boolean full;
	private Random random;

	public Ground()
	{
		listeners = new HashSet();
		obstacles = new UnitType[Global.WIDTH][Global.HEIGHT];
		stubbornObstacleColor = UnitType.STUBBORN_OBSTACLE.getColor();
		griddingColor = DEFAULT_GRIDDING_COLOR;
		obstacleColor = DEFAULT_OBSTACLE_COLOR;
		fullLineColor = DEFAULT_FULL_LINE_COLOR;
		random = new Random();
		init();
	}

	public void init()
	{
		clear();
		full = false;
	}

	public void clear()
	{
		for (int x = 0; x < Global.WIDTH; x++)
		{
			for (int y = 0; y < Global.HEIGHT; y++)
				obstacles[x][y] = UnitType.BLANK.clone();

		}

	}

	public void genernateAStubbornStochasticObstacle()
	{
		Random random = new Random();
		if (Global.HEIGHT < 5)
		{
			return;
		} else
		{
			int y = (random.nextInt(5) + Global.HEIGHT) - 5;
			int x = random.nextInt(Global.WIDTH);
			addStubbornObstacle(x, y);
			return;
		}
	}

	public void generateSomeStochasticObstacle(int amount, int lineNum)
	{
		if (lineNum < 1)
			return;
		if (lineNum > Global.HEIGHT)
			lineNum = Global.HEIGHT;
		for (int i = 0; i < amount; i++)
		{
			int x = random.nextInt(Global.WIDTH);
			int y = (random.nextInt(lineNum) + Global.HEIGHT) - lineNum;
			obstacles[x][y] = UnitType.OBSTACLE.clone();
			obstacles[x][y].setColor(Global.getRandomColor());
		}

	}

	public void accept(Shape shape)
	{
		int left = shape.getLeft();
		int top = shape.getTop();
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
				if (left + x < Global.WIDTH && top + y < Global.HEIGHT && shape.isMember(x, y, false))
					if (top + y < 0)
					{
						full = true;
						GroundListener l;
						for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.groundIsFull(this))
							l = (GroundListener)iterator.next();

					} else
					{
						obstacles[left + x][top + y].cloneProperties(UnitType.OBSTACLE);
						obstacles[left + x][top + y].setColor(colorfulSupport ? shape.getColor() : obstacleColor);
					}

		}

		deleteFullLine();
	}

	public void deleteFullLine()
	{
		int deletedLineCount = 0;
		for (int y = Global.HEIGHT - 1; y >= 0; y--)
		{
			boolean isFull = true;
			for (int x = 0; x < Global.WIDTH; x++)
				if (obstacles[x][y].equals(UnitType.BLANK))
					isFull = false;

			if (isFull)
			{
				deleteLine(y++);
				deletedLineCount++;
			}
		}

		if (deletedLineCount > 0)
		{
			GroundListener l;
			for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.fullLineDeleted(this, deletedLineCount))
				l = (GroundListener)iterator.next();

		}
	}

	public void deleteLine(int lineNum)
	{
		GroundListener l;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.beforeDeleteFullLine(this, lineNum))
			l = (GroundListener)iterator.next();

		for (int y = lineNum; y > 0; y--)
		{
			for (int x = 0; x < Global.WIDTH; x++)
				if (!obstacles[x][y].equals(UnitType.STUBBORN_OBSTACLE))
					if (obstacles[x][y - 1].equals(UnitType.STUBBORN_OBSTACLE))
					{
						obstacles[x][y].cloneProperties(UnitType.BLANK);
						obstacles[x][y].setColor(griddingColor);
					} else
					{
						obstacles[x][y].cloneProperties(obstacles[x][y - 1]);
					}

		}

		for (int x = 0; x < Global.WIDTH; x++)
			if (!obstacles[x][0].equals(UnitType.STUBBORN_OBSTACLE))
				obstacles[x][0] = UnitType.BLANK.clone();

	}

	public boolean isFull()
	{
		return full;
	}

	public synchronized boolean isMoveable(Shape shape, int action)
	{
		int left = shape.getLeft();
		int top = shape.getTop();
		switch (action)
		{
		case 1: // '\001'
			top--;
			break;

		case 2: // '\002'
			top++;
			break;

		case 3: // '\003'
			left--;
			break;

		case 4: // '\004'
			left++;
			break;
		}
		if (top < 0 - shape.getHeight())
			return false;
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if ((left + x < 0 || left + x >= Global.WIDTH || top + y >= Global.HEIGHT) && shape.isMember(x, y, action == 5))
					return false;
				if (top + y >= 0 && shape.isMember(x, y, action == 5) && !obstacles[left + x][top + y].equals(UnitType.BLANK))
					return false;
			}

		}

		return true;
	}

	public void changeFullLineColor(int lineNum)
	{
		for (int x = 0; x < Global.WIDTH; x++)
			obstacles[x][lineNum].setColor(fullLineColor);

	}

	public void addObstacle(int x, int y)
	{
		if (x < 0 || x >= Global.WIDTH || y < 0 || y >= Global.HEIGHT)
		{
			throw new RuntimeException((new StringBuilder("这个位置超出了显示区域 (x:")).append(x).append("  y:").append(y).append(")").toString());
		} else
		{
			obstacles[x][y].cloneProperties(UnitType.OBSTACLE);
			return;
		}
	}

	public void addStubbornObstacle(int x, int y)
	{
		if (x < 0 || x >= Global.WIDTH || y < 0 || y >= Global.HEIGHT)
		{
			throw new RuntimeException((new StringBuilder("这个位置超出了显示区域 (x:")).append(x).append("  y:").append(y).append(")").toString());
		} else
		{
			obstacles[x][y].cloneProperties(UnitType.STUBBORN_OBSTACLE);
			return;
		}
	}

	public void drawMe(Graphics g)
	{
		for (int x = 0; x < Global.WIDTH; x++)
		{
			for (int y = 0; y < Global.HEIGHT; y++)
				if (drawGridding && obstacles[x][y].equals(UnitType.BLANK))
				{
					g.setColor(griddingColor);
					drawGridding(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else
				if (obstacles[x][y].equals(UnitType.STUBBORN_OBSTACLE))
				{
					g.setColor(stubbornObstacleColor);
					drawStubbornObstacle(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else
				if (obstacles[x][y].equals(UnitType.OBSTACLE))
				{
					g.setColor(obstacles[x][y].getColor());
					drawObstacle(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT);
				}

		}

	}

	public void drawGridding(Graphics g, int x, int y, int width, int height)
	{
		g.drawRect(x, y, width, height);
	}

	public void drawStubbornObstacle(Graphics g, int x, int y, int width, int height)
	{
		g.fill3DRect(x, y, width, height, true);
	}

	public void drawObstacle(Graphics g, int x, int y, int width, int height)
	{
		g.fill3DRect(x, y, width, height, true);
	}

	public Color getStubbornObstacleColor()
	{
		return stubbornObstacleColor;
	}

	public void setStubbornObstacleColor(Color stubbornObstacleColor)
	{
		this.stubbornObstacleColor = stubbornObstacleColor;
	}

	public Color getGriddingColor()
	{
		return griddingColor;
	}

	public void setGriddingColor(Color griddingColor)
	{
		this.griddingColor = griddingColor;
	}

	public Color getObstacleColor()
	{
		return obstacleColor;
	}

	public void setObstacleColor(Color obstacleColor)
	{
		this.obstacleColor = obstacleColor;
	}

	public Color getFullLineColor()
	{
		return fullLineColor;
	}

	public void setFullLineColor(Color fullLineColor)
	{
		this.fullLineColor = fullLineColor;
	}

	public boolean isDrawGridding()
	{
		return drawGridding;
	}

	public void setDrawGridding(boolean drawGridding)
	{
		this.drawGridding = drawGridding;
	}

	public boolean isColorfulSupport()
	{
		return colorfulSupport;
	}

	public void setColorfulSupport(boolean colorfulSupport)
	{
		this.colorfulSupport = colorfulSupport;
	}

	public void addGroundListener(GroundListener l)
	{
		if (l != null)
			listeners.add(l);
	}

	public void removeGroundListener(GroundListener l)
	{
		if (l != null)
			listeners.remove(l);
	}

	public boolean isStubbornObstacle(int x, int y)
	{
		if (x >= 0 && x < Global.WIDTH && y >= 0 && y < Global.HEIGHT)
			return obstacles[x][y].equals(UnitType.STUBBORN_OBSTACLE);
		else
			throw new RuntimeException((new StringBuilder("这个坐标超出了显示区域: (x:")).append(x).append(" y:").append(y).append(")").toString());
	}

	public boolean isObstacle(int x, int y)
	{
		if (x >= 0 && x < Global.WIDTH && y >= 0 && y < Global.HEIGHT)
			return obstacles[x][y].equals(UnitType.OBSTACLE);
		else
			throw new RuntimeException((new StringBuilder("这个坐标超出了显示区域: (x:")).append(x).append(" y:").append(y).append(")").toString());
	}

	public boolean isBlank(int x, int y)
	{
		if (x >= 0 && x < Global.WIDTH && y >= 0 && y < Global.HEIGHT)
			return obstacles[x][y].equals(UnitType.BLANK);
		else
			throw new RuntimeException((new StringBuilder("这个坐标超出了显示区域: (x:")).append(x).append(" y:").append(y).append(")").toString());
	}

	static 
	{
		DEFAULT_GRIDDING_COLOR = Color.LIGHT_GRAY;
		DEFAULT_OBSTACLE_COLOR = UnitType.OBSTACLE.getColor();
		DEFAULT_FULL_LINE_COLOR = Color.DARK_GRAY;
	}
}
