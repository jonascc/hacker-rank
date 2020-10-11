package hackerrank.practice.algorithms.medium.magicsquare;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MagicSquare {

	private String filePath;
	private static final Scanner scanner = new Scanner(System.in);
	private int[][] inputMatrix = new int[3][3];

	// Complete the formingMagicSquare function below.
	public int formingMagicSquare() throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
			
			// Creates two lists containing the input values.
			// workInputMatrix will be manipulated; listInputMatrix will be used to calculate the cost.
			List<List<Integer>> listInputMatrix = new ArrayList<>();
			List<List<Integer>> workInputMatrix = new ArrayList<>();
			for (int rowi = 0; rowi < 3; rowi++) {
				List<Integer> row1 = new ArrayList<>();
				List<Integer> row2 = new ArrayList<>();
				for (int coli = 0; coli < 3; coli++) {
					row1.add(inputMatrix[rowi][coli]);
					row2.add(inputMatrix[rowi][coli]);
				}
				listInputMatrix.add(row1);
				workInputMatrix.add(row2);
			}

			// The first element is the sum of each row and the second the sum of each
			// column
			List<List<Integer>> matrixSum = new ArrayList<>();
			// Calculates the sum of each row
			List<Integer> rowsSum = new ArrayList<>();
			for (int rowi = 0; rowi < 3; rowi++) {
				int sum = 0;
				for (Integer row : workInputMatrix.get(rowi)) {
					sum += row;
				}
				rowsSum.add(sum);
			}
			matrixSum.add(rowsSum);

			// Calculates the sum of each column
			List<Integer> colsSum = new ArrayList<>();
			for (int coli = 0; coli < 3; coli++) {
				int sum = 0;
				for (List<Integer> row : workInputMatrix) {
					sum += row.get(coli);
				}
				colsSum.add(sum);
			}
			matrixSum.add(colsSum);

			// Calculates the sum of the diagonals
			int mainDiagonalSum = 0;
			int secondaryDiagonalSum = 0;
			for (int maini = 0, secondaryi = 2; maini < 3; maini++, secondaryi--) {
				mainDiagonalSum += workInputMatrix.get(maini).get(maini);
				secondaryDiagonalSum += workInputMatrix.get(maini).get(secondaryi);
			}

			while (rowsSum.get(0) != 15 || rowsSum.get(1) != 15 || rowsSum.get(2) != 15 || colsSum.get(0) != 15 || colsSum.get(1) != 15
					|| colsSum.get(2) != 15 || mainDiagonalSum != 15 || secondaryDiagonalSum != 15) {

				boolean checkPosition = false;
				for (int rowi = 0; rowi < 3; rowi++) {
					for (int coli = 0; coli < 3; coli++) {
						checkPosition = true;
						while (checkPosition) {
							
								int row;
								int col;
								
								List<Integer> greaterThan15 = new ArrayList<>();
								List<Integer> lessThan15 = new ArrayList<>();
								
								row = rowsSum.get(rowi);
								col = colsSum.get(coli);
								
								if (row > 15) {
									greaterThan15.add(row);
								} else if (row < 15) {
									lessThan15.add(row);
								}
								if (col > 15) {
									greaterThan15.add(col);
								} else if (col < 15) {
									lessThan15.add(col);
								}
								
								boolean isCenter = false;
								char region = ' ';
								// Center
								if (rowi == 1 && coli == 1) {
									region = 'C';
									if (mainDiagonalSum > 15) {
										greaterThan15.add(mainDiagonalSum);
									} else if (mainDiagonalSum < 15) {
										lessThan15.add(mainDiagonalSum);
									}
									
									if (secondaryDiagonalSum > 15) {
										greaterThan15.add(secondaryDiagonalSum);
									} else if (secondaryDiagonalSum < 15) {
										lessThan15.add(secondaryDiagonalSum);
									}
								} else if ((rowi == 0 && coli == 0) || (rowi == 2 && coli == 2)) { // Main diagonal's edges
									region = 'M';
									if (mainDiagonalSum > 15) {
										greaterThan15.add(mainDiagonalSum);
									} else if (mainDiagonalSum < 15) {
										lessThan15.add(mainDiagonalSum);
									}
								} else if ((rowi == 0 && coli == 2) || (rowi == 2 && coli == 0)) { // Secondary diagonal's edges
									region = 'S';
									if (secondaryDiagonalSum > 15) {
										greaterThan15.add(secondaryDiagonalSum);
									} else if (secondaryDiagonalSum < 15) {
										lessThan15.add(secondaryDiagonalSum);
									}
								}
								
								int greaterThan15Size = greaterThan15.size();
								int lessThan15Size = lessThan15.size();
								
								int n = 0;
								
								if (isCenter) {
									n = 3;
								} else {
									n = 2;
								}
								
								if (greaterThan15Size > lessThan15Size && greaterThan15Size >= n && workInputMatrix.get(rowi).get(coli) > 1) {
								int temp = workInputMatrix.get(rowi).get(coli);
								workInputMatrix.get(rowi).set(coli, --temp);
									
									int tempRowSum = rowsSum.get(rowi);
									rowsSum.set(rowi, --tempRowSum);
									int tempColSum = colsSum.get(coli);
									colsSum.set(coli, --tempColSum);
									
									if (region == 'C') {
										mainDiagonalSum--;
										secondaryDiagonalSum--;
									} else if (region == 'M') {
										mainDiagonalSum--;
									} else if (region == 'S') {
										secondaryDiagonalSum--;
									}
								} else if (lessThan15Size > greaterThan15Size && lessThan15Size >= n && workInputMatrix.get(rowi).get(coli) < 9) {
								int temp = workInputMatrix.get(rowi).get(coli);
								workInputMatrix.get(rowi).set(coli, ++temp);
									
									int tempRowSum = rowsSum.get(rowi);
									rowsSum.set(rowi, ++tempRowSum);
									int tempColSum = colsSum.get(coli);
									colsSum.set(coli, ++tempColSum);
									
									if (region == 'C') {
										mainDiagonalSum++;
										secondaryDiagonalSum++;
									} else if (region == 'M') {
										mainDiagonalSum++;
									} else if (region == 'S') {
										secondaryDiagonalSum++;
									}
								} else {
									checkPosition = false;
								}
						}

					}
				}

			}
			
				// Calculates the cost
				int result = 0;
				for (int rowi = 0; rowi < 3; rowi++) {
					for (int coli = 0; coli < 3; coli++) {
						int temp = listInputMatrix.get(rowi).get(coli) - workInputMatrix.get(rowi).get(coli);
						result += Math.abs(temp);
					}
				}
				System.out.println(result);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
		}

		return 0;
	}

	public MagicSquare(String filePath) {
		this.filePath = filePath;

		for (int i = 0; i < 3; i++) {
			String[] sRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 3; j++) {
				int sItem = Integer.parseInt(sRowItems[j]);
				inputMatrix[i][j] = sItem;
			}
		}

		scanner.close();

	}

}
