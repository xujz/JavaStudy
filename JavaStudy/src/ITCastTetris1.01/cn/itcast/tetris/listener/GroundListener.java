// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GroundListener.java

package cn.itcast.tetris.listener;

import cn.itcast.tetris.entities.Ground;

public interface GroundListener
{

	public abstract void beforeDeleteFullLine(Ground ground, int i);

	public abstract void fullLineDeleted(Ground ground, int i);

	public abstract void groundIsFull(Ground ground);
}
