package model;

import java.util.ArrayList;

public class DilatedHaarWavelet extends FunctionData {
	
	private int i;
	private int j;
	
	public DilatedHaarWaveletFunction function;
	
	public DilatedHaarWavelet(int i, int j)
	{
		setI(i);
		setJ(j);
	}
	
	public void setI(int i)
	{
		this.i = i;
	}
	
	public void setJ(int j)
	{
		this.j = j;
		
	}
	
	public void calculateOutput()
	{
		function = new DilatedHaarWaveletFunction(i, j);
		
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
