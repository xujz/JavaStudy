package tetris;

import java.awt.Color;
import java.awt.Graphics;
/**
 * 场景类
 * @author Administrator
 *
 */
public class Scene {

	public final static int BLOCK_LENGTH = 25;
	public final static int MAX_COL = GameUI.WIDTH/BLOCK_LENGTH;
	public final static int MAX_ROW = GameUI.HEIGHT/BLOCK_LENGTH;
	private int[][] space = new int[MAX_ROW][MAX_COL];
	
	public Scene(){
		for(int j=0;j<space.length;j++){
			for(int i = 0;i<space[0].length;i++){
				space[j][i]=0;
			}
		}
	}
	public void drawMe(Graphics graphics){
		System.out.println("场景显示");
		graphics.setColor(Color.WHITE);
		for(int j=0;j<space.length;j++){
			for(int i = 0;i<space[0].length;i++){
				if(space[j][i]==1){
					graphics.fill3DRect(i*BLOCK_LENGTH, j*BLOCK_LENGTH, BLOCK_LENGTH, BLOCK_LENGTH, true);
				}
			}
		}
	}
	public void addShape(Shape s,int x,int y){
		System.out.println("场景添加形状");
		int [][] block = s.getBlock();
		for(int j=0;j<block.length;j++){
			for(int i=0;i<block[0].length;i++){
				if(block[j][i]==1){
					space[y+j][x+i]=block[j][i];
				}
			}
		}
		int top = s.getTopStart();
		int bot = s.getBottomStart();
		int rows = 0;
		for(int j = top+y;j<=bot+y;j++){
			if(this.isFullRow(j)){
				this.deleteRow(j);
				rows++;
			}
		}
		CommonUtil.getUserControl().addScore(rows);
	}
	private boolean isFullRow(int row){
		for(int i=0;i<space[0].length;i++){
			if(space[row][i]==0){
				return false;
			}
		}
		return true;
	}
	private void deleteRow(int row){
		int lastEmptyRow = this.getLastEmptyRow(row-1);
		for(int j = row;j>=lastEmptyRow+1;j--){
			this.copyRow(j, j-1);
		}
	}
	public void copyRow(int dest,int src){
		for(int i=0;i<space[0].length;i++){
			space[dest][i] = space[src][i];
		}
	}
	private boolean isEmptyRow(int row){
		for(int i=0;i<space[0].length;i++){
			if(space[row][i]==1){
				return false;
			}
		}
		return true;
	}
	private int getLastEmptyRow(int from){
		for(int j = from;j>=0;j--){
			if(this.isEmptyRow(j)){
				return j;
			}
		}
		return 0;
	}
	
	public int[][] getSpace(){
		return space;
	}

	public void clearSapce(){
		for(int j=0;j<space.length;j++){
			for(int i = 0;i<space[0].length;i++){
				space[j][i]=0;
			}
		}
	}
}
