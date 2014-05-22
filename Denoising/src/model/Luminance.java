package model;

import java.awt.Color;

public class Luminance {
	
	public static double lum(Color color)
	{
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		return .299*r + .587*g + .114*b;
	}
	
	public static Color toGray(Color color)
	{
		int y = (int) (Math.round(lum(color)));
		Color gray = new Color(y, y, y);
		return gray;
	}

}
