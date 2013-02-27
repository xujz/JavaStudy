package game;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class Square implements Data, Serializable
{
	public Square(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public Square()
	{}
	
	public void draw(Graphics2D g2)
	{
		int clientX = BIGLEFT + x * LONG;
		int clientY = BIGUP + y * LONG;
		Rectangle2D.Double rect = new Rectangle2D.Double(clientX, clientY, LONG, LONG);
		g2.setPaint(color);
		g2.fill(rect);
		g2.setPaint(Color.BLACK);
		g2.draw(rect);
	}
	
	public int x;
	public int y;
	public Color color;
}
