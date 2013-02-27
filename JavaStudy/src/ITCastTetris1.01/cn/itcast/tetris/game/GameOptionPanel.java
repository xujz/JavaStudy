// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GameOptionPanel.java

package cn.itcast.tetris.game;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class GameOptionPanel extends JPanel
{

	private ImageIcon czbkIcon;
	private JFrame frame;
	private final JTextField textField_stayTime = new JTextField();
	private final JTextField textField_obstacleNum = new JTextField();
	private JTextField textField_lineNum;
	private final JButton newGameButton = new JButton();
	private final JButton stopGameButton = new JButton();
	private final JButton pauseButton = new JButton();
	private final JCheckBox checkBox_drawGridding = new JCheckBox();
	private final JCheckBox checkBox_colorfulShape = new JCheckBox();
	private final JCheckBox checkBox_colorfulObstacle = new JCheckBox();
	private final JButton button_obstacleColor = new JButton();
	private final JButton button_griddingColor = new JButton();
	private final JButton button_shapeColor = new JButton();
	private final JButton button_fullLineColor = new JButton();
	private final JButton buttonBackgroundColor = new JButton();
	private final JButton button_default = new JButton();

	public GameOptionPanel()
	{
		czbkIcon = new ImageIcon(cn/itcast/tetris/game/GameOptionPanel.getResource("/czbk.png"));
		textField_lineNum = new JTextField();
		setSize(280, 334);
		setLayout(null);
		setBorder(new EtchedBorder(1));
		setFocusable(false);
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 69, 255, 30);
		add(separator);
		button_griddingColor.setBounds(135, 5, 96, 23);
		separator.add(button_griddingColor);
		button_griddingColor.setFont(new Font("����", 0, 12));
		button_griddingColor.setFocusable(false);
		button_griddingColor.setText("������ɫ");
		button_griddingColor.setVisible(false);
		checkBox_drawGridding.setBounds(5, 5, 105, 23);
		separator.add(checkBox_drawGridding);
		checkBox_drawGridding.setText("��ʾ����");
		checkBox_drawGridding.setFont(new Font("����", 0, 12));
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 105, 255, 39);
		add(separator_1);
		button_shapeColor.setBounds(135, 10, 96, 23);
		separator_1.add(button_shapeColor);
		button_shapeColor.setFont(new Font("����", 0, 12));
		button_shapeColor.setFocusable(false);
		button_shapeColor.setText("������ɫ");
		checkBox_colorfulShape.setText("�رղ�ɫͼ��");
		checkBox_colorfulShape.setBounds(5, 10, 105, 23);
		separator_1.add(checkBox_colorfulShape);
		checkBox_colorfulShape.setSelected(true);
		checkBox_colorfulShape.setFont(new Font("����", 0, 12));
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 145, 255, 39);
		add(separator_2);
		button_obstacleColor.setBounds(135, 10, 96, 23);
		separator_2.add(button_obstacleColor);
		button_obstacleColor.setFont(new Font("����", 0, 12));
		button_obstacleColor.setFocusable(false);
		button_obstacleColor.setText("������ɫ");
		checkBox_colorfulObstacle.setText("�رղ�ɫ�ϰ���");
		checkBox_colorfulObstacle.setBounds(5, 10, 122, 23);
		separator_2.add(checkBox_colorfulObstacle);
		checkBox_colorfulObstacle.setSelected(true);
		checkBox_colorfulObstacle.setFont(new Font("����", 0, 12));
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 185, 255, 33);
		add(separator_3);
		JLabel label_7 = new JLabel();
		label_7.setBounds(10, 10, 48, 15);
		separator_3.add(label_7);
		label_7.setFont(new Font("����", 0, 12));
		label_7.setFocusable(false);
		label_7.setText("�������");
		textField_obstacleNum.setFont(new Font("����", 0, 12));
		textField_obstacleNum.setBounds(65, 10, 32, 15);
		separator_3.add(textField_obstacleNum);
		textField_obstacleNum.setText("30");
		JLabel label_2 = new JLabel();
		label_2.setBounds(100, 10, 18, 15);
		separator_3.add(label_2);
		label_2.setText("��");
		label_2.setFont(new Font("����", 0, 12));
		textField_lineNum.setBounds(120, 10, 24, 15);
		textField_lineNum.setFont(new Font("����", 0, 12));
		separator_3.add(textField_lineNum);
		textField_lineNum.setText("0");
		JLabel label_8 = new JLabel();
		label_8.setBounds(150, 10, 66, 15);
		separator_3.add(label_8);
		label_8.setFont(new Font("����", 0, 12));
		label_8.setFocusable(false);
		label_8.setText("���ϰ���");
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 220, 255, 33);
		add(separator_4);
		textField_stayTime.setFont(new Font("����", 0, 12));
		textField_stayTime.setBounds(110, 14, 42, 15);
		separator_4.add(textField_stayTime);
		textField_stayTime.setText("300");
		JLabel label_1 = new JLabel();
		label_1.setBounds(160, 14, 24, 15);
		separator_4.add(label_1);
		label_1.setFont(new Font("����", 0, 12));
		label_1.setFocusable(false);
		label_1.setText("����");
		button_fullLineColor.setBounds(195, 10, 60, 23);
		separator_4.add(button_fullLineColor);
		button_fullLineColor.setFont(new Font("����", 0, 12));
		button_fullLineColor.setFocusable(false);
		button_fullLineColor.setText("��ɫ");
		JLabel label = new JLabel();
		label.setBounds(10, 14, 120, 15);
		separator_4.add(label);
		label.setFont(new Font("����", 0, 12));
		label.setFocusable(false);
		label.setText("���е�Ч��ʱ�䣺");
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(15, 260, 250, 69);
		add(separator_5);
		stopGameButton.setText("ֹͣ��Ϸ");
		stopGameButton.setBounds(10, 10, 101, 23);
		separator_5.add(stopGameButton);
		stopGameButton.setFont(new Font("����", 0, 12));
		stopGameButton.setFocusable(false);
		pauseButton.setBounds(135, 10, 103, 23);
		separator_5.add(pauseButton);
		pauseButton.setText("��ͣ/����");
		pauseButton.setFont(new Font("����", 0, 12));
		pauseButton.setFocusable(false);
		newGameButton.setFont(new Font("����", 0, 12));
		newGameButton.setBounds(65, 40, 110, 23);
		separator_5.add(newGameButton);
		newGameButton.setFocusable(false);
		newGameButton.setText("��ʼ����Ϸ");
		JLabel label_logo = new JLabel(czbkIcon);
		label_logo.setBounds(140, 10, 125, 50);
		add(label_logo);
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(12, 35, 119, 33);
		add(separator_6);
		buttonBackgroundColor.setBounds(5, 5, 110, 23);
		separator_6.add(buttonBackgroundColor);
		buttonBackgroundColor.setFont(new Font("����", 0, 12));
		buttonBackgroundColor.setFocusable(false);
		buttonBackgroundColor.setText("���ñ�����ɫ");
		button_default.setFont(new Font("����", 0, 12));
		button_default.setText("�ָ�Ĭ������");
		button_default.setBounds(17, 5, 110, 23);
		button_default.setFocusable(false);
		add(button_default);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public int getObstacleNum()
	{
		return Integer.parseInt(textField_obstacleNum.getText());
		NumberFormatException e;
		e;
		return 0;
	}

	public int getStayTime()
	{
		return Integer.parseInt(textField_stayTime.getText());
		NumberFormatException e;
		e;
		return 0;
	}

	public JButton getNewGameButton()
	{
		return newGameButton;
	}

	public JButton getStopGameButton()
	{
		return stopGameButton;
	}

	public int getLineNum()
	{
		return Integer.parseInt(textField_lineNum.getText());
		NumberFormatException e;
		e;
		return 0;
	}

	public void setTextField_lineNum(JTextField textField_lineNum)
	{
		this.textField_lineNum = textField_lineNum;
	}

	public JCheckBox getCheckBox_colorfulShape()
	{
		return checkBox_colorfulShape;
	}

	public JCheckBox getCheckBox_drawGridding()
	{
		return checkBox_drawGridding;
	}

	public JTextField getTextField_stayTime()
	{
		return textField_stayTime;
	}

	public JTextField getTextField_obstacleNum()
	{
		return textField_obstacleNum;
	}

	public JTextField getTextField_lineNum()
	{
		return textField_lineNum;
	}

	public boolean isDrawGridding()
	{
		return checkBox_drawGridding.isSelected();
	}

	public boolean isColorfulShape()
	{
		return checkBox_colorfulShape.isSelected();
	}

	public boolean isColorfulObstacle()
	{
		return checkBox_colorfulObstacle.isSelected();
	}

	public ImageIcon getCzbkIcon()
	{
		return czbkIcon;
	}

	public void setCzbkIcon(ImageIcon czbkIcon)
	{
		this.czbkIcon = czbkIcon;
	}

	public JButton getPauseButton()
	{
		return pauseButton;
	}

	public JCheckBox getCheckBox_colorfulObstacle()
	{
		return checkBox_colorfulObstacle;
	}

	public JButton getButton_obstacleColor()
	{
		return button_obstacleColor;
	}

	public JButton getButton_griddingColor()
	{
		return button_griddingColor;
	}

	public JButton getButton_shapeColor()
	{
		return button_shapeColor;
	}

	public JButton getButton_fullLineColor()
	{
		return button_fullLineColor;
	}

	public JButton getButtonBackgroundColor()
	{
		return buttonBackgroundColor;
	}

	public JButton getButton_default()
	{
		return button_default;
	}
}
