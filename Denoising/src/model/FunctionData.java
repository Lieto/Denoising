package model;

import java.util.ArrayList;

public class FunctionData {
	
	
	public ArrayList<Double> input;
	public ArrayList<Double> output;
	public int length;
	
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
	
	public void setOutput(ArrayList<Double> data)
	{
		this.output = new ArrayList<Double>();
		this.output.addAll(data);
		
	}
	
	
	public void calculateOutput()
	{
				
	}
	
	public double InnerProduct(FunctionData other)
	{
		//ArrayList<Double> tmp = new ArrayList<Double>();
		double value  = 0.0;
		for (int i = 0; i < input.size(); i++)
		{
			value  += this.output.get(i) * other.getOutput().get(i);
			//tmp.add(this.output.get(i) * other.getOutput().get(i)); 
			
		}
		
		return value/input.size();
	}

}
