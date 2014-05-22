package model;

public class DilatedHaarScalingFunction extends HaarScalingFunction {
	
	public double i;
	public double n;
	
	public DilatedHaarScalingFunction(double i, double n)
	{
		super();
		
		this.i = i;
		this.n = n;
	}
	
	public double solve()
	{
		input = input * Math.pow(2, -n) - i;
		
		output = super.solve();
		
		return output * Math.pow(2, -n/2);
		
	}


}
