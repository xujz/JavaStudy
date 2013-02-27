// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ShapeListener.java

package cn.itcast.tetris.listener;

import cn.itcast.tetris.entities.Shape;

public interface ShapeListener
{

	public abstract boolean isShapeMoveDownable(Shape shape);

	public abstract void shapeMovedDown(Shape shape);
}
