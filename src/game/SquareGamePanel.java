package game;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SquareGamePanel extends JPanel implements Data, Serializable 

{
	
	public SquareGamePanel(SquareGameFrame frame)
	{
		for(int i = LEFT; i <= RIGHT; i++)
		{
			for(int j = UP; j <= DOWN; j++)
			{
				flag[i][j] = 0;
			}
		}
		addKeyListener(new KeyHandler());
		setFocusable(true);
		timerAction = new TimerAction();
		timer = new Timer(1000, timerAction);
		
		sr1 = makeNewSquareRoot();
		sr2 = makeNewSquareRoot();
		score = 0;
		
		this.frame = frame;
		
		square = new Square();
	}
	
	public void drawGameFrame(Graphics2D g2)
	{
		Rectangle2D.Double leftFrame=new Rectangle2D.Double(BIGLEFT, BIGUP, 200, 400);
		Rectangle2D.Double rightFrame=new Rectangle2D.Double(LITTLELEFT, LITTLEUP, 80, 80);
		g2.draw(leftFrame);
		g2.draw(rightFrame);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		drawGameFrame(g2);
		
		if(gameState == INIT_STATE)
		{
			return;
		}
		
		sr1.draw(g2);
		sr2.drawNext(g2);
		
		for(int i = LEFT; i <= RIGHT; i++)
		{
			for(int j = UP; j <= DOWN; j++)
			{
				if(flag[i][j] == 1)
				{
					square.x = i;
					square.y = j;
					square.color = color[i][j];
					square.draw(g2);
				}
			}
		}
		
		g.drawString("Score: " + score, LITTLELEFT, 200);
	}
	
	public void writeSelfToFile(File file)
	{

		try
		{
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(flag);
			objectStream.writeObject(color);
			objectStream.writeObject(sr1);
			objectStream.writeObject(sr2);
			objectStream.writeObject(new Integer(score));
			objectStream.close();
			fileStream.close();
			
			JOptionPane.showConfirmDialog(frame,  "保存游戏进度成功", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
		}
		catch(Exception e)
		{
			JOptionPane.showConfirmDialog(frame, e.toString() + "\n保存游戏进度失败", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	public void readSelfFromFile(File file)
	{
		
		try
		{
			int[][] f;
			SquareRoot s1, s2;
			Integer integer;
			Color[][] c;
			FileInputStream fileStream = new FileInputStream(file);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			f = (int[][])objectStream.readObject();
			c = (Color[][])objectStream.readObject();
			s1 = (SquareRoot)objectStream.readObject();
			s2 = (SquareRoot)objectStream.readObject();
			integer = (Integer)objectStream.readObject();
			objectStream.close();
			fileStream.close();
			
			if(f != null && c != null && s1 != null && s2 != null && integer != null)
			{
				flag = f;
				color = c;
				sr1 = s1;
				sr2 = s2;
				score = integer.intValue();
				gameState = RUN_STATE;
				frame.saveMI.setEnabled(true);
				if(!timer.isRunning())
				{
					timer.start();
				}
				
				repaint();
				
				JOptionPane.showConfirmDialog(frame, "装载游戏进度成功", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showConfirmDialog(frame, e.toString() + "\n装载游戏进度失败", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	public void setGameState(int state)
	{
		gameState = state;
	}
	
	private void writeScore()
	{
		if(score == 0)
		{
			return;
		}
		
		
		ReadAndWriteFile.setPath("file.dat");
		MyArray array = ReadAndWriteFile.read();
		if(array == null || array.isEmpty() || !array.isFull() || (array.getTair().score < score && array.isFull()))
		{
			System.out.println("score " + score);
			String playerName = JOptionPane.showInputDialog("Please input your name");
			if(playerName == null || playerName.length() == 0)
			{
				playerName = "无名英雄";
			}
			
			array.add(new Score(playerName, score));
			ReadAndWriteFile.write(array);
		}
	}
	
	private void judgeGameOver()
	{
		if(flag[4][UP] == 1 || flag[5][UP] == 1 || flag[6][UP] == 1)
		{
			gameState = OVER_STATE;
			writeScore();
			int result =JOptionPane.showConfirmDialog(frame, 
					"Game over! Continue?", "俄罗斯方块", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION)
			{
				for(int i = LEFT; i <= RIGHT; i++)
				{
					for(int j = UP; j <= DOWN; j++)
					{
						flag[i][j] = 0;
					}
				}
				
				gameState = RUN_STATE;
				score = 0;
				timer.start();
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	private class KeyHandler implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			if(gameState != RUN_STATE)
			{
				return;
			}
			
			int keyCode = event.getKeyCode();
			switch(keyCode)
			{
				case KeyEvent.VK_LEFT:
						sr1.moveLeft(flag);
					break;
					
				case KeyEvent.VK_RIGHT:
						sr1.moveRight(flag);
					break;
					
				case KeyEvent.VK_UP:
						sr1.changeState(flag);
					break;
					
				case KeyEvent.VK_DOWN:
						sr1.moveDown(flag);
					break;
				case KeyEvent.VK_SPACE:
						while(sr1.isLife)
						{
							sr1.moveDown(flag);
						}
				default:
					break;
			}
			repaint();
		}
		
		public void keyReleased(KeyEvent event)
		{
		}
		
		public void keyTyped(KeyEvent event)
		{
		}
	}
	
	private class TimerAction implements ActionListener, Serializable
	{
		public void actionPerformed(ActionEvent event)
		{
			if(gameState != RUN_STATE)
			{
				return;
			}
			
			int num = 0;
			sr1.moveDown(flag);
			
			if(!sr1.isLife)
			{
				for(int i = 0; i < 4; i++)
				{
					flag[sr1.square[i].x][sr1.square[i].y] = 1;
					color[sr1.square[i].x][sr1.square[i].y] = sr1.color;
				}
				
				judgeGameOver();
				
				for(int i = UP; i <= DOWN; i++)
				{
					int count = 0;
					for(int j = LEFT; j <= RIGHT; j++)
					{
						count += flag[j][i];
					}
					
					if(count == 10)
					{
						num++;
						
						for(int m = i; m > UP; m--)
						{
							for(int n = LEFT; n <= RIGHT; n++)
							{
								flag[n][m] = flag[n][m - 1];
								color[n][m] = color[n][m - 1];
							}
						}
						
						for(int s = LEFT; s <= RIGHT; s++)
						{
							flag[s][UP] = 0;
						}
					}
				}
				
				sr1 = sr2;
				sr2 = makeNewSquareRoot();
			}
			if(num == 1)
			{
				score += num * 10;
			}
			else if(num > 1)
			{
				score += (num + 1) * 10;
			}
			
			repaint();
		}
	}
	
	private SquareRoot makeNewSquareRoot()
	{
		SquareRoot sr = null;
		int index = (int)(Math.random() * 7);
		switch(index)
		{
		case 0:
			sr = new SquareRoot1();
			break;
			
		case 1:
			sr = new SquareRoot2();
			break;
			
		case 2:
			sr = new SquareRoot3();
			break;
			
		case 3:
			sr = new SquareRoot4();
			break;
			
		case 4:
			sr = new SquareRoot5();
			break;
			
		case 5:
			sr = new SquareRoot6();
			break;
			
		case 6:
			sr = new SquareRoot7();
			break;
	
		default:
			break;
		}
		
		return sr;
	}
	
	
	public int flag[][] = new int[10][20];
	public Color color[][] = new Color[10][20];
	public SquareRoot sr1;
	public SquareRoot sr2;
	public TimerAction timerAction;
	public int score;
	public SquareGameFrame frame;
	public Timer timer;
	public Square square;
	public int gameState;
	
}
