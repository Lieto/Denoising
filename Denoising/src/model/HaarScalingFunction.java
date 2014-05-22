package model;

public class HaarScalingFunction {
	
	public double input;
	public double output;
	public int length;
	
	public double getOutput()
	{
		return output;
	}
	
	public void setInput(double input)
	{
		this.input = input;
	
	}
	
	public double solve()
	{
		int first = 0;
		
		if (input >= 0.0 && input < 1.0)
			first = 1;
		
		
		return first;
	}

}
