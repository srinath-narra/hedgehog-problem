package com.hedgehog.traverse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author srina
 * @ An object That loads and parse input files for obtaining Apples count from left to right towards to bottom end.
 */

public final class HedgeHogTreeTraverse {

	
	 static int lowercountSave [] = new int[1];
	 static int rightcountSave [] = new int[1];
	/*
	 * Staring point to run the application
	 */
	   public static String initlizeData(File inputFile) throws IOException {

		//File inputFile = new File("input.txt");
		int matrix[][] = FileRead.parseFile(inputFile.getAbsolutePath());

		int m = FileRead.dimensionsRead[0];
		int n = FileRead.dimensionsRead[1];

		int maxLengthOfPath = m + n - 1;

		readMatrix(matrix, m, n, 0, 0, new int[maxLengthOfPath], 0);

		// Writing count in output.txt file. here comparing which path has more apples
		try {Files.write(Paths.get("output.txt"), String
					.valueOf(rightcountSave[0] > lowercountSave[0] ? rightcountSave[0] : lowercountSave[0]).getBytes()); 

		} catch (IOException e) {
			System.out.println("Exception thrown while writing into the file" + e.getMessage());
			return "Failure";
		}
		return "Success";
	}

	/**
	 * 
	 * @param matrix
	 * @param m
	 * @param n
	 * @param i
	 * @param j
	 * @param path
	 * @param idx
	 * @throws IOException
	 */
	private static void readMatrix(int matrix[][], int m, int n, int i, int j, int path[], int idx) {
		//creating path array to store apples from left to right 
		path[idx] = matrix[i][j];
	
		//picking apples and counting towards right and down direction
		
		int lowerMovesCount = 0;
		
		if (i == m - 1)  // moving lower direction
        { 
            for (int k = j + 1; k < n; k++) 
            { 
                path[idx + k - j] = matrix[i][k]; 
                
            } 
            for (int l = 0; l < idx + n - j; l++)  
            { 
            	lowerMovesCount +=path[l]; 
            } 
            lowercountSave[0] = lowercountSave[0] > lowerMovesCount ?  lowercountSave[0]: lowerMovesCount ;
            return; 
        } 
		
		int rightMovesCount = 0;
		int y =0;
		if (j == n - 1) {// moving right direction
			for (int k = i + 1; k < m; k++) {
				path[idx + k - i] = matrix[k][j]; // filling the path with apples in down direction from right index
				
			}
			for (int l = 0; l < idx + m - i; l++) { // counting each apple in path
				rightMovesCount +=path[l];
			}
			 rightcountSave[0] = rightcountSave[0] > rightMovesCount ?  rightcountSave[0]: rightMovesCount ;
			return;
		}
		 // moving in lower direction in recursive style 
		readMatrix(matrix, m, n, i + 1, j, path, idx + 1); 
		// moving in right direction in recursive style
		readMatrix(matrix, m, n, i, j + 1, path, idx + 1);
		
		
	}

}