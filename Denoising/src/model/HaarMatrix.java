package model;

import java.util.ArrayList;

import kone.core.Matrixd;

public class HaarMatrix extends Matrixd {

	public HaarMatrix(int rows, int columns) {
		super(rows, columns);
		
		ArrayList<DilatedHaarWavelet> dilatedHaars = new ArrayList<DilatedHaarWavelet>();
		//Indexes
		ArrayList<Double> indexes = new ArrayList<Double>();
		for (int i = 0; i < rows; i++)
			indexes.add((double) i/rows);
		
		//Add (0,0)
		DilatedHaarWavelet dilatedHaar = new DilatedHaarWavelet(0, 0);
		dilatedHaar.setInput(indexes);
		dilatedHaar.calculateOutput();
		dilatedHaars.add(dilatedHaar);
		
		
		//Add others
		int power = (int) (Math.log(rows)/Math.log(2));
		
		for (int i = 0; i < power; i++)
		{
			//int max = 4;
			int max = (int)(Math.pow(2,i));
			
			for (int j = 1; j <= max; j++)
			{
				dilatedHaar = new DilatedHaarWavelet(i, j);
				dilatedHaar.setInput(indexes);
				dilatedHaar.calculateOutput();
				dilatedHaars.add(dilatedHaar);
			}
			
				
		}
		
		for (int i = 0 ; i < rows; i++)
			for (int j = 0; j < rows; j++)
				this.entry[i][j] = dilatedHaars.get(i).getOutput().get(j)/Math.sqrt(rows);
		
		
	}

}
