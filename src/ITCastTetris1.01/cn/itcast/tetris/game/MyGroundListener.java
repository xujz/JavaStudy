// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyGroundListener.java

package cn.itcast.tetris.game;

import cn.itcast.tetris.entities.Ground;
import cn.itcast.tetris.listener.GroundAdapter;
import cn.itcast.tetris.util.Global;

public class MyGroundListener extends GroundAdapter
{

	int deletedLineCount;

	public MyGroundListener()
	{
		deletedLineCount = 0;
	}

	public void fullLineDeleted(Ground ground, int deletedLineCount)
	{
		this.deletedLineCount += deletedLineCount;
		if ((deletedLineCount %= 10) == 9 || deletedLineCount > 2)
		{
			for (int y = 0; y < Global.HEIGHT; y++)
			{
				for (int x = 0; x < Global.WIDTH; x++)
					if (ground.isStubbornObstacle(x, y))
						ground.addObstacle(x, y);

			}

		}
	}
}
