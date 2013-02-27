// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ShapeFactory.java

package cn.itcast.tetris.entities;

import cn.itcast.tetris.listener.ShapeListener;
import cn.itcast.tetris.util.Global;
import java.awt.Color;
import java.util.Random;

// Referenced classes of package cn.itcast.tetris.entities:
//			Shape

public class ShapeFactory
{

	protected static int shapes[][][] = {
		{
			{
				1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 
				0, 0, 0, 0, 0, 0
			}, {
				0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 
				0, 0, 0, 0, 0, 0
			}
		}, {
			{
				1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0
			}, {
				1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 
				0, 0, 1, 0, 0, 0
			}
		}
	};
	protected Random random;
	public static final Color DEFAULT_SHAPE_COLOR = new Color(0x990066);
	private Color defaultShapeColor;
	private boolean colorfulShape;

	public ShapeFactory()
	{
		random = new Random();
		defaultShapeColor = DEFAULT_SHAPE_COLOR;
	}

	public Shape getShape(ShapeListener shapeListener)
	{
		int type = random.nextInt(shapes.length);
		Shape shape = new Shape(shapes[type], random.nextInt(shapes[type].length));
		shape.setColor(colorfulShape ? Global.getRandomColor() : defaultShapeColor);
		shape.addShapeListener(shapeListener);
		return shape;
	}

	public Color getDefaultShapeColor()
	{
		return defaultShapeColor;
	}

	public void setDefaultShapeColor(Color defaultShapeColor)
	{
		this.defaultShapeColor = defaultShapeColor;
	}

	public boolean isColorfulShape()
	{
		return colorfulShape;
	}

	public void setColorfulShape(boolean colorfulShape)
	{
		this.colorfulShape = colorfulShape;
	}

}
