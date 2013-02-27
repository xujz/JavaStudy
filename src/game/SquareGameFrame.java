package game;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class SquareGameFrame extends JFrame implements Data
{
	public SquareGameFrame()
	{
		setTitle("俄罗斯方块");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu setMenu = new JMenu("Set");
		JMenu helpMenu = new JMenu("Help");
		
		setMenu.setMnemonic('s');
		setMenu.setMnemonic('H');
		
		menuBar.add(setMenu);
		menuBar.add(helpMenu);
		
		setMenu.add(startMI);
		setMenu.add(pauseMI);
		setMenu.addSeparator();
		
		setMenu.add(loadMI);
		setMenu.add(saveMI);
		setMenu.add(recordMI);
		
		setMenu.addSeparator();
		setMenu.add(speedMenu);
		setMenu.addSeparator();
		setMenu.add(exitMI);
		
		ButtonGroup group = new ButtonGroup();
		group.add(speedMI1);
		group.add(speedMI2);
		group.add(speedMI3);
		group.add(speedMI4);
		group.add(speedMI5);
		
		speedMenu.add(speedMI1);
		speedMenu.add(speedMI2);
		speedMenu.add(speedMI3);
		speedMenu.add(speedMI4);
		speedMenu.add(speedMI5);
		
		startMI.addActionListener(new StartAction());
		pauseMI.addActionListener(new PauseAction());
		loadMI.addActionListener(new LoadAction());
		saveMI.addActionListener(new SaveAction());
		recordMI.addActionListener(new RecordAction());
		exitMI.addActionListener(new ExitAction());
		speedMI1.addActionListener(new SpeedAction());
		speedMI2.addActionListener(new SpeedAction());
		speedMI3.addActionListener(new SpeedAction());
		speedMI4.addActionListener(new SpeedAction());
		speedMI5.addActionListener(new SpeedAction());
		
		helpMenu.add(aboutMI);
		aboutMI.addActionListener(new AboutAction());
		
		contentPane = getContentPane();
		panel = new SquareGamePanel(this);
		contentPane.add(panel);
		
		startMI.setEnabled(true);
		pauseMI.setEnabled(false);
		saveMI.setEnabled(false);
		
		//设置游戏状态是初始化状态
		panel.setGameState(INIT_STATE);
	}
	
	
	private class StartAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			startMI.setEnabled(false);
			pauseMI.setEnabled(true);
			saveMI.setEnabled(true);
			panel.setGameState(RUN_STATE);
			panel.timer.start();
		}
	}
	
	private class PauseAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			pauseMI.setEnabled(false);
			startMI.setEnabled(true);
			panel.setGameState(PAUSE_STATE);
			if(panel.timer.isRunning())
			{
				panel.timer.stop();
			}
			
		}
	}
	
	private class LoadAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			FileDialog dialog = new FileDialog(SquareGameFrame.this, "Open", FileDialog.LOAD);
			dialog.setVisible(true);
			String dir = dialog.getDirectory();
			String fileName = dialog.getFile();
			String filePath = dir + fileName;
			
			if(fileName != null && fileName.trim().length() != 0)
			{
				File file = new File(filePath);
				panel.readSelfFromFile(file);
				startMI.setEnabled(false);
				pauseMI.setEnabled(true);
			}
			else
			{
				JOptionPane.showConfirmDialog(SquareGameFrame.this, "文件名为空\n装载游戏进度失败", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
			}
			
		}
	}
	
	private class SaveAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(panel.gameState == INIT_STATE)
			{
				JOptionPane.showConfirmDialog(SquareGameFrame.this, "游戏没有运行\n不能保存游戏进度", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
				return;
			}
			
			FileDialog dialog = new FileDialog(SquareGameFrame.this, "Save", FileDialog.SAVE);
			dialog.setVisible(true);
			String dir = dialog.getDirectory();
			String fileName = dialog.getFile();
			String filePath = dir + fileName;
			if(fileName != null && fileName.trim().length() != 0)
			{
				File file = new File(filePath);
				panel.writeSelfToFile(file);
			}
			else
			{
				JOptionPane.showConfirmDialog(SquareGameFrame.this, "文件名为空\n保存游戏进度失败", "俄罗斯方块", JOptionPane.DEFAULT_OPTION);
			}
			
		}
	}
	
	private class RecordAction implements ActionListener
	{
		
		public void actionPerformed(ActionEvent event)
		{
			ReadAndWriteFile.setPath("file.dat");
			JScrollPane panel = ReadAndWriteFile.getRecordPanel();

			JDialog recordDialog = new JDialog(SquareGameFrame.this, "俄罗斯方块");
			recordDialog.setBounds(300, 300, 300, 219);

			Container container = recordDialog.getContentPane();
			container.add(panel);
			recordDialog.show();
		}
	}
	
	private class SpeedAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object speed = event.getSource();
			if(speed == speedMI1)
			{
				speedFlag = 1;
			}
			else if(speed == speedMI2)
			{
				speedFlag = 2;
			}
			else if(speed == speedMI3)
			{
				speedFlag = 3;
			}
			else if(speed == speedMI4)
			{
				speedFlag = 4;
			}
			else if(speed == speedMI5)
			{
				speedFlag = 5;
			}
			
			panel.timer.setDelay(1000 - 200 * (speedFlag - 1));
		}
	}
	
	private class ExitAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int result =JOptionPane.showConfirmDialog(SquareGameFrame.this, 
					"Are you sure quit？", "俄罗斯方块", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}
	
	
	private class AboutAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String string="说明:\n1.按左键向左移动\n" +
							"2.按右键向右移动\n" +
							"3.按向上键翻滚\n" +
							"4.按向下键加速下降\n" +
							"5.按空格键下降到最底部";
			JOptionPane.showMessageDialog(SquareGameFrame.this, string);
		}
	}
	
	private final int WIDTH = 340;
	private final int HEIGHT = 460;
	
	private SquareGamePanel panel;
	private Container contentPane;
	
	private JMenuItem startMI = new JMenuItem("Start");
	private JMenuItem pauseMI = new JMenuItem("Pause");
	private JMenuItem recordMI = new JMenuItem("Record");
	private JMenu speedMenu = new JMenu("Speed");
	private JMenuItem exitMI = new JMenuItem("Exit");
	private JMenuItem aboutMI = new JMenuItem("About");
	private JMenuItem loadMI = new JMenuItem("Open");
	public JMenuItem saveMI = new JMenuItem("Save");
	
    private JRadioButtonMenuItem speedMI1 = new JRadioButtonMenuItem("Speed1", true);
	private JRadioButtonMenuItem speedMI2 = new JRadioButtonMenuItem("Speed2", false);
	private JRadioButtonMenuItem speedMI3 = new JRadioButtonMenuItem("Speed3", false);
	private JRadioButtonMenuItem speedMI4 = new JRadioButtonMenuItem("Speed4", false);
	private JRadioButtonMenuItem speedMI5 = new JRadioButtonMenuItem("Speed5", false);
	
	public int speedFlag = 1;

}
