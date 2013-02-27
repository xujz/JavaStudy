package game;

public interface Data
{
	public final int BIGLEFT = 10;
	public final int BIGRIGHT = BIGLEFT + 200;
	public final int BIGUP = 0;
	public final int BIGDOWN = BIGUP + 400;
	public final int LITTLELEFT = 230;
	public final int LITTLERIGHT = LITTLELEFT + 80;
	public final int LITTLEUP = 0;
	public final int LITTLEDOWN = LITTLEUP + 80;
	public final int LONG = 20;
	
	public final int LEFT = 0;
	public final int RIGHT = 9;
	public final int UP = 0;
	public final int DOWN =19;
	
	public final int LITTLEX = 11;
	
	public int INIT_STATE = 0;
	public int RUN_STATE = 1;
	public int PAUSE_STATE = 2;
	public int OVER_STATE = 3;
}
