package hackerrank.practice.algorithms.medium.magicsquare;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import hackerrank.practice.algorithms.medium.magicsquare.MagicSquareMain;

public class MagicSquareTest {
	
	@Test
	public void TestInputIsRead() throws IOException {
		String args[] = null;
		final InputStream original = System.in;
		final FileInputStream fips = new FileInputStream(new File("resources/medium/MagicMatrixTest.txt"));
		System.setIn(fips);
		MagicSquareMain.main(args);
		System.setIn(original);
		
		File magicSquareOutput = new File("resources/medium/MagicMatrix.txt");
		FileInputStream fis = new FileInputStream(magicSquareOutput);
		
		boolean emptyOutput = fis.available() == 0;
		fis.close();
		assertFalse(emptyOutput);
	}
	
}
