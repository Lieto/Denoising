package model;

public class HaarWaveletFunction extends Function {
	
	public double solve()
	{
		double first = 0;
		double second  = 0;

		if (input >= 0.0 && input < 0.5)
			first = 1.0;
		
		if (input  >= 0.5 && input < 1.0)
			second = -1.0;
		
		return first + second;
		
	}

}
