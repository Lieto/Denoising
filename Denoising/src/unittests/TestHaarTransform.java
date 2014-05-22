package unittests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import kone.core.Matrixd;
import kone.core.Vectord;

import model.DilatedHaarWavelet;
import model.FunctionData;
import model.HaarMatrix;
import model.Luminance;

import org.junit.Test;

public class TestHaarTransform {

	@Test
	public void test1DHaar() {
		
		//Create test row
		ArrayList<Double> indexes = new ArrayList<Double>();
		for (int i = 0; i < 8; i++)
			indexes.add((double) i/8);
		
		String ret = " ";
		
		for (int i = 0; i < indexes.size(); i++)
		{
			ret += indexes.get(i) + " ";
		}
		
		System.out.println(ret);
		
		Double[] testArray = new Double[]{1.0, 2.0, 3.0, 1.0, 2.0, 3.0, 4.0, 0.0};
		ArrayList<Double> testList = new ArrayList<Double>(Arrays.asList(testArray));
				//(ArrayList<Double>) Arrays.asList(testArray);
		//ArrayList<Double> testList = new ArrayList<Double>();
		//testList.addAll(Arrays.a
		
		FunctionData testData = new FunctionData();
		testData.setInput(indexes);
		testData.setOutput(testList);
		
		ret = " ";
		
		for (int i = 0; i < testData.getOutput().size(); i++)
		{
			ret += testData.getOutput().get(i) + " ";
		}
		
		System.out.println(ret);
		
		//Calculate Haar basis functions
		// Length of data = 8 -> i = 0 .. log2(length) - 1; j =  0 .. 2^i
		HaarMatrix haar = new HaarMatrix(testArray.length, testArray.length);
		System.out.println(haar.toString());
		
	
		//Check inner product
		Vectord base1 = haar.getRow(1);
		Vectord base2 = haar.getRow(2);
		double result = base1.ScalarProduct(base2);
				
		// Haar basis should be orthonormal-> innerproduct agianst other = 0
		assertEquals(0.0, result, 0.001);
		
		// inner product with itself should be 1
		Vectord base3 = haar.getRow(1);
		result = base3.ScalarProduct(base3);
		
		assertEquals(1.0, result, 0.001);
		
		// Calc coefficients
		ArrayList<Double> waveletCoeffs = new ArrayList<Double>();
		
		// coeff<Vector> = Mhaar x inputT
		Matrixd input = new Matrixd(testArray.length, 1);
		input.setColumn(0, testArray);
		
		System.out.println(input.toString());
		
		Matrixd output = haar.Multiply(input);
		
		System.out.println(output.toString());
		
	}

	@Test
	public void testSimple2D()
	{
		int width = 4;
		int height = 4;
		
		Matrixd image = new Matrixd(width, height);
		image.entry[0][0] = 10.0;
		image.entry[0][1] = 5.0;
		image.entry[0][2] = 20.0;
		image.entry[0][3] = 10.0;
		
		image.entry[1][0] = 20.0;
		image.entry[1][1] = 30.0;
		image.entry[1][2] = 40.0;
		image.entry[1][3] = 50.0;
		
		image.entry[2][0] = 10.0;
		image.entry[2][1] = 30.0;
		image.entry[2][2] = 40.0;
		image.entry[2][3] = 60.0;
		
		image.entry[3][0] = 60.0;
		image.entry[3][1] = 90.0;
		image.entry[3][2] = 40.0;
		image.entry[3][3] = 60.0;
		
		System.out.println(image.toString());
		
		Matrixd haar = new HaarMatrix(width, height);
		System.out.println(haar.toString());
		
		Matrixd coeffs = new Matrixd(width, height);
		
		// Transform row by row
		for  (int i =  0; i < height; i++)
		{
			Vectord row = image.getRow(i);
			
			Vectord coeff = haar.Multiply(row);
			
			coeffs.setRow(i, coeff);
			
		}
		
		System.out.println(coeffs.toString());
		
		//Transform column by column
		for (int j = 0; j < width; j++)
		{
			Vectord column = coeffs.getColumn(j);
			
			Vectord coeff = haar.Multiply(column);
			
			coeffs.setColumn(j, coeff);
		}
		
		System.out.println(coeffs.toString());
		
	}
	
	@Test
	public void test2DHaar()
	{

		//Load image and create matrix from it
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File("C:\\computervision\\Denoising\\src\\Baboon.jpg"));
		}
		catch (IOException ioe)
		{
			
		}
		
		int type = originalImage.getType();
		int imageWidth = originalImage.getWidth();
		int imageHeight = originalImage.getHeight();
		
		System.out.println(type);
		System.out.println(imageWidth);
		System.out.println(imageHeight);
		
		Matrixd grayImage = new Matrixd(imageWidth, imageHeight);
		
		for (int i = 0; i < imageWidth; i++)
		{
			for (int j = 0; j < imageHeight; j++)
			{
			 
				Color tmp = new Color(originalImage.getRGB(i, j));
				Color gray = Luminance.toGray(tmp);
				
				grayImage.entry[i][j] = (double) gray.getBlue();
				
			}
		}
		
		
		//Calculate Haar bases
		
		Matrixd haar = new HaarMatrix(imageWidth, imageHeight);
		
		/*
		Matrixd haarT = haar.Transpose();
		haar = haar.Multiply( haarT);
		
		BufferedImage haarImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < imageWidth; i++)
		{
			for (int j = 0; j < imageHeight; j++)
			{
				double value = haar.entry[i][j];
				//value = value * 255;
			
				haarImage.setRGB(i, j, (int) value);
			}
		}
		
		
		try {
			ImageIO.write(haarImage, "jpg", new File("C:\\computervision\\Denoising\\src\\HaarImage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Matrixd coeffs = new Matrixd(imageWidth, imageHeight);
		
		// Transform row by row
		for  (int i =  0; i < imageHeight; i++)
		{
			Vectord row = grayImage.getRow(i);
			
			Vectord coeff = haar.Multiply(row);
			
			coeffs.setRow(i, coeff);
			
		}
		
		//System.out.println(coeffs.toString());
		
		//Transform column by column
		for (int j = 0; j < imageWidth; j++)
		{
			Vectord column = coeffs.getColumn(j);
			
			Vectord coeff = haar.Multiply(column);
			
			coeffs.setColumn(j, coeff);
		}
		
		//System.out.println(coeffs.toString());
		
		

		/*
		//Multiply Haar-matrix with image matrix
		Matrixd Coeffs = haar.Multiply(grayImage.Transpose());
		Coeffs = haar.Multiply(Coeffs);
		Coeffs = Coeffs.Transpose();
		*/
		
		BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < imageWidth; i++)
		{
			for (int j = 0; j < imageHeight; j++)
			{
				double value = coeffs.entry[i][j];
			
				outputImage.setRGB(i, j, (int) value);
			}
		}
		
		try {
			ImageIO.write(outputImage, "jpg", new File("C:\\computervision\\Denoising\\src\\Koe.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
