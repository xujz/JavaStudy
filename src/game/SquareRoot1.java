package game;

public class SquareRoot1 extends SquareRoot implements Data
{
	public SquareRoot1()
	{
		square[0].x = 4;
		square[0].y = 0;
		square[1].x = square[0].x + 1;
		square[1].y = 0;
		square[2].x = square[0].x;
		square[2].y = square[0].y + 1;
		square[3].x = square[0].x + 1;
		square[3].y = square[0].y + 1;
	}
	
	public void changeState(int flag[][])
	{
		
	}
	
}
