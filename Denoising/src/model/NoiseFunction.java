package model;

import java.util.Random;

public class NoiseFunction extends Function {
	
	Random random;
	
	public NoiseFunction(Random random)
	{
		this.random = random;
	}
	
	public double solve()
	{
		return 10*random.nextGaussian();
	}


}
