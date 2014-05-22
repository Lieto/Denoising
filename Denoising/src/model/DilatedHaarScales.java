package model;

import java.util.ArrayList;

public class DilatedHaarScales extends FunctionData {
	
	private int i;
	private int n;
	
	public DilatedHaarScalingFunction function;
	
	public DilatedHaarScales(int i, int n)
	{
		setI(i);
		setN(n);
	}
	
	public void setI(int i)
	{
		this.i = i;
	}
	
	public void setN(int n)
	{
		this.n = n;
		
	}
	
	public void calculateOutput()
	{
		function = new DilatedHaarScalingFunction(i, n);
		
		output = new ArrayList<Double>();
		
		for (int k = 0; k < input.size(); k++)
		{
			function.setInput(input.get(k));
			function.output = function.solve();
			output.add(function.getOutput());
			
		}
				
	}
	
	public String toString()
	{
		String ret = "";
		
		for (int i = 0; i < output.size(); i++)
		{
			ret += output.get(i) + " ";
		}
		
		return ret;
	}


}
