package model;

import java.util.ArrayList;

public class HaarWavelet extends FunctionData {
	
	public HaarWaveletFunction function;
	
	public void calculateOutput()
	{
		function = new HaarWaveletFunction();
		
		output = new ArrayList<Double>();
		
		for (int i = 0; i < length; i++)
		{
			function.setInput(input.get(i));
			function.solve();
			output.add(function.getOutput());
			
		}
				
	}

}
