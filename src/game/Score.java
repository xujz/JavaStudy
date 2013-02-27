package game;


public class Score implements Comparable, java.io.Serializable
{
	public String name;
	public int score;
	
	public Score(String name, int score)
	{
		this.name = name;
		this.score = score;
	}
	
	public int compareTo(Object a)
	{
		return score - ((Score)a).score;
	}
}

