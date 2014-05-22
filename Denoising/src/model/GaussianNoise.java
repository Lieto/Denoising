package model;

import java.util.ArrayList;
import java.util.Random;

public class GaussianNoise {
	

	public ArrayList<Double> input;
	public ArrayList<Double> output;
	public int length;
	public NoiseFunction function;
	
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
		Random random = new Random();
		function = new NoiseFunction(random);
		
		output = new ArrayList<Double>();
		
		for (int i = 0; i < length; i++)
		{
			function.setInput(input.get(i));
			function.solve();
			output.add(function.getOutput());
			
		}
				
	}


}
