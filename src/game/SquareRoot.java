package game;

import java.awt.*;
import java.io.Serializable;

public abstract class SquareRoot implements Data, Serializable 
{
	public SquareRoot()
	{
		int r = (int)(Math.random() * 256);
		int g = (int)(Math.random() * 256);
		int b = (int)(Math.random() * 256);
		color = new Color(r, g, b);
		square[0] = new Square(0, 0, color);
		square[1] = new Square(0, 0, color);
		square[2] = new Square(0, 0, color);
		square[3] = new Square(0, 0, color);
		isLife = true;
	}
	
	public void draw(Graphics2D g2)
	{
		for(int i = 0; i < square.length; i++)
		{
			square[i].draw(g2);
		}
	}
	
	public void moveLeft(int flag[][])
	{
		if(!isLife)
		{
			return;
		}
		
		for(int i = 0; i < square.length; i++)
		{
			tempX[i] = square[i].x - 1;
			tempY[i] = square[i].y;
		}
		
		if(tempX[0] >= LEFT && flag[tempX[0]][tempY[0]] == 0 &&
		   tempX[1] >= LEFT && flag[tempX[1]][tempY[1]] == 0 &&
		   tempX[2] >= LEFT && flag[tempX[2]][tempY[2]] == 0 &&
		   tempX[3] >= LEFT && flag[tempX[3]][tempY[3]] == 0)
		{
			for(int i = 0; i < square.length; i++)
			{
				square[i].x = tempX[i];
			}
		}
	}
	
	public void moveRight(int flag[][])
	{
		if(!isLife)
		{
			return;
		}
		
		for(int i = 0; i < square.length; i++)
		{
			tempX[i] = square[i].x + 1;
			tempY[i] = square[i].y;
		}
		
		if(tempX[0] <= RIGHT && flag[tempX[0]][tempY[0]] == 0 &&
		   tempX[1] <= RIGHT && flag[tempX[1]][tempY[1]] == 0 &&
		   tempX[2] <= RIGHT && flag[tempX[2]][tempY[2]] == 0 &&
		   tempX[3] <= RIGHT && flag[tempX[3]][tempY[3]] == 0)
		{
			for(int i = 0; i < square.length; i++)
			{
				square[i].x = tempX[i];
			}
		}
	}
	
	public void moveDown(int flag[][])
	{
		for(int i = 0; i < square.length; i++)
		{
			tempX[i] = square[i].x;
			tempY[i] = square[i].y + 1;
		}
		
		if(tempY[0] <= DOWN && flag[tempX[0]][tempY[0]] == 0 &&
		   tempY[1] <= DOWN && flag[tempX[1]][tempY[1]] == 0 &&
		   tempY[2] <= DOWN && flag[tempX[2]][tempY[2]] == 0 &&
		   tempY[3] <= DOWN && flag[tempX[3]][tempY[3]] == 0)
		{
			for(int i = 0; i < square.length; i++)
			{
				square[i].y = tempY[i];
			}
		}
		else
		{
			isLife = false;
		}
	}
	
	protected void isAllowChangeState(int flag[][], int count)
	{
		if(!isLife)
		{
			return;
		}
		
		if(tempX[0] >= LEFT && tempX[0] <= RIGHT && tempY[0] >= UP && tempY[0] <= DOWN && flag[tempX[0]][tempY[0]] == 0 &&
		   tempX[1] >= LEFT && tempX[1] <= RIGHT && tempY[1] >= UP && tempY[1] <= DOWN && flag[tempX[1]][tempY[1]] == 0 &&
		   tempX[2] >= LEFT && tempX[2] <= RIGHT && tempY[2] >= UP && tempY[2] <= DOWN && flag[tempX[2]][tempY[2]] == 0 &&
		   tempX[3] >= LEFT && tempX[3] <= RIGHT && tempY[3] >= UP && tempY[3] <= DOWN && flag[tempX[3]][tempY[3]] == 0)
		{
			for(int i = 0; i < square.length; i++)
			{
				square[i].x = tempX[i];
				square[i].y = tempY[i];
			}
			
			state = (state + 1) % count;
		}
	}
	
	public void drawNext(Graphics2D g2)
	{
		for(int i = 0; i < square.length; i++)
		{
			tempX[i] = square[i].x;
			tempY[i] = square[i].y;
		}
		
		while(true)
		{
			tempX[0]++;
			tempX[1]++;
			tempX[2]++;
			tempX[3]++;
			
			if(tempX[0] >= LITTLEX && tempX[1] >= LITTLEX && tempX[2] >= LITTLEX && tempX[3] >= LITTLEX)
			{
				break;
			}
		}
		
		for(int i = 0; i < square.length; i++)
		{
			new Square(tempX[i], tempY[i], color).draw(g2);
		}
	}
	
	public abstract void changeState(int flag[][]);
	
	public Square square[]={null, null, null, null};
	public int tempX[]={0, 0, 0, 0};
	public int tempY[]={0, 0, 0, 0};
	public boolean isLife;
	public Color color;
	public int state;
}
