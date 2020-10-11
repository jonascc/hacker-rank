package hackerrank.practice.algorithms.medium.magicsquare;

import java.io.IOException;

public class MagicSquareMain {

	public static void main(String[] args) throws IOException {
		MagicSquare ms = new MagicSquare("resources/medium/MagicMatrix.txt");
		ms.formingMagicSquare();
	}

}
