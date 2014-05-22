package model;

import java.util.ArrayList;

public class Sinusoid {
	
	public ArrayList<Double> input;
	public ArrayList<Double> output;
	public int length;
	public SineFunction function;
	
	public void setInput(ArrayList<Double> input)
	{
		this.length = input.size();
		
		this.input = new ArrayList<Double>();
		this.input.addAll(input);
		
	}
	
	public ArrayList<Double> getOutput()
	{
		ArrayList<Double> tmp = new ArrayList<Double>();
		tmp.addAll(output);
		return tmp;
	}
	
	
	public void calculateOutput()
	{
		function = new SineFunction();
		
		output = new ArrayList<Double>();
		
		for (int i = 0; i < length; i++)
		{
			function.setInput(input.get(i));
			function.solve();
			output.add(function.getOutput());
			
		}
				
	}

}
