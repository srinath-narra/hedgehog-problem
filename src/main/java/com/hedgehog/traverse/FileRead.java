package com.hedgehog.traverse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author srinath {@summary : Below class will be used as a Utility for reading the input file getting 
 *         dimensions and Matrix values}
 *
 */
public class FileRead {

	static int[] dimensionsRead = null;

	/**
	 * 
	 * @param fileName
	 * @return M*N matrix 
	 * @summary static method used for reading the file as a Utility
	 * @throws IOException
	 */
	public static int[][] parseFile(String fileName) throws IOException {

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int[][] numArray;
			String line = reader.readLine();
			if (line == null) {
				throw new IllegalArgumentException("No contents availble in file");
			}
			String[] dimensions = line.split("\\s+"); // Splitting the firstline with space and taking dimensions
			try {
				int rows = Integer.parseInt(dimensions[0]); // taking first dimension
				int cols = Integer.parseInt(dimensions[1]); // taking second dimension
				dimensionsRead = new int[] { rows, cols };
				numArray = new int[rows][cols];
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("First line of file has to be 'rows and cols'");
			}

			int row = 0;

			while ((line = reader.readLine()) != null && row < numArray.length) {
				String[] tokens = line.split("\\s+");
				if (tokens.length > numArray[row].length) {
					throw new IllegalArgumentException("more values provided in matrix row " + row);
				}

				for (int column = 0; column < tokens.length; column++) {
					try {
						int value = Integer.parseInt(tokens[column]);
						numArray[row][column] = value;
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException(
								"Non numeric value found in matrix row " + row + ", column " + column);
					}
				}
				row++;
			}
			return numArray;
		}
	}
}
