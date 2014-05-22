package model;

public class DilatedHaarWaveletFunction extends HaarWaveletFunction {
	
	public double i;
	public double j;
	
	public DilatedHaarWaveletFunction(double i, double j)
	{
		super();
		
		this.i = i;
		this.j = j;
	
	}
	
	public double solve()
	{
		if (i == 0.0 && j == 0.0)
		{
			if (input >= 0.0 && input <= 1.0)
				return 1.0;
		}
		
		
		double pow = Math.pow(2, i);
		double sqrt = Math.sqrt(pow);
		
		double min = (j - 1) / pow;
		double max = j / pow;
		double middle = (j - 0.5) / pow;
		
		if (input >= min && input < middle)
		{
			return sqrt;
			
		}
		else if (input >= middle && input < max)
		{
			return -sqrt;
		}
		else
		{
			return 0.0;
		}
		
	}
	
	/*
	public double solve()
	{
		input = input * Math.pow(2, -j) - i;
		
		double ret = super.solve();
		
		//return ret;
		
		return ret * Math.pow(2, -j/2);
		
	}
	*/

}
