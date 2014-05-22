package model;

public class HaarTransform {
	
	public int[][] image;
	public int[][] output;
	
	public int width;
	public int height;
	
	public HaarTransform(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		image = new int[width][height];
	}
	
	public void setInput(int[][] image)
	{
		this.image = image;
	}
	
	public void calculate()
	{
		for (int i = 0; i < width; i++)
		{
			int w = height;
			
			do
			{
				//int k = 0;
				
				for (int j = 0; j < w/2; j++);
					
			}
			while (w != 1);
		}
		
	}
	
	public int[][] getOutput()
	{
		return output;
	}
	
	

}
