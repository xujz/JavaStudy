// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Shape.java

package cn.itcast.tetris.entities;

import cn.itcast.tetris.listener.ShapeListener;
import cn.itcast.tetris.util.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.util.TooManyListenersException;

public class Shape
{
	private class ShapeDriver
		implements Runnable
	{

		final Shape this$0;

		public void run()
		{
			if (listener == null)
				throw new RuntimeException("ÇëÏÈ×¢²á ShapeListener");
			while (life && listener.isShapeMoveDownable(Shape.this)) 
			{
				if (!swift && !pause)
				{
					moveDown();
					listener.shapeMovedDown(Shape.this);
				}
				try
				{
					Thread.sleep(speed);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			life = false;
		}

		private ShapeDriver()
		{
			this$0 = Shape.this;
			super();
		}

		ShapeDriver(ShapeDriver shapedriver)
		{
			this();
		}
	}

	private class ShapeSwiftDriver
		implements Runnable
	{

		final Shape this$0;

		public void run()
		{
			while (swift && life) 
			{
				if (listener == null)
					throw new RuntimeException("ÇëÏÈ×¢²á ShapeListener");
				if (listener.isShapeMoveDownable(Shape.this))
				{
					if (!pause)
					{
						moveDown();
						listener.shapeMovedDown(Shape.this);
					}
					try
					{
						Thread.sleep(swiftSpeed);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				} else
				{
					life = false;
				}
			}
		}

		private ShapeSwiftDriver()
		{
			this$0 = Shape.this;
			super();
		}

		ShapeSwiftDriver(ShapeSwiftDriver shapeswiftdriver)
		{
			this();
		}
	}


	public static final int ROTATE = 5;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	private ShapeListener listener;
	private int body[][];
	private int status;
	private int height;
	private int left;
	private int top;
	private int speed;
	private boolean life;
	private boolean pause;
	private boolean swift;
	private int swiftSpeed;
	private Thread shapeThread;
	private Thread swiftThread;
	private Color color;

	public Shape(int body[][], int status)
	{
		swiftSpeed = Global.SWIFT_SPEED;
		color = Color.BLUE;
		this.body = body;
		this.status = status;
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
				if (isMember(x, y, false))
					height = y + 1;

		}

		init();
	}

	public void init()
	{
		life = true;
		pause = false;
		swift = false;
		left = Global.WIDTH / 2 - 2;
		top = 0 - height;
		speed = Global.CURRENT_SPEED;
	}

	public void rotate()
	{
		status = (status + 1) % body.length;
	}

	public void moveUp()
	{
		top--;
	}

	public void moveDown()
	{
		top++;
	}

	public void moveLeft()
	{
		left--;
	}

	public void moveRight()
	{
		left++;
	}

	public void drawMe(Graphics g)
	{
		if (!life)
			return;
		g.setColor(color);
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
				if (getFlagByPoint(status, x, y))
					drawUnit(g, (left + x) * Global.CELL_WIDTH, (top + y) * Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT);

		}

	}

	public void drawUnit(Graphics g, int x, int y, int width, int height)
	{
		g.fill3DRect(x, y, width, height, true);
	}

	private boolean getFlagByPoint(int status, int x, int y)
	{
		return body[status][y * 4 + x] != 0;
	}

	public boolean isMember(int x, int y, boolean isRotate)
	{
		return getFlagByPoint(isRotate ? (status + 1) % body.length : status, x, y);
	}

	public void speedUp()
	{
		if (speed > Global.SPEED_STEP)
			speed -= Global.SPEED_STEP;
		Global.CURRENT_SPEED = speed;
	}

	public void speedDown()
	{
		speed += Global.SPEED_STEP;
		Global.CURRENT_SPEED = speed;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public boolean isPause()
	{
		return pause;
	}

	public void setPause(boolean pause)
	{
		this.pause = pause;
	}

	public void changePause()
	{
		pause = !pause;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getLeft()
	{
		return left;
	}

	public void setLeft(int left)
	{
		this.left = left;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public void setBody(int body[][])
	{
		this.body = body;
	}

	public void addShapeListener(ShapeListener l)
	{
		if (l == null || listener == l)
			return;
		if (listener != null)
		{
			throw new RuntimeException(new TooManyListenersException());
		} else
		{
			listener = l;
			shapeThread = new Thread(new ShapeDriver(null));
			shapeThread.start();
			return;
		}
	}

	public boolean isLife()
	{
		return life;
	}

	public void die()
	{
		life = false;
	}

	public boolean isSwift()
	{
		return swift;
	}

	public void setSwift(boolean swift)
	{
		if (this.swift == swift)
			return;
		this.swift = swift;
		if (this.swift)
		{
			swiftThread = new Thread(new ShapeSwiftDriver(null));
			swiftThread.start();
		}
	}

	public int getSwiftSpeed()
	{
		return swiftSpeed;
	}

	public void setSwiftSpeed(int swiftSpeed)
	{
		this.swiftSpeed = swiftSpeed;
	}

	public int getHeight()
	{
		return height;
	}







}
