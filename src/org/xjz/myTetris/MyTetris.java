package org.xjz.myTetris;

import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JFrame;

public class MyTetris extends JFrame {
	
	
	public MyTetris(){
		System.out.println("����ĳ����ǣ�"+shapes.length);
		System.out.println("����������ݣ�");
		int p = 1;
		for(int i =0;i<shapes.length;i++){
			for(int j = 0;j<shapes[i].length;j++){
					System.out.println("��"+p+++"�У�"+Arrays.toString(shapes[i][j])+"i="+i+" j="+j );
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
				0,0,0,0		}}, //����
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
				0,0,0,0		}}//��Z
	};
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

