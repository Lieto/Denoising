package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import contoller.WaveLetManager;

public class ImageView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WaveLetManager manager;

	public void init(WaveLetManager manager) {
		this.manager = manager;
		this.setBackground(Color.white);
		
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		
		/*
		 * Draw gray image
		 */
		
		BufferedImage gray = new BufferedImage(manager.scaledWidth, manager.scaledHeight, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < manager.scaledWidth; i++)
			for (int j = 0; j < manager.scaledHeight; j++)
				gray.setRGB(i, j, manager.grayImage[i][j].getRGB());
		
		g.drawImage(gray, 0, 0, manager.scaledWidth, manager.scaledHeight, null);
		
		/*
		 * Draw noisy image
		 */
		
		
		BufferedImage noisyGrey = new BufferedImage(manager.scaledWidth, manager.scaledHeight, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < manager.scaledWidth; i++)
			for (int j = 0; j < manager.scaledHeight; j++)
				noisyGrey.setRGB(i, j, manager.noisyImage[i][j].getRGB());
		
		g.drawImage(noisyGrey, manager.scaledWidth + 10, 0, manager.scaledWidth, manager.scaledHeight, null);
		
		
	}

}
