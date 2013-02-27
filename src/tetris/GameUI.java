package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 界面类
 * @author Administrator
 *
 */
public class GameUI{
	public final static int WIDTH = 400;
	public final static int HEIGHT = 600;
	JFrame jFrame = new JFrame();
	JPanel panel = new JPanel();
	GameController controller;
	JButton btnStart = new JButton("开始游戏");
	JButton btnPause = new JButton("暂停");
	JButton btnNew = new JButton("新游戏");
	JTextField score;
	public GameUI(GameController controller){
		this.controller = controller;
	}
	public void init(){
		jFrame.setBounds(0, 0, 700, 700);
		jFrame.setTitle("俄罗期方块v01");
		jFrame.setDefaultCloseOperation(3);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		panel.setVisible(true);
		panel.setBackground(Color.BLACK);
		jFrame.add(panel);
		btnStart.setBounds(100, 620, 100, 30);
		jFrame.add(btnStart);
		btnPause.setBounds(250, 620, 100, 30);
		jFrame.add(btnPause);
		btnNew.setBounds(400, 720, 100, 30);
		jFrame.add(btnNew);
		jFrame.setFocusable(true);
		jFrame.requestFocus();
		ActionListener l = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("开始游戏")){
					controller.setStatus(GameController.STATUS_PLAYING);
					jFrame.requestFocus();
				}
				else if(e.getActionCommand().equals("暂停")){
					controller.setStatus(GameController.STATUS_PAUSE);
					jFrame.requestFocus();
				}
				else if(e.getActionCommand().equals("新游戏")){
					controller.setStatus(GameController.STATUS_PLAYING);
					jFrame.requestFocus();
					controller.newGame();
				}
			}
			
		};
		btnStart.addActionListener(l);
		btnPause.addActionListener(l);
		btnNew.addActionListener(l);
		JLabel lScore = new JLabel("分数：");
		score = new JTextField("0");
		lScore.setBounds(450, 220, 50, 30);
		jFrame.add(lScore);
		score.setBounds(500, 220, 150, 30);
		jFrame.add(score);
		jFrame.repaint();
	}
	public Graphics getGraphics(){
		return panel.getGraphics();
	}
	public void clearPanel(){
		panel.update(panel.getGraphics());
	}
	public void addListener(KeyListener kl){
		jFrame.addKeyListener(kl);
	}
	public void setScore(String sc){
		score.setText(sc);
	}
}
