// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GameListener.java

package cn.itcast.tetris.listener;


public interface GameListener
{

	public abstract void gameStart();

	public abstract void gameOver();

	public abstract void gamePause();

	public abstract void gameContinue();
}
