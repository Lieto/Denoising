package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import contoller.WaveLetManager;

public class HaarFunctionView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WaveLetManager manager;

	public void init(WaveLetManager manager) {
		
		this.manager = manager;
		this.setBackground(Color.white);
		
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		
		double xPos;
		double yPos = 0;
		
		int origoX = manager.getWidth() / 2;
		//int origoY = manager.getHeight() / 2;
		
		ArrayList<Double> in; 
		//ArrayList<Double> out;
		
		g.setColor(Color.red);
		

		in = manager.getInput();
		// Show sinusoid
		ArrayList<Double> sine = manager.sinusoid.getOutput();
		ArrayList<Double> noisySine = manager.noisySinusoid;
		
		for (int k = 0; k < sine.size(); k++)
		{
			int startX = origoX - 200;
			int endX = origoX + 200;
			int startY = 300 - 20*10 - 10;
			int endY = 300 + 20*10 + 10;
			
			g.drawLine(startX, endY, endX, endY);
			
			
			g.drawLine(startX, startY, startX, endY);
			
			
			xPos = origoX + in.get(k)*10;
			yPos = 300  -sine.get(k)*10;
			
			//Original sine
			g.setColor(Color.red);
			g.drawOval((int) xPos, (int) yPos, 2, 2);
			
			//NoisySine
			g.setColor(Color.blue);
			yPos = 300  -noisySine.get(k)*10;
			g.drawOval((int) xPos, (int) yPos, 2, 2);
				
		}
		
		/*
		g.setColor(Color.blue);
		// Show noisy sinusoid
		ArrayList<Double> noisySine = manager.noisySinusoid;
		for (int k = 0; k < noisySine.size(); k++)
		{
			int startX = origoX - 200;
			int endX = origoX + 200;
			int startY = 800 - 20*10 - 10;
			int endY = 800 + 20*10 + 10;
			
			g.drawLine(startX, endY, endX, endY);
			
			
			g.drawLine(startX, startY, startX, endY);
			xPos = origoX + in.get(k)*10;
			yPos = 800  -noisySine.get(k)*10;
						
			g.drawOval((int) xPos, (int) yPos, 2, 2);
			
		}
		*/
		/*
		for (int i = 0; i < 10; i++)
		{
			int startX = origoX - 200;
			int endX = origoX + 200;
			int startY = 300 + 100*i + 10;
			int endY = 500 + 100*i -10;
			
			g.drawLine(startX, startY, endX, startY);
			
			
			g.drawLine(startX, startY, startX, endY);
			
			
			
			out = manager.getOutput(i);

			for (int k = 0; k < out.size(); k++)
			{
				xPos = origoX + in.get(k)*10;
				yPos = 550 + 100*i -out.get(k)*10;
					
				g.drawOval((int) xPos, (int) yPos, 2, 2);
					
			}
		}
		*/
		
		//Draw y axis
		//Draw x axis
		//Draw sample as circle
		
		
	}

}
