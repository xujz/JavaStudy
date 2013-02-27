// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UnitType.java

package cn.itcast.tetris.entities;

import java.awt.Color;

public class UnitType
	implements Cloneable
{

	private static final int BLANK_VALUE = 0;
	private static final int STUBBORN_OBSTACLE_VALUE = 1;
	private static final int OBSTACLE_VALUE = 2;
	public static final UnitType BLANK;
	public static final UnitType STUBBORN_OBSTACLE = new UnitType(1, new Color(0x808000));
	public static final UnitType OBSTACLE;
	private int value;
	private Color color;

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	private UnitType(int value)
	{
		this.value = value;
	}

	private UnitType(int value, Color color)
	{
		this.value = value;
		this.color = color;
	}

	public UnitType clone()
	{
		return new UnitType(value, color);
	}

	public void cloneProperties(UnitType ut)
	{
		color = ut.color;
		value = ut.value;
	}

	public int hashCode()
	{
		int prime = 31;
		int result = 1;
		result = 31 * result + value;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitType other = (UnitType)obj;
		return value == other.value;
	}

	public volatile Object clone()
		throws CloneNotSupportedException
	{
		return clone();
	}

	static 
	{
		BLANK = new UnitType(0, Color.WHITE);
		OBSTACLE = new UnitType(2, Color.DARK_GRAY);
	}
}
