package model;

public class HaarShrinkageFunction {
	
	private double input;
	private double output;
	private double phii;
	
	public double getOutput()
	{
		return output;
	}
	
	public void setPhii(double phii)
	{
		this.phii = phii;
	}
	
	public void setInput(double input)
	{
		this.input = input;
	}
	
	public void solve()
	{
		output = 0.0;
		
		if (Math.abs(input) > phii)
		{
			output = input - phii * Math.signum(input);
		}
	}

}
