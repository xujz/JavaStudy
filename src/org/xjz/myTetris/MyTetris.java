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
	
	
	
	
	
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		super.print(g);
	}





	private int[][][] shapes = new int[][][]{
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

