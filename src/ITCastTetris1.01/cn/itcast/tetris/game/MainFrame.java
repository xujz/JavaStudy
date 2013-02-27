// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MainFrame.java

package cn.itcast.tetris.game;

import cn.itcast.tetris.controller.Controller;
import cn.itcast.tetris.entities.Ground;
import cn.itcast.tetris.entities.ShapeFactory;
import cn.itcast.tetris.listener.GameListener;
import cn.itcast.tetris.util.Global;
import cn.itcast.tetris.view.GamePanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package cn.itcast.tetris.game:
//			GameOptionPanel, CommonShapeFactory, MyGroundListener

public class MainFrame extends JFrame
	implements GameListener
{

	private final GameOptionPanel op = new GameOptionPanel();
	private final GamePanel gp = new GamePanel();
	private final Ground ground = new Ground();
	private final ShapeFactory shapeFactory = new CommonShapeFactory();

	public static void main(String args[])
	{
		try
		{
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public MainFrame()
	{
		setTitle("传智播客版俄罗斯方块");
		setDefaultCloseOperation(3);
		setLayout(null);
		setResizable(false);
		JLabel infoLabel = new JLabel();
		MyGroundListener mgl = new MyGroundListener();
		ground.addGroundListener(mgl);
		final Controller controller = new Controller(shapeFactory, ground, gp, infoLabel);
		op.getNewGameButton().setEnabled(true);
		op.getStopGameButton().setEnabled(false);
		addFocusListener(new FocusAdapter() {

			final MainFrame this$0;
			private final Controller val$controller;

			public void focusGained(FocusEvent focusevent)
			{
			}

			public void focusLost(FocusEvent arg0)
			{
				controller.pauseGame();
				if (op.getPauseButton().isEnabled())
					op.getPauseButton().setText("继续游戏");
			}

			
			{
				this$0 = MainFrame.this;
				controller = controller1;
				super();
			}
		});
		gp.addFocusListener(new FocusAdapter() {

			final MainFrame this$0;
			private final Controller val$controller;

			public void focusGained(FocusEvent focusevent)
			{
			}

			public void focusLost(FocusEvent arg0)
			{
				controller.pauseGame();
				if (op.getPauseButton().isEnabled())
					op.getPauseButton().setText("继续游戏");
			}

			
			{
				this$0 = MainFrame.this;
				controller = controller1;
				super();
			}
		});
		op.getNewGameButton().addActionListener(new ActionListener() {

			final MainFrame this$0;
			private final Controller val$controller;

			public void actionPerformed(ActionEvent e)
			{
				if (controller.isPlaying())
				{
					return;
				} else
				{
					int lineNum = op.getLineNum();
					int obstacleNum = op.getObstacleNum();
					controller.newGame();
					ground.generateSomeStochasticObstacle(obstacleNum, lineNum);
					return;
				}
			}

			
			{
				this$0 = MainFrame.this;
				controller = controller1;
				super();
			}
		});
		op.getStopGameButton().addActionListener(new ActionListener() {

			final MainFrame this$0;
			private final Controller val$controller;

			public void actionPerformed(ActionEvent e)
			{
				controller.stopGame();
			}

			
			{
				this$0 = MainFrame.this;
				controller = controller1;
				super();
			}
		});
		op.getPauseButton().setEnabled(false);
		op.getPauseButton().addActionListener(new ActionListener() {

			final MainFrame this$0;
			private final Controller val$controller;

			public void actionPerformed(ActionEvent e)
			{
				if (controller.isPausingGame())
					controller.continueGame();
				else
					controller.pauseGame();
				if (controller.isPausingGame())
					op.getPauseButton().setText("继续游戏");
				else
					op.getPauseButton().setText("暂停游戏");
			}

			
			{
				this$0 = MainFrame.this;
				controller = controller1;
				super();
			}
		});
		op.getCheckBox_drawGridding().addChangeListener(new ChangeListener() {

			final MainFrame this$0;

			public void stateChanged(ChangeEvent arg0)
			{
				op.getButton_griddingColor().setVisible(op.getCheckBox_drawGridding().isSelected());
				refreshOption();
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getCheckBox_colorfulShape().addChangeListener(new ChangeListener() {

			final MainFrame this$0;

			public void stateChanged(ChangeEvent arg0)
			{
				op.getButton_shapeColor().setVisible(op.getCheckBox_colorfulShape().isSelected());
				refreshOption();
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getCheckBox_colorfulObstacle().addChangeListener(new ChangeListener() {

			final MainFrame this$0;

			public void stateChanged(ChangeEvent arg0)
			{
				op.getButton_obstacleColor().setVisible(op.getCheckBox_colorfulObstacle().isSelected());
				refreshOption();
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButton_shapeColor().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent arg0)
			{
				Color shapeColor = JColorChooser.showDialog(MainFrame.this, "请选择图形的颜色", new Color(0xff4500));
				if (shapeColor != null)
					shapeFactory.setDefaultShapeColor(shapeColor);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButton_griddingColor().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent arg0)
			{
				Color griddingColor = JColorChooser.showDialog(MainFrame.this, "请选择网格的颜色", Color.LIGHT_GRAY);
				if (griddingColor != null)
					ground.setGriddingColor(griddingColor);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButton_obstacleColor().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent arg0)
			{
				Color obstacleColor = JColorChooser.showDialog(MainFrame.this, "请选择障碍物的颜色", Color.DARK_GRAY);
				if (obstacleColor != null)
					ground.setObstacleColor(obstacleColor);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButton_fullLineColor().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent arg0)
			{
				Color fullLineColor = JColorChooser.showDialog(MainFrame.this, "请选择满行的效果的颜色", Color.DARK_GRAY);
				if (fullLineColor != null)
					ground.setFullLineColor(fullLineColor);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButtonBackgroundColor().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent arg0)
			{
				Color backgroundColor = JColorChooser.showDialog(MainFrame.this, "请选择背景的颜色", new Color(0xcfcfcf));
				if (backgroundColor != null)
					gp.setBackgroundColor(backgroundColor);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		op.getButton_default().addActionListener(new ActionListener() {

			final MainFrame this$0;

			public void actionPerformed(ActionEvent e)
			{
				gp.setBackgroundColor(GamePanel.DEFAULT_BACKGROUND_COLOR);
				op.getCheckBox_drawGridding().setSelected(false);
				ground.setGriddingColor(Ground.DEFAULT_GRIDDING_COLOR);
				op.getCheckBox_colorfulShape().setSelected(true);
				shapeFactory.setDefaultShapeColor(ShapeFactory.DEFAULT_SHAPE_COLOR);
				op.getCheckBox_colorfulObstacle().setSelected(true);
				ground.setObstacleColor(Ground.DEFAULT_OBSTACLE_COLOR);
				op.getTextField_obstacleNum().setText("30");
				op.getTextField_lineNum().setText("0");
				op.getTextField_stayTime().setText("300");
				ground.setFullLineColor(Ground.DEFAULT_FULL_LINE_COLOR);
			}

			
			{
				this$0 = MainFrame.this;
				super();
			}
		});
		JPanel subPanel = new JPanel();
		subPanel.setLayout(null);
		subPanel.setBorder(new EtchedBorder(1));
		subPanel.setSize(gp.getSize().width + 3, infoLabel.getSize().height + gp.getSize().height + 2);
		infoLabel.setBounds(5, 0, infoLabel.getSize().width - 5, infoLabel.getSize().height);
		gp.setBounds(1, infoLabel.getSize().height, gp.getSize().width, gp.getSize().height);
		subPanel.add(infoLabel);
		subPanel.add(gp);
		int left = 10;
		int left2 = 5;
		op.setBounds(left, 21, op.getSize().width, op.getSize().height);
		subPanel.setBounds(left + left2 + op.getSize().width, 1, subPanel.getSize().width, subPanel.getSize().height);
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EtchedBorder(1));
		infoPanel.setLayout(null);
		infoPanel.setBounds(10, 25 + op.getSize().height, op.getSize().width, subPanel.getSize().height - op.getSize().height - 25);
		JLabel infoTitleLable = new JLabel();
		infoTitleLable.setFont(new Font("宋体", 0, 12));
		infoTitleLable.setText(Global.TITLE_LABEL_TEXT);
		infoTitleLable.setBounds(10, 5, infoPanel.getSize().width - 10, 20);
		JTextArea InfoTextArea = new JTextArea();
		InfoTextArea.setFont(new Font("宋体", 0, 12));
		InfoTextArea.setText(Global.INFO_LABEL_TEXT);
		InfoTextArea.setFocusable(false);
		InfoTextArea.setBackground(getBackground());
		InfoTextArea.setBounds(10, 25, infoPanel.getSize().width - 20, infoPanel.getSize().height - 50);
		infoPanel.add(infoTitleLable);
		infoPanel.add(InfoTextArea);
		op.getCheckBox_colorfulObstacle().setFocusable(false);
		op.getCheckBox_colorfulShape().setFocusable(false);
		op.getCheckBox_drawGridding().setFocusable(false);
		setSize(op.getSize().width + gp.getSize().width + left + left2 + 15, gp.getSize().height <= op.getSize().height + 20 ? op.getSize().height + 60 : gp.getSize().height + 60);
		setLocation(getToolkit().getScreenSize().width / 2 - getWidth() / 2, getToolkit().getScreenSize().height / 2 - getHeight() / 2);
		gp.addKeyListener(controller);
		op.addKeyListener(controller);
		addKeyListener(controller);
		controller.addGameListener(this);
		subPanel.addKeyListener(controller);
		getContentPane().add(op);
		getContentPane().add(infoPanel);
		getContentPane().add(subPanel);
	}

	public void gameOver()
	{
		op.getTextField_lineNum().setFocusable(true);
		op.getTextField_stayTime().setFocusable(true);
		op.getTextField_obstacleNum().setFocusable(true);
		op.getPauseButton().setEnabled(false);
		op.getStopGameButton().setEnabled(false);
		op.getNewGameButton().setEnabled(true);
		op.getPauseButton().setText("暂停/继续");
	}

	public void gameStart()
	{
		op.getTextField_lineNum().setFocusable(false);
		op.getTextField_stayTime().setFocusable(false);
		op.getTextField_obstacleNum().setFocusable(false);
		op.getPauseButton().setEnabled(true);
		op.getNewGameButton().setEnabled(false);
		op.getStopGameButton().setEnabled(true);
	}

	public void gameContinue()
	{
		op.getPauseButton().setText("暂停游戏");
	}

	public void gamePause()
	{
		op.getPauseButton().setText("继续游戏");
	}

	private void refreshOption()
	{
		int stayTime = op.getStayTime();
		ground.setDrawGridding(op.isDrawGridding());
		shapeFactory.setColorfulShape(!op.isColorfulShape());
		ground.setColorfulSupport(!op.isColorfulObstacle());
		Global.STAY_TIME = stayTime;
	}





}
