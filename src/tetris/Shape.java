package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
/**
 * 图形类
 * @author Administrator
 *
 */
public class Shape {

	private String name;
	public static int number = 0;
	private int x=Scene.MAX_COL/2-2,y=0;
	private static int[][][][] blocks = new int[7][][][];
	private static Random r = new Random();
	static{
		blocks[0] = new int[][][]{
				{
					{0,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,1,0,0}
				},{
					{0,0,0,0},
					{0,0,0,0},
					{1,1,1,0},
					{1,0,0,0}
				},{
					{0,0,0,0},
					{1,1,0,0},
					{0,1,0,0},
					{0,1,0,0}
				},{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,1,0},
					{1,1,1,0}
				}
		};
		blocks[1] = new int[][][]{
			{
				{0,0,0,0},
				{0,0,1,0},
				{0,0,1,0},
				{0,1,1,0}
			},
			{
				{0,0,0,0},
				{0,0,0,0},
				{1,0,0,0},
				{1,1,1,0}
			},
			{
				{0,0,0,0},
				{0,1,1,0},
				{0,1,0,0},
				{0,1,0,0}
			},
			{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,1,0},
				{0,0,1,0}
			}
		};
		blocks[2] = new int[][][]{
			{
				{0,0,0,0},
				{0,0,0,0},
				{0,1,0,0},
				{1,1,1,0}
			},
			{
				{0,0,0,0},
				{1,0,0,0},
				{1,1,0,0},
				{1,0,0,0}
			},{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,1,0},
				{0,1,0,0}
			},{
				{0,0,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{0,1,0,0}
			}
			
		};
		blocks[3] = new int[][][]{
			{
				{0,0,0,0},
				{0,0,1,0},
				{0,1,1,0},
				{0,1,0,0}
			},			{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,0,0},
				{0,1,1,0}
			},			{
				{0,0,0,0},
				{0,0,1,0},
				{0,1,1,0},
				{0,1,0,0}
			},			{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,0,0},
				{0,1,1,0}
			}
		};
		blocks[4] = new int[][][]{
			{
				{0,0,0,0},
				{0,1,0,0},
				{0,1,1,0},
				{0,0,1,0}
			},			{
				{0,0,0,0},
				{0,0,0,0},
				{0,1,1,0},
				{1,1,0,0}
			},			{
				{0,0,0,0},
				{0,1,0,0},
				{0,1,1,0},
				{0,0,1,0}
			},
			{
				{0,0,0,0},
				{0,0,0,0},
				{0,1,1,0},
				{1,1,0,0}
			}	
		};
		blocks[5] = new int[][][]{
			{
				{0,0,1,0},
				{0,0,1,0},
				{0,0,1,0},
				{0,0,1,0}
			},	
			{
				{0,0,0,0},
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0}
			},			{
				{0,0,1,0},
				{0,0,1,0},
				{0,0,1,0},
				{0,0,1,0}
			},
			{
				{0,0,0,0},
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0}
			}
		};
		blocks[6] = new int[][][]{
				{
					{0,0,0,0},
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0}
				},	
				{
					{0,0,0,0},
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0}
				},			{
					{0,0,0,0},
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0}
				},
				{
					{0,0,0,0},
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0}
				}
			};
	}
	private int tx = 0,ty = 0;
	private int[][] block ;
	
	
	public Shape(){
		tx = r.nextInt(blocks[0].length);
		ty = r.nextInt(blocks.length);
		block = blocks[ty][tx];
		number++;
		name = "shape"+number;
	}
	public int[][] copyShape(int[][] blc){
		int [][] rt = new int[blc.length][blc[0].length];
		for(int j = 0;j<rt.length;j++){
			for(int i=0;i<rt[0].length;i++){
				rt[j][i] = blc[j][i];
			}
		}
		return rt;
	}
	public int getLeftStart(){
		for(int i=0;i<block[0].length;i++){
			for(int j=0;j<block.length;j++){
				if(block[j][i]==1){
					return i;
				}
			}
		}
		return 0;
	}
	public int getRightStart(){
		for(int i=block[0].length-1;i>=0;i--){
			for(int j=0;j<block.length;j++){
				if(block[j][i]==1){
					return i;
				}
			}
		}
		return 0;
	}
	public int getBottomStart(){
		for(int j=block.length-1;j>=0;j--){
			for(int i=0;i<block[0].length;i++){
				if(block[j][i]==1){
					return j;
				}
			}
		}
		return 0;
	}
	public int getTopStart(){
		for(int j=0;j>block.length;j++){
			for(int i=0;i<block[0].length;i++){
				if(block[j][i]==1){
					return j;
				}
			}
		}
		return 0;
	}
	
	public void goLeft(){
		System.out.println(name+"左移");
		if(getLeftStart()+x>=1){
			x--;
		}
	}
	public void goRight(){
		System.out.println(name+"右移");
		if(getRightStart()+x<Scene.MAX_COL-1){
			x++;
		}
	}
	public void goDown(){
		System.out.println(name+"下移");
		y++;
	}
	public void drawMe(Graphics graphics){
		
		graphics.setColor(Color.WHITE);
		for(int j = 0;j<block.length;j++){
			for(int i = 0;i<block[0].length;i++){
				if(block[j][i]==1){
					graphics.fill3DRect((x+i)*Scene.BLOCK_LENGTH, (y+j)*Scene.BLOCK_LENGTH, Scene.BLOCK_LENGTH, Scene.BLOCK_LENGTH, true);
				}
			}
		}
		System.out.println(name+"显示");
	}
	public void trans(){
		System.out.println(name+"trans");
		tx++;
		if(tx>=blocks[0].length){
			tx = 0;
		}
		block = blocks[ty][tx];
	}
	public String toString(){
		return name;
	}
	public int[][] getBlock(){
		return block;
	}
	public int getLocX(){
		return x;
	}
	public int getLocY(){
		return y;
	}
	public static void main(String[] args){
		
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		
	}
}
