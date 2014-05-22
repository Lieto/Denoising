package contoller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;

import javax.swing.JFrame;


import model.DilatedHaarWavelet;
import model.GaussianNoise;
import model.GaussianNoise2D;

import model.Luminance;
import model.Sinusoid;

import view.HaarFunctionView;
import view.ImageView;

public class WaveLetManager extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HaarFunctionView haarFunctionView;
	public ArrayList<Double> input;
	public ArrayList<Double> output;
	public Sinusoid sinusoid;
	public GaussianNoise gnoise;
	public ArrayList<Double> noisySinusoid;
	public int inputLength = 100;
	public DilatedHaarWavelet dilatedHaarWavelet;
	public Color[][] grayImage;
	public Color[][] noisyImage;
	public GaussianNoise2D gaussianNoise2D;
	public ImageView imageView;
	public int imageWidth, imageHeight, type;
	public ArrayList<DilatedHaarWavelet> wavelets;
	public BufferedImage originalImage;
	public int scalingFactor = 2;
	public int scaledWidth, scaledHeight;
	
	public WaveLetManager()
	{
		haarFunctionView = new HaarFunctionView();
		imageView = new ImageView();
	}
	
	public void initInput()
	{
		originalImage = null;
		try {
			originalImage = ImageIO.read(new File("C:\\computervision\\Denoising\\src\\Baboon.jpg"));
		}
		catch (IOException ioe)
		{
			
		}
		
		type = originalImage.getType();
		imageWidth = originalImage.getWidth();
		imageHeight = originalImage.getHeight();
		
		if (scalingFactor != 0)
		{
			scaledWidth = imageWidth/scalingFactor;
			scaledHeight = imageHeight/scalingFactor;
		}
		else
		{
			scaledWidth = imageWidth;
			scaledHeight = imageHeight;
		}
		
		/*
		System.out.println("Type " + type);
		System.out.println(BufferedImage.TYPE_INT_ARGB);
		System.out.println("Width: " + imageWidth);
		System.out.println("Height: " + imageHeight);
		*/
		//Write to 2-dimensional array with only grey values
		
		grayImage = new Color[scaledWidth][scaledHeight];
		
		for (int i = 0; i < imageWidth; i = i + scalingFactor)
		{
			for (int j = 0; j < imageHeight; j = j + scalingFactor)
			{
			 
				Color tmp = new Color(originalImage.getRGB(i, j));
				Color gray = Luminance.toGray(tmp);
				
				if (scalingFactor != 0)
				{
					grayImage[i/scalingFactor][j/scalingFactor] = gray;
				}
				else
				{
					grayImage[i][j] = gray;
					
				}
				
			}
		}
		
		//Create gaussian noise
		gaussianNoise2D = new GaussianNoise2D(scaledWidth, scaledHeight);
		gaussianNoise2D.calculateOutput();
		
	
		input = new ArrayList<Double>();
		double min = -10.0;
		double max = 10.0;
		
		for (int i = 0; i < inputLength; i++)
		{
			double tmp = min + (double) (i*(max-min)/inputLength);
			input.add(tmp);
		}
		
		
		
	}
	
	public void initOutput()
	{
		output = new ArrayList<Double>();
		wavelets = new ArrayList<DilatedHaarWavelet>();
		noisySinusoid = new ArrayList<Double>();
	}
	
	public void init()
	{
		
		initInput();
		initOutput();
		
		/*
		//Calculate sin function
		sinusoid = new Sinusoid();
		sinusoid.setInput(getInput());
		sinusoid.calculateOutput();
		
		gnoise = new GaussianNoise();
		gnoise.setInput(getInput());
		gnoise.calculateOutput();
		*/
		// Add gaussian noise to original gray
		Color tmp = null;
		double[][] g = gaussianNoise2D.getOutput();
		long valueR, valueG, valueB;
		
		noisyImage = new Color[scaledWidth][scaledHeight];
		
		for (int i = 0; i < scaledWidth; i++)
			for (int j = 0; j < scaledHeight; j++)
			{
				valueR = valueG = valueB = 0;
				
				tmp = grayImage[i][j];
				
				valueR = Math.round(tmp.getRed() + g[i][j]);
				if (valueR > 255)
					valueR = 255;
				
				if (valueR < 0)
					valueR = 0;
				
				valueG = Math.round(tmp.getGreen() + g[i][j]);
				
				if (valueG > 255)
					valueG = 255;
				
				if (valueG < 0)
					valueG = 0;
				valueB = Math.round(tmp.getBlue() + g[i][j]);
				
				if (valueB > 255)
					valueB = 255;
				
				if (valueB < 0)
					valueB = 0;
				
				Color color = new Color((int) valueR, (int)valueG, (int)valueB);
				
				noisyImage[i][j] = Luminance.toGray(color);
				
			}
				
		/*
		for (int i = 0; i < gnoise.getOutput().size(); i++)
		{
			tmp = sinusoid.getOutput().get(i);
			tmp += gnoise.getOutput().get(i);
			noisySinusoid.add(tmp);
		}
		*/
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 1; j++)
			{
				dilatedHaarWavelet = new DilatedHaarWavelet(i, j);
				dilatedHaarWavelet.setInput(getInput());
				dilatedHaarWavelet.calculateOutput();
				
				output.clear();
				output.addAll(dilatedHaarWavelet.getOutput());
				wavelets.add(dilatedHaarWavelet);
				
			}
		}
		
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		haarFunctionView.init(this);
		imageView.init(this);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.setSize((new Dimension(1024, 780)));
		this.setTitle("WaveletDemo");
		
		//this.getContentPane().add(haarFunctionView, BorderLayout.CENTER);
		this.getContentPane().add(imageView, BorderLayout.CENTER);
		this.validate();
		this.setVisible(true);
	}
	
	public ArrayList<Double> getOutput(int index)
	{
		return wavelets.get(index).getOutput();
	}
	
	public ArrayList<Double> getInput()
	{
		return input;
	}



}
