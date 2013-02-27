// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GamePanel.java

package cn.itcast.tetris.view;

import cn.itcast.tetris.entities.Ground;
import cn.itcast.tetris.entities.Shape;
import cn.itcast.tetris.util.Global;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class GamePanel extends JPanel
{

	private Image oimg;
	private Graphics og;
	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0xcfcfcf);
	private Color backgroundColor;

	public GamePanel()
	{
		backgroundColor = DEFAULT_BACKGROUND_COLOR;
		setSize(Global.WIDTH * Global.CELL_WIDTH, Global.HEIGHT * Global.CELL_HEIGHT);
		setBorder(new EtchedBorder(1));
		setFocusable(true);
	}

	public synchronized void redisplay(Ground ground, Shape shape)
	{
		if (og == null)
		{
			oimg = createImage(getSize().width, getSize().height);
			if (oimg != null)
				og = oimg.getGraphics();
		}
		if (og != null)
		{
			og.setColor(backgroundColor);
			og.fillRect(0, 0, Global.WIDTH * Global.CELL_WIDTH, Global.HEIGHT * Global.CELL_HEIGHT);
			ground.drawMe(og);
			if (shape != null)
				shape.drawMe(og);
			paint(getGraphics());
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(oimg, 0, 0, this);
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

}
