package org.xjz.myTetris;

import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JFrame;

public class MyTetris extends JFrame {
	
	
	public MyTetris(){
		System.out.println("数组的长度是："+shapes.length);
		System.out.println("输出数组内容：");
		int p = 1;
		for(int i =0;i<shapes.length;i++){
			for(int j = 0;j<shapes[i].length;j++){
					System.out.println("第"+p+++"行："+Arrays.toString(shapes[i][j])+"i="+i+" j="+j );
			}
		}
		this.setTitle("Xujz's Tetris");
		this.createBufferStrategy(2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 400);
		this.setVisible(true);
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyTetris();
	}
	
	
	
	
	
	public void print2() {
		// TODO Auto-generated method stub
//		super.print(g);
		
		Graphics gp = this.getBufferStrategy().getDrawGraphics();
		gp.fillRect(0, 0, 300, 400);
		this.getBufferStrategy().show();
	}




	
	private int[][] metrix = new int[21][10];	//包含方块的矩阵
	
	
	private int[][][] shapes = new int[][][]{	//所有方块的图形
			{{	1,1,1,1,
				0,0,0,0,
				0,0,0,0,
				0,0,0,0	},
			{	1,0,0,0,
				1,0,0,0,
				1,0,0,0,
				1,0,0,0		}}, //----
			{{	1,1,0,0,
				1,1,0,0,
				0,0,0,0,
				0,0,0,0		}}, //矩形
			{{	1,1,1,0,
				1,0,0,0,
				0,0,0,0,
				0,0,0,0	},
			{	1,0,00,
				1,0,0,0,
				1,1,0,0,
				0,0,1,0	},
			{	1,1,1,0,
				0,0,0,0,
				0,0,0,0,
				0,0,0,0	}, 
			{	1,1,0,0,
				0,1,0,0,
				0,1,0,0,
				0,0,0,0		}}, //L					
			{{	0,1,0,0,
				0,1,0,0,
				1,1,0,0,
				0,0,0,0	},
			{	1,1,1,0,
				0,0,1,0,
				0,0,0,0,
				0,0,0,0	},
			{	1,1,0,0,
				1,0,0,0,
				1,0,0,0,
				0,0,0,0	},
			{	1,0,0,0,
				1,1,1,0,
				0,0,0,0,
				0,0,0,0		}},//J
			{{	1,1,1,0,
				0,1,0,0,
				0,0,0,0,
				0,0,0,0	},
			{	1,0,0,0,
				1,1,0,0,
				1,0,0,0,
				0,0,0,0	},
			{	0,1,0,0,
				1,1,1,0,
				0,0,0,0,
				0,0,0,0	},
			{	0,1,0,0,
				1,1,0,0,
				0,1,0,0,
				0,0,0,0		}},//T
			{{	1,1,0,0,
				0,1,1,0,
				0,0,0,0,
				0,0,0,0	},
			{	0,1,0,0,
				1,1,0,0,
				1,0,0,0,
				0,0,0,0		}},//Z
			{{	0,1,1,0,
				1,1,0,0,
				1,0,0,0,
				0,0,0,0	},
			{	1,0,0,0,
				1,1,0,0,
				0,1,0,0,
				0,0,0,0		}}//反Z
	};
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

