package model;

import java.util.Random;

public class GaussianNoise2D {

	public int width;
	public int height;
	public double[][] output;
	
	public NoiseFunction function;
	
	public GaussianNoise2D(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		output = new double[width][height];
		
	}
	
	public double[][] getOutput()
	{
		return output;
	}
	
	
	public void calculateOutput()
	{
		Random random = new Random();
		function = new NoiseFunction(random);
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				output[i][j] = function.solve();
			
		
				
	}

}
