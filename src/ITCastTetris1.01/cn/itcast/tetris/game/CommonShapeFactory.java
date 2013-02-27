// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonShapeFactory.java

package cn.itcast.tetris.game;

import cn.itcast.tetris.entities.Shape;
import cn.itcast.tetris.entities.ShapeFactory;
import cn.itcast.tetris.listener.ShapeListener;
import cn.itcast.tetris.util.Global;
import java.util.List;
import java.util.Random;

public class CommonShapeFactory extends ShapeFactory
{

	public CommonShapeFactory()
	{
	}

	public Shape getShape(ShapeListener shapeListener)
	{
		int type = random.nextInt(shapes.length);
		int status = random.nextInt(shapes[type].length);
		Shape shape = new Shape(shapes[type], status);
		shape.setColor(isColorfulShape() ? getColorByType(type) : getDefaultShapeColor());
		shape.addShapeListener(shapeListener);
		return shape;
	}

	private java.awt.Color getColorByType(int type)
	{
		if (type < 0 || type >= Global.COMMON_COLORS.size())
			return getDefaultShapeColor();
		else
			return (java.awt.Color)Global.COMMON_COLORS.get(type);
	}
}
