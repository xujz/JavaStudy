// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Controller.java

package cn.itcast.tetris.controller;

import cn.itcast.tetris.entities.*;
import cn.itcast.tetris.listener.*;
import cn.itcast.tetris.util.Global;
import cn.itcast.tetris.view.GamePanel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.*;
import javax.swing.JLabel;

public class Controller extends KeyAdapter
	implements ShapeListener, GroundListener
{

	private Set listeners;
	private ShapeFactory shapeFactory;
	private Shape shape;
	private Ground ground;
	private GamePanel gamePanel;
	private JLabel gameInfoLabel;
	private boolean playing;

	public Controller(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel)
	{
		listeners = new HashSet();
		this.shapeFactory = shapeFactory;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	public Controller(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel, JLabel gameInfoLabel)
	{
		this(shapeFactory, ground, gamePanel);
		setGameInfoLabel(gameInfoLabel);
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() != 89 && !playing)
			return;
		switch (e.getKeyCode())
		{
		default:
			break;

		case 37: // '%'
			if (isPausingGame())
				continueGame();
			shape.setSwift(false);
			if (shape.isLife() && !shape.isPause() && ground.isMoveable(shape, 3))
				shape.moveLeft();
			break;

		case 39: // '\''
			if (isPausingGame())
				continueGame();
			shape.setSwift(false);
			if (shape.isLife() && !shape.isPause() && ground.isMoveable(shape, 4))
				shape.moveRight();
			break;

		case 38: // '&'
			if (shape.isLife() && !shape.isPause() && ground.isMoveable(shape, 5))
			{
				shape.setSwift(false);
				shape.rotate();
				break;
			}
			if (!shape.isLife() || !shape.isPause())
				break;
			if (ground.isMoveable(shape, 1))
			{
				shape.moveUp();
			} else
			{
				shape.die();
				shape = shapeFactory.getShape(this);
			}
			break;

		case 40: // '('
			if (isPausingGame())
				continueGame();
			if (shape.isLife() && !shape.isPause() && isShapeMoveDownable(shape))
				shape.moveDown();
			break;

		case 33: // '!'
			shape.speedUp();
			break;

		case 34: // '"'
			shape.speedDown();
			break;

		case 192: 
			if (playing && !ground.isFull())
			{
				shape.die();
				shape = shapeFactory.getShape(this);
			}
			break;

		case 10: // '\n'
			if (isPausingGame())
				continueGame();
			else
				pauseGame();
			break;

		case 89: // 'Y'
			if (!playing || ground.isFull())
				newGame();
			break;

		case 32: // ' '
			if (playing && !isPausingGame())
				shape.setSwift(true);
			break;
		}
		gamePanel.redisplay(ground, shape);
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	public synchronized boolean isShapeMoveDownable(Shape s)
	{
		if (shape == null)
			return true;
		if (!playing || shape != s)
			return false;
		if (ground.isMoveable(shape, 2))
			return true;
		shape.die();
		ground.accept(shape);
		if (playing && !ground.isFull())
			shape = shapeFactory.getShape(this);
		gamePanel.redisplay(ground, shape);
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
		return false;
	}

	public void shapeMovedDown(Shape s)
	{
		if (playing && ground != null && shape != null)
			gamePanel.redisplay(ground, shape);
	}

	public void newGame()
	{
		playing = true;
		ground.init();
		ground.addGroundListener(this);
		Global.CURRENT_SPEED = Global.DEFAULT_SPEED;
		shape = shapeFactory.getShape(this);
		if (playing)
			gamePanel.redisplay(ground, shape);
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
		GameListener l;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.gameStart())
			l = (GameListener)iterator.next();

	}

	public void stopGame()
	{
		if (shape == null)
			return;
		playing = false;
		GameListener l;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.gameOver())
			l = (GameListener)iterator.next();

	}

	public void pauseGame()
	{
		if (shape == null)
			return;
		shape.setPause(true);
		GameListener l;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.gamePause())
			l = (GameListener)iterator.next();

	}

	public void continueGame()
	{
		shape.setPause(false);
		GameListener l;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.gameContinue())
			l = (GameListener)iterator.next();

	}

	public boolean isPausingGame()
	{
		return shape.isPause();
	}

	public String getNewInfo()
	{
		if (!playing || ground.isFull())
			return " ";
		else
			return "提示: " + " 速度 " + shape.getSpeed() + "毫秒/格";
	}

	public ShapeFactory getShapeFactory()
	{
		return shapeFactory;
	}

	public void setShapeFactory(ShapeFactory shapeFactory)
	{
		this.shapeFactory = shapeFactory;
	}

	public Ground getGround()
	{
		return ground;
	}

	public void setGround(Ground ground)
	{
		this.ground = ground;
	}

	public GamePanel getGamePanel()
	{
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	public void beforeDeleteFullLine(Ground ground, int lineNum)
	{
		ground.changeFullLineColor(lineNum);
		gamePanel.redisplay(ground, shape);
		try
		{
			Thread.sleep(Global.STAY_TIME);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void fullLineDeleted(Ground ground, int deletedLineCount)
	{
		System.out.println((new StringBuilder("消了 ")).append(deletedLineCount).append(" 行").toString());
	}

	public boolean isPlaying()
	{
		return playing && !ground.isFull();
	}

	public JLabel getGameInfoLabel()
	{
		return gameInfoLabel;
	}

	public void setGameInfoLabel(JLabel gameInfoLabel)
	{
		this.gameInfoLabel = gameInfoLabel;
		this.gameInfoLabel.setSize(Global.WIDTH * Global.CELL_WIDTH, 20);
		this.gameInfoLabel.setFont(new Font("宋体", 0, 12));
		gameInfoLabel.setText(getNewInfo());
	}

	public void groundIsFull(Ground ground)
	{
		if (playing)
		{
			playing = false;
			GameListener l;
			for (Iterator iterator = listeners.iterator(); iterator.hasNext(); l.gameOver())
				l = (GameListener)iterator.next();

		}
	}

	public void addGameListener(GameListener l)
	{
		if (l != null)
			listeners.add(l);
	}

	public void removeGameListener(GameListener l)
	{
		if (l != null)
			listeners.remove(l);
	}
}
