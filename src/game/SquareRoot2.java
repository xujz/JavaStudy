package game;

public class SquareRoot2 extends SquareRoot implements Data
{
	public SquareRoot2()
	{
		state = (int)(Math.random() * 2);
		switch(state)
		{
			case 0:
				square[0].x = 4;
				square[0].y = 0;
				square[1].x = square[0].x;
				square[1].y = square[0].y + 1;
				square[2].x = square[0].x;
				square[2].y = square[0].y + 2;
				square[3].x = square[0].x;
				square[3].y = square[0].y + 3;
				break;
				
			case 1:
				square[0].x = 4;
				square[0].y = 0;
				square[1].x = square[0].x + 1;
				square[1].y = square[0].y;
				square[2].x = square[0].x + 2;
				square[2].y = square[0].y;
				square[3].x = square[0].x + 3;
				square[3].y = square[0].y;
				break;
			default:
				break;
			
		}
		
	}
	
	public void changeState(int flag[][])
	{
		switch(state)
		{
			case 0:
				tempX[0] = square[0].x - 1;
				tempY[0] = square[0].y + 1;
				tempX[1] = tempX[0] + 1;
				tempY[1] = tempY[0];
				tempX[2] = tempX[0] + 2;
				tempY[2] = tempY[0];
				tempX[3] = tempX[0] + 3;
				tempY[3] = tempY[0];
				isAllowChangeState(flag, 2);
				break;
				
			case 1:
				tempX[0] = square[0].x + 1;
				tempY[0] = square[0].y - 1;
				tempX[1] = tempX[0];
				tempY[1] = tempY[0] + 1;
				tempX[2] = tempX[0];
				tempY[2] = tempY[0] + 2;
				tempX[3] = tempX[0];
				tempY[3] = tempY[0] + 3;
				isAllowChangeState(flag, 2);
				break;
				
			default:
				break;
		}
	}
}
